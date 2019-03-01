# Implementing DDD on AWS

![image](/documents/images/coffee.jpg)

> Way to deal with complexity problem(s).

This repo is designed to run an EventStorming workshop to crunch a Coffeeshop bussiness domain, and implement it on Amazon Web Services. By going through this workshop, you will learn the classic DDD Strategical modeling and Tactical Design pattern, implement solution domains as microservice(s).

Besides, making all of these artifact automatically deploy to AWS is a must to have, you will also learn how to use AWS infrastructure provisioning service - Cloudformation and DevOps tools - CodePipeline, CodeBuild, CodeDeploy ...etc.

Core services used on AWS as the following :

## 01 Getting Started with Event Storming

[Why Event Storming](/documents/01-getting-started-with-EventStorming/README.md)

## 02 Case Study

[CallCarBar coffee shop](/documents/02-case-study/README.md)

## 03 Strategic Modeling

[CallCarBar Strategic Modeling](/documents/03-strategic-modeling/README.md)

## 04 Tactical Modeling

[CallCarBar Tactical Modeling](/documents/04-tactical-modeling/README.md)

## 05 Implementing stack

[Java](/documents/05-implementing-in-java/README.md)

## 06 CI/CD Pipeline

To leave your latop clean and keep the instruction could be run everywhere, recommend to use [AWS Cloud9](https://aws.amazon.com/cloud9/) to run deployment commands.

If you're willing to do this on laptop, there are some prerequisite tools should be installed, check as the following.

### Prerequisite

* AWS CLI
* AWS SAM CLI

### 6.1 Build and package

#### 6.0 Provision evenronment by Cloudformation

#### 6.1 Integrate with Amazon ECR

#### 6.2 Integrate Jib within Maven Lifecycle

### 6.2 Deploy Lambda function

#### 6.2.1 Packaging in Lambda Layer(s)

#### 6.2.2 SAM and SAM CLI

#### 6.2.3 Build up CI/CD pipeline

### 6.3 Deploy with ECS/Fargate

### 6.4 Deploy with EKS

## 07 Service Control and Monitoring

### 7.1 Integrate with App Mesh

## 08 Service Discovery

### 8.1 Integrate with Cloud Map

## 09 Microservices Design Pattern

### 9.1 Transaction Compensate

#### 9.1.1 Saga: Way to deal with distributed transactions