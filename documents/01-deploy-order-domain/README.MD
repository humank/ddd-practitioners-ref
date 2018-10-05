# Deploy Order Domain Service

This EventStormingWorkshop sample code is written in Java8 and leverage maven package management system to do all the java source code compile and package stuff. If you are familiared with Maven, then just go through the command:

> mvn clean test package

If you are new to java and maven, please check for these instructions to setup

[Install Java 8 SDK](https://www.ntu.edu.sg/home/ehchua/programming/howto/JDK_HowTo.html)

[Install Maven](https://maven.apache.org/install.html)

After the Java8 SDK and Maven installation finished, then run command:

```bash

cd EventStormingWorkShop/sources/java-implements

cd callcarbar-coffee-order

mvn clean test package
```

![image](/documents/images/mvn-process.png)

## SAM CLI Local Test

The Order Domain take the Order Lambda function as the entry point, which is proxy integrated with API Gateway.

The way to start the api gateway at local, just start with the command:

> sam local start-api

![image](/documents/images/order-domain-sam-local-startapi.png)

## SAM CLI Package & Deploy