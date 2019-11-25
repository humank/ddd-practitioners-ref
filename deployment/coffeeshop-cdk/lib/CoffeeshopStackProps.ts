import cdk = require('@aws-cdk/core');

export interface CoffeeshopStackProps extends cdk.StackProps {
    bucketName?: string;
}