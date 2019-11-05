#!/usr/bin/env node
import cdk = require('@aws-cdk/core');
import { CoffeeShopCdkStack } from '../lib/coffee-shop-cdk-stack';

const app = new cdk.App();
new CoffeeShopCdkStack(app, 'CoffeeshopCdkStack');