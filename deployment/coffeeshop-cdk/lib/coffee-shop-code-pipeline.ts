import * as cdk from '@aws-cdk/core';
import * as ecr from '@aws-cdk/aws-ecr';
import * as iam from '@aws-cdk/aws-iam';
import * as codebuild  from '@aws-cdk/aws-codebuild';
import * as codepipeline from '@aws-cdk/aws-codepipeline';
import * as codepipeline_actions from '@aws-cdk/aws-codepipeline-actions';
import * as ecs from '@aws-cdk/aws-ecs';
import * as ecsPatterns from '@aws-cdk/aws-ecs-patterns';
import * as codecommit from '@aws-cdk/aws-codecommit';
import {CodeBuildProject} from '@aws-cdk/aws-events-targets';
import {Duration} from '@aws-cdk/core';
import {Vpc} from '@aws-cdk/aws-ec2';
import * as dynamodb from '@aws-cdk/aws-dynamodb';
import * as events from '@aws-cdk/aws-events';
import {Rule} from "@aws-cdk/aws-events";
import * as ssm from '@aws-cdk/aws-ssm';

const DOCKER_IMAGE_PREFIX = 'solid-humank-coffeeshop/orders-web'
const CODECOMMIT_REPO_NAME = 'EventStormingWorkshop'

export class CoffeeShopCodePipeline extends cdk.Stack {

    readonly ecrRepository: ecr.Repository
    constructor(scope: cdk.App, id: string, props?: cdk.StackProps) {
        super(scope, id, props);
        this.ecrRepository = new ecr.Repository(this, 'Repository', {
            repositoryName: DOCKER_IMAGE_PREFIX,
        });

        const buildRole = new iam.Role(this, 'CodeBuildIamRole', {
            assumedBy: new iam.ServicePrincipal('codebuild.amazonaws.com')
        });

        buildRole.addToPolicy(new iam.PolicyStatement({
            resources: ['*'],
            actions: ['ecr:GetAuthorizationToken']
        }));

        buildRole.addToPolicy(new iam.PolicyStatement({
            resources: [`${this.ecrRepository.repositoryArn}*`],
            actions: ['ecr:*']
        }));

        // ECR LifeCycles
        // repository.addLifecycleRule({ tagPrefixList: ['prod'], maxImageCount: 9999 });
        this.ecrRepository.addLifecycleRule({maxImageAge: cdk.Duration.days(30)});

        const defaultSource = codebuild.Source.gitHub({
            owner: 'humank',
            repo: 'EventStormingWorkShop',
            webhook: true, // optional, default: true if `webhookFilteres` were provided, false otherwise
            webhookFilters: [
                codebuild.FilterGroup.inEventOf(codebuild.EventAction.PUSH).andBranchIs('master'),
            ], // optional, by default all pushes and Pull Requests will trigger a build
        });


        new codebuild.Project(this, 'CodeBuildProject', {
            role: buildRole,
            source: defaultSource,
            // Enable Docker AND custom caching
            cache: codebuild.Cache.local(codebuild.LocalCacheMode.DOCKER_LAYER, codebuild.LocalCacheMode.CUSTOM),
            environment: {
                buildImage: codebuild.LinuxBuildImage.UBUNTU_14_04_OPEN_JDK_8,
                privileged: true,
            },
            buildSpec: codebuild.BuildSpec.fromObject({
                version: '0.2',
                phases: {
                    build: {
                        commands: [
                            'echo "Build all modules"',
                            'echo "Run Maven clean install to have all the required jars in local .m2 repository"',
                            'pwd',
                            'ls',
                            'cd sources/coffeeshop',
                            'pwd',
                            'mvn clean install -Dmaven.test.skip=true'
                        ]
                    },
                    post_build: {
                        commands: [
                            'TAG=${CODEBUILD_RESOLVED_SOURCE_VERSION}',
                            'LATEST="latest"',
                            'echo "Pack web modules into docker and push to ECR"',
                            'echo "ECR login now"',
                            '$(aws ecr get-login --no-include-email)',
                            'pwd',
                            'echo "build orders-web docker image"',
                            'cd orders-web',
                            'mvn package -Dmaven.test.skip=true',
                            `docker build -f src/main/docker/Dockerfile.jvm -t ${this.ecrRepository.repositoryUri}:$LATEST .`,
                            `docker images`,
                            `docker tag ${this.ecrRepository.repositoryUri}:$LATEST ${this.ecrRepository.repositoryUri}:$TAG`,
                            'echo "Pushing Orders-web"',
                            `docker images`,
                            `docker push ${this.ecrRepository.repositoryUri}:$TAG`,
                            `docker push ${this.ecrRepository.repositoryUri}:$LATEST`,
                            'echo "finished ECR push"',
                        ]

                    }
                },
                cache:{
                    paths:[
                        '/root/.m2/**/*',
                    ]
                }
            })
        });

        const vpc = Vpc.fromLookup(this, 'CoffeeShopCdkStack/CoffeeShopVPC',{
            vpcName: 'CoffeeShopCdkStack/CoffeeShopVPC',
            isDefault: false,
        });

        const cluster = new ecs.Cluster(this, 'Cluster', {
            clusterName: 'coffeeshop',
            vpc
        });



        const taskDefinition = new ecs.TaskDefinition(this, 'orders-web-Task', {
            compatibility: ecs.Compatibility.FARGATE,
            memoryMiB: '512',
            cpu: '256',
        });

        taskDefinition.addContainer('defaultContainer', {
            image: ecs.ContainerImage.fromRegistry('amazon/amazon-ecs-sample'),
            logging: new ecs.AwsLogDriver({
                streamPrefix: 'coffeeshop-orders-web',

            })
        }).addPortMappings({
            containerPort: 8080
        });

        const fargatesvc = new ecsPatterns.ApplicationLoadBalancedFargateService(this, 'AlbSvc', {
            cluster,
            taskDefinition,
        })

        const fargateTaskRole = fargatesvc.service.taskDefinition.taskRole;
        fargateTaskRole.addToPolicy(new iam.PolicyStatement({
            resources: ['*'],
            actions: ['events:*']
        }));
        const table = new dynamodb.Table(this, 'Order', {
            partitionKey: { name: 'seqNo', type: dynamodb.AttributeType.NUMBER },
            billingMode: dynamodb.BillingMode.PAY_PER_REQUEST,
            tableName: 'Order',
        });

        table.grantFullAccess(fargateTaskRole);



        const coffeeshop_eventbus = new events.EventBus(this, 'EventBus', {
            eventBusName: 'coffeeshop-event-bus',
        });

        const rule = new Rule(this, 'OrderCreatedRule',{
            eventPattern:{
                source:['{"detail-type": [ "customevent" ],"source": ["solid.humank.coffeeshop.order"]}'
                ]
            },
            eventBus: coffeeshop_eventbus,
            ruleName: 'OrderCreatedRule'
        });



        //add ssm parameter store for cloudwatchevent put usage
        const eventSourceParam = new ssm.StringParameter(this, 'eventSourceParam', {
            parameterName: '/coffeeshop/events/ordercreated/event_source',
            stringValue: 'solid.humank.coffeeshop.order',
        });

        // Grant read access to some Role
        eventSourceParam.grantRead(fargateTaskRole);

        //add ssm parameter store for cloudwatchevent put usage
        const eventArnParam = new ssm.StringParameter(this, 'eventArnParam', {
            parameterName: '/coffeeshop/events/ordercreated/event_arn',
            stringValue: rule.ruleArn,
        });

        // Grant read access to some Role
        eventArnParam.grantRead(fargateTaskRole);

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
        const codePipeline = new codepipeline.Pipeline(this, 'CoffeeShopPipeline', {
            pipelineName: 'CoffeeShopPipeline',
        });

        const sourceOutputEcr = new codepipeline.Artifact();
        const sourceOutputCodeCommit = new codepipeline.Artifact();
        const sourceActionECR = new codepipeline_actions.EcrSourceAction({
            actionName: 'ECR',
            repository: this.ecrRepository,
            imageTag: 'latest', // optional, default: 'latest'
            output: sourceOutputEcr,
        });

        const codecommitRepo = new codecommit.Repository(this, 'GitRepo', {
            repositoryName: CODECOMMIT_REPO_NAME
        });

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

        new cdk.CfnOutput(this, 'CodeCommitRepoName', {
            value: codecommitRepo.repositoryName
        })

        let codeCommitHint = `
Create a "imagedefinitions.json" file and git add/push into CodeCommit repository "${CODECOMMIT_REPO_NAME}" with the following value:

[
  {
    "name": "defaultContainer",
    "imageUri": "${this.ecrRepository.repositoryUri}:latest"
  }
]
`
        new cdk.CfnOutput(this, 'Hint', {
            value: codeCommitHint
        })

        new cdk.CfnOutput(this, 'CodeBuildProjectName', {
            value: CodeBuildProject.name
        })
    }
}