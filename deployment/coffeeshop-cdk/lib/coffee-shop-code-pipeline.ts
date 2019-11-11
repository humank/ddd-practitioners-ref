import cdk = require('@aws-cdk/core');
import ec2 = require('@aws-cdk/aws-ec2');
import ecr = require('@aws-cdk/aws-ecr');
import iam = require('@aws-cdk/aws-iam');
import codebuild = require('@aws-cdk/aws-codebuild');
import codepipeline = require('@aws-cdk/aws-codepipeline');
import codepipeline_actions = require('@aws-cdk/aws-codepipeline-actions');
import ecs = require('@aws-cdk/aws-ecs');
import ecsPatterns = require('@aws-cdk/aws-ecs-patterns');
import { CodeBuildProject } from '@aws-cdk/aws-events-targets';
import { Duration } from '@aws-cdk/core';

const DOCKER_IMAGE_PREFIX = 'solid-humank-coffeeshop'
const CODECOMMIT_REPO_NAME = 'EventStormingWorkshop'

export class CoffeeShopCodePipeline extends cdk.Stack {
    readonly ecrRepository: ecr.Repository
    constructor(scope: cdk.Construct, id: string, props?: cdk.StackProps) {
        super(scope, id, props);
        this.ecrRepository = new ecr.Repository(this, 'Repository', {
            repositoryName: `${DOCKER_IMAGE_PREFIX}-${this.stackName.toLowerCase()}`,
        });

        const buildRole = new iam.Role(this, 'CodeBuildIamRole', {
            assumedBy: new iam.ServicePrincipal('codebuild.amazonaws.com')
        })
        buildRole.addToPolicy(new iam.PolicyStatement({
            resources: ['*'],
            actions: ['ecr:GetAuthorizationToken']
        }));

        buildRole.addToPolicy(new iam.PolicyStatement({
            resources: [`${this.ecrRepository.repositoryArn}*`],
            actions: ['ecr:*']
        }));

        // ECR Lifecycles
        // repository.addLifecycleRule({ tagPrefixList: ['prod'], maxImageCount: 9999 });
        this.ecrRepository.addLifecycleRule({ maxImageAge: cdk.Duration.days(30) });

        const gitHubSource = codebuild.Source.gitHub({
            owner: 'humank',
            repo: 'EventStormingWorkshop',
            webhook: true, // optional, default: true if `webhookFilteres` were provided, false otherwise
            webhookFilters: [
                codebuild.FilterGroup.inEventOf(codebuild.EventAction.PUSH).andBranchIs('master'),
            ], // optional, by default all pushes and Pull Requests will trigger a build
        });

        new codebuild.Project(this, 'CodeBuildProject', {
            role: buildRole,
            source: gitHubSource,
            // Enable Docker AND custom caching
            cache: codebuild.Cache.local(codebuild.LocalCacheMode.DOCKER_LAYER, codebuild.LocalCacheMode.CUSTOM),
            environment: {
                buildImage: codebuild.LinuxBuildImage.UBUNTU_14_04_OPEN_JDK_11,
                privileged: true,
            },
            buildSpec: codebuild.BuildSpec.fromObject({
                version: '0.2',
                phases: {
                    install: {
                        'runtime-versions': {
                            java: 'corretto11'
                        }
                    },
                    build: {
                        commands: [
                            'cd sources/coffeeshop',
                            'echo "Build all of the modules"',
                            'echo "Run Maven clean install to have all the required jars in local .m2 repository"',
                            'mvn clean install'
                        ]
                    },
                    post_build:{
                        commands:[
                            'echo "Pack web modules into docker and push to ECR"',
                            'echo "ECR login now"',
                            'USER_ID=$(aws sts get-caller-identity | jq -r \'.Account\')',
                            'REGION=$(aws configure get region)',
                            '$(aws ecr get-login --no-include-email)',

                            'echo "build orders-web docker image"',
                            'cd orders-web && mvn clean package && cd ..',
                            'docker build -f src/main/docker/Dockerfile.jvm -t solid-humank-coffeeshop/orders-web .',
                            'docker tag solid-humank-coffeeshop/orders-web:latest "$USER_ID".dkr.ecr."$REGION".amazonaws.com/solid-humank-coffeeshop/orders-web:latest',

                            'echo "Pushing Orders-web"',
                            'docker push "$USER_ID".dkr.ecr."$REGION".amazonaws.com/solid-humank-coffeeshop/orders-web:latest',
                            'echo "finished ECR push"'
                        ]

                    }
                }
            })
        });

        const vpc = ec2.Vpc.fromLookup(this, 'CoffeeShopVPC', {
            // vpcName: 'CoffeeShopVPC',
            isDefault: true
        })


        const cluster = new ecs.Cluster(this, 'Cluster', {
            clusterName: 'coffeeshop',
            vpc
        });

        const taskDefinition = new ecs.TaskDefinition(this, 'Task', {
            compatibility: ecs.Compatibility.FARGATE,
            memoryMiB: '512',
            cpu: '256'
        });

        const expressContainer = taskDefinition.addContainer('express', {
            image: ecs.ContainerImage.fromAsset(__dirname + '/../../express/')
        });

        expressContainer.addPortMappings({
            containerPort: 8080
        });

        const fargatesvc = new ecsPatterns.ApplicationLoadBalancedFargateService(this, 'express', {
            cluster,
            taskDefinition,
        })

        // Create Fargate Service
        const fargateService = new ecsPatterns.NetworkLoadBalancedFargateService(this, 'sample-app', {
            cluster,
            taskImageOptions: {
                image: ecs.ContainerImage.fromRegistry("amazon/amazon-ecs-sample")
            },
        });

        // Setup AutoScaling policy
        const scaling = fargateService.service.autoScaleTaskCount({ maxCapacity: 3 });
        scaling.scaleOnCpuUtilization('CpuScaling', {
            targetUtilizationPercent: 50,
            scaleInCooldown: cdk.Duration.seconds(60),
            scaleOutCooldown: cdk.Duration.seconds(60)
        });

        // if the default image is not from ECR, the ECS task execution role will not have ECR pull privileges
        // we need grant the pull for it explicitly
        this.ecrRepository.grantPull({
            grantPrincipal: (fargatesvc.service.taskDefinition.executionRole as iam.IRole)
        })

        // reduce the default deregistration delay timeout from 300 to 30 to accelerate the rolling update
        fargatesvc.targetGroup.setAttribute('deregistration_delay.timeout_seconds', '30')
        // customize the healthcheck to speed up the ecs rolling update
        fargatesvc.targetGroup.configureHealthCheck({
            interval: Duration.seconds(5),
            healthyHttpCodes: '200',
            healthyThresholdCount: 2,
            unhealthyThresholdCount: 3,
            timeout: Duration.seconds(4),
        })

        // CodePipeline
        const codePipeline = new codepipeline.Pipeline(this, 'Ecr2EcsPipeline', {
            pipelineName: 'Ecr2Ecs',
        });

        const sourceOutputEcr = new codepipeline.Artifact();
        const sourceOutputCodeCommit = new codepipeline.Artifact();
        const sourceActionECR = new codepipeline_actions.EcrSourceAction({
            actionName: 'ECR',
            repository: this.ecrRepository,
            imageTag: 'latest', // optional, default: 'latest'
            output: sourceOutputEcr,
        });

        // const codecommitRepo = new codecommit.Repository(this, 'GitRepo', {
        //     repositoryName: CODECOMMIT_REPO_NAME
        // });

        const sourceActionCodeCommit = new codepipeline_actions.CodeCommitSourceAction({
            actionName: 'CodeCommit',
            // repository: codecommit.Repository.fromRepositoryName(this, 'GitRepo', CODECOMMIT_REPO_NAME),
            repository: codecommitRepo,
            output: sourceOutputCodeCommit,
        });

        codePipeline.addStage({
            stageName: 'Source',
            actions: [sourceActionCodeCommit, sourceActionECR],
        });

        codePipeline.addStage({
            stageName: 'Deploy',
            actions: [
                new codepipeline_actions.EcsDeployAction({
                    actionName: 'DeployAction',
                    service: fargatesvc.service,
                    // if your file is called imagedefinitions.json,
                    // use the `input` property,
                    // and leave out the `imageFile` property
                    input: sourceOutputCodeCommit,
                    // if your file name is _not_ imagedefinitions.json,
                    // use the `imageFile` property,
                    // and leave out the `input` property
                    // imageFile: sourceOutput.atPath('imageDef.json'),
                }),
            ],
        });
        new cdk.CfnOutput(this, 'ServiceURL', {
            value: `http://${fargatesvc.loadBalancer.loadBalancerDnsName}`
        })

        new cdk.CfnOutput(this, 'StackId', {
            value: this.stackId
        })

        new cdk.CfnOutput(this, 'StackName', {
            value: this.stackName
        })
    }
}