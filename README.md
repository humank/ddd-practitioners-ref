# Running DDD on AWS

![image](/documents/images/coffee.jpg)

```bash
picture license free from : Pexels
https://www.pexels.com/photo/background-beverage-breakfast-brown-414645/
```

>This demo is running on AWS. By using Lambda and cloudwatch event to present how can we keep model classes at the core, and leverage the outside adapter to interact with other domain.

## Why Event Storming

![image](/documents/images/problemsolving.png)

As the Complexity Problem domain growing, it's hard to have a fluent way to help Product Owner and Developer team to collaborate to produce a requriments all fulfilled Service/system.

This problem is not a new story, from 2003, Eric Evans has already introduced the "Domain Driven Design" implementing approach to help developers, but it's really hard to implement from the Strategy and Tactic abstraction level.

After several years, Alberto Brandolini introduced the Event Storming approach to go through the DDD concept and make it easy to implement. 

Event storming is a rapid, lightweight, and underappreciated group modeling technique that is intense, fun, and useful for accelerating development teams. It's a synthesis of facilitated group learning practices from Gamestorming and the principles of domain-driven design (DDD). The technique isn't limited to software development. Frankly speaking it is recommend to invite all the stakeholders to join the storming workshop, collect each opinions from each viewpoints.

You can apply it to practically any technical or business domain, especially those that are large, complex, or both.

## What is the benefit

When you want to divide a monolithic system into microservices, or you want to build up a new system from scratch, the most pain point is there is no idea to clarify the system boundary. Even though you interview with domain experts, and get a whole bunch of requirement documents, it still not easy to start the design. Maybe you are familiar with the classic modeling methodologies such as :

>UP/RUP, OOAD, RAD, Use Case Modeling, ICONIX Processing ...etc

No matter any one of these methodologies, all deeply depends on experts skill, but if you apply Event Storming workshop, you could leverage team's collaboration to acquire requirements with key events. However, these events are most concerned business value by stakeholders. With different color, pin, and diagrams to group the actions, events, and the aggregate context. Naturally forms up the domain boundary.

## How to implement DDD via Event Storming approach

There are plenty materials to introduce how to run the EventStorming process, most useful materials are listed:

* [Join EventStorming workshop](https://www.eventstorming.com/)

* [Book from Alberto Brandolini](https://www.eventstorming.com/book/)

* [More deatil from awesome-eventstorming](https://github.com/mariuszgil/awesome-eventstorming)

## Design each Microservices in Port-adapter concept

![image](/documents/images/implementation.png)

>The famous Port-Adapter pattern is best suite for developing microservices. Focus on core domain problem, and switch any infrastructure or communication tools as you need.

![image](/documents/images/orderdomain.png)

>For this workshop demo, design a order domain object, and leverage AWS services to do persistent, http request accept and handler, and event propagation.

## Using Lambda function as the entry point

You can easily export a lambda function to accept the incomg command, and do some stuff.

## Using CloudWatch Event as the integration Event

If cross boundary event did occured in current domain, never call other domain service directly, just publish a cross-domain-event. On AWS, the most appropriate one is using CloudWatch Event, it's a near-real-time event, high performance and scalable.

## Using DynamoDB as the Write Model/ Read Model persistent Repository

Once capture Model with Domain Experts, you can design Write Model first, and create Query usage Read Model.

## Launch this workshop example on AWS

![image](/documents/images/eventhandling.png)

This workshop example explained a Coffee shop use case, go through a customer order coffee and barista accept the order then make coffee.

### Prerequisite

1. Create a Lambda function Execution Role
2. Add API Gateway Logging policy to Cloudwatch
3. Install SAM CLI
4. Get used to do Unit Test
5. Java/Maven Environment
6. Configure AWS CLI Enviroment

## TODO

* Add installation instructions for the application
* Using AWS SDK for Java v2 to have better performance
* Put on X-ray inspection diagram
* Try to use Dagger2 as the DI framework to have better cold start time
* Modify the barista sample from built-in nodejs lambda function to Java Barista Maven Module
