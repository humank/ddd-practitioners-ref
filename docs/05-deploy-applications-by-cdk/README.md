*Will update ASAP*

To deploy applications to AWS, you need to have the following essential tools installed:

* AWS CLI
* AWS CDK

quick deploy: 

```shell script
cd deployment/coffeeshop-cdk

npm run build 

cdk synth

cdk deploy CoffeeShopCdkStack CoffeeShopCodePipeline 
```