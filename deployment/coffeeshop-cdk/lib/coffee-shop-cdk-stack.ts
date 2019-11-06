import ecs = require('@aws-cdk/aws-ecs');
import ec2 = require('@aws-cdk/aws-ec2');
import cdk = require('@aws-cdk/core');

export class CoffeeShopCdkStack extends cdk.Stack {
  constructor(scope: cdk.App, id: string, props?: cdk.StackProps) {
    super(scope, id, props);

    // Create a VPC
    const vpc = new ec2.Vpc(this, 'CoffeeShopVPC', {
      cidr: '10.0.0.0/16',
      natGateways: 1
    });


    // Create a ECS Fargate cluster
    const cluster = new ecs.Cluster(this, 'CoffeeShop-Fargate', { vpc });
  }
}