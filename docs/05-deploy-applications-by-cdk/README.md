# Deploy Coffeeshop application through AWS Code* Suite with AWS CDK



**Kudos for your insist learning from this repository. It's time to deploy the applications to real AWS environment.**





![](../img/EventStormingWorkshop-CDK.jpg)

To deploy applications to AWS, you need to have the following essential tools installed:

* [AWS CLI](https://docs.aws.amazon.com/zh_tw/cli/latest/userguide/cli-chap-install.html)
* [AWS CDK](https://docs.aws.amazon.com/cdk/latest/guide/getting_started.html)



## Deploy instruction

### Deploy infrastructure by CDK

```shell
cd deployment/coffeeshop-cdk

npm run build 

cdk synth

cdk deploy CoffeeShopCdkStack 
```

**By running this CDK application, You will get a standard VPC with 3 Availablity Zones environment, and one NATGateway serving private subnets.**

**Besides, in order to have an ease of use container orcheration service, an ECS Cluster with Fargate mode is also created.**

### Deploy Application by Code* family

```shell script
cd deployment/coffeeshop-cdk

npm run build 

cdk synth

cdk deploy CoffeeShopCodePipeline 
```

**This workshop sample code is developed in Java8 with Quarkus Framework, Libs dependency managed by Maven. By running this CDK CoffeeShopCodePipeline stack, You will have:**

* CodeCommit Repository - for auto deployment
* CodeBuild - Get Github WebHooked project, build source code, build docker image, Push image to ECR,  deploy **Orders-web** Fargate Service, deploy **coffee-sls Lambda Function**, create **Dynamodb Table -{ Order, Coffee}**, create Event Rule in default **Amazon EventBridge** ..etc.



**Deploy Result**

```shell
Outputs:
CoffeeShopCodePipeline.CodeBuildProjectName = CodeBuildProject
CoffeeShopCodePipeline.AlbSvcServiceURL46A1D997 = http://Coffe-AlbSv-5MLHALGIGWUB-82783022.us-west-2.elb.amazonaws.com
CoffeeShopCodePipeline.AlbSvcLoadBalancerDNS20AA0F0B = Coffe-AlbSv-5MLHALGIGWUB-82783022.us-west-2.elb.amazonaws.com
CoffeeShopCodePipeline.Hint =
Create a "imagedefinitions.json" file and git add/push into CodeCommit repository "EventStormingWorkshop" with the following value:

[
  {
    "name": "defaultContainer",
    "imageUri": "123456789012.dkr.ecr.us-west-2.amazonaws.com/solid-humank-coffeeshop/orders-web:latest"
  }
]

CoffeeShopCodePipeline.Bucket = coffeeshop-nypea
CoffeeShopCodePipeline.CodeCommitRepoName = EventStormingWorkshop
CoffeeShopCodePipeline.ServiceURL = http://Coffe-AlbSv-5MLHALGIGWUB-82783022.us-west-2.elb.amazonaws.com
CoffeeShopCodePipeline.StackName = CoffeeShopCodePipeline
CoffeeShopCodePipeline.StackId = arn:aws:cloudformation:us-west-2:584518143473:stack/CoffeeShopCodePipeline/f10c0520-0618-11ea-8122-023709c486f0

Stack ARN:
arn:aws:cloudformation:us-west-2:584518143473:stack/CoffeeShopCodePipeline/f10c0520-0618-11ea-8122-023709c486f0
```

Do remember to create a Create a "imagedefinitions.json" file and git add/push into CodeCommit repository "EventStormingWorkshop" with the following value:

[
  {
    "name": "defaultContainer",
    "imageUri": "your ecr repository arn for this coffeeshop/solid-humank-coffeeshop/orders-web:latest"
  }
]



### Setup Lambda function trigger with EventBridge

```shell
targetArn=$(aws lambda get-function --function-name coffee-sls-OrderCreatedHandler | jq \'.Configuration.FunctionArn\')

aws events  put-targets --rule OrderCreatedRule --targets "Id"="OrderCreated","Arn"=$targetArn'

ruleArn=$(aws events list-rules --name-prefix OrderCreatedRule | jq -r '.Rules[0].Arn')

aws lambda add-permission \
	--function-name coffee-sls-OrderCreatedHandler \
  --action lambda:InvokeFunction \
	--statement-id stat-coffee-sls \
  --principal events.amazonaws.com \
	--source-arn $ruleArn
```

### Run Test

**As all of the setting done, now you could hit the url which you created to make an coffee order:**

The **Orders-web** service endpoint is the Stak output - **CoffeeShopCodePipeline.AlbSvcServiceURLxxxx**

```shell
curl --header "Content-Type: application/json" \                                                                                            
        --request POST \
        --data '{"items":[{"productId":"5678","qty":2,"price":200}]}' \
        http://Coffe-AlbSv-5MLHALGIGWUB-82783022.us-west-2.elb.amazonaws.com/order
{"items":[{"productId":"5678","qty":2,"price":200,"fee":400}],"status":0,"id":"ord-20191126-5906","createdDate":1574801783.400000000,"modifiedDate":null}
```

