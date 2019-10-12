#!/usr/bin/env node
import cdk = require('@aws-cdk/core');
import { CoffeeshopCdkStack } from '../lib/coffeeshop-cdk-stack';

const app = new cdk.App();
new CoffeeshopCdkStack(app, 'CoffeeshopCdkStack');