# Implementing DDD on AWS

![image](documents/images/coffee.jpg)

```bash
picture license free from : Pexels
https://www.pexels.com/photo/background-beverage-breakfast-brown-414645/
```

why you build software ? It's all about solve the business problem. However, there will be a gap from requirements to implementaion, it's a painpoint for all stakeholders. As a builder, you may eager to know how to mitigate this gap in a fluent way to deal with it, the Event Storming approach is "The One".

## Why EventStorming

![image](documents/images/problemsolving.png)

As the Complexity Problem domain growing, it's hard to have a fluent way to help Product Owner and Developer team to collaborate to develop a system which fulfill all of the requirements withcorrect understanding.

This problem is not a new story, from 2003, Eric Evans has already introduced the "Domain Driven Design" implementing approach to help developers, but it's really hard to implement from the Strategy and Tactic abstraction level.

After several years, Alberto Brandolini introduced the Event Storming approach to go through the DDD concept and make it easy to implement. 

**Event storming is a rapid, lightweight, and underappreciated group modeling technique that is intense, fun, and useful for accelerating development teams**. It's a synthesis of facilitated group learning practices from Gamestorming and the principles of domain-driven design (DDD). The technique isn't limited to software development. Frankly speaking it is recommend to invite all the stakeholders to join the storming workshop, collect each opinions from each viewpoints.

You can apply it to practically any technical or business domain, especially those that are large, complex, or both.

### Event Storming

![Event Storming](https://storage.googleapis.com/xebia-blog/1/2018/10/From-EventStorming-to-CoDDDing-New-frame-3.jpg)

> Reference from Kenny Bass - https://storage.googleapis.com/xebia-blog/1/2018/10/From-EventStorming-to-CoDDDing-New-frame-3.jpg



Take a look on this diagram, there are a few colorful stickers with different intention:

* **Domain Events** ( Orange sticker ) 
  * **Event**, Stands for the fact happened in specific business context
* **Actions** (Blue sticker)   
  * **Command**, It is a request or intention, raised by a role or time or external system
* **Information** ( Green sticker)  
  * **View Model**, it's a supporting information to help role to make a decision to raise a command
* **Consistent Business Rules** ( Yellow sticker) 
  * **Aggregate**
    * Has the responsibility to accept or fulfill the intention of command
    * Represent a specific business capability to support command
    * Shouldn't naming it with role concept, or you will get only the "actor" in the whole business.
    * Should be in small scope
    * Protects business invariants
    * Exposes by ID
    * And communicated by eventual consistency
* **Eventual Consistent Business rules** (Lilac sticker)
  * Policy
    * It's all about rules, sometimes the rules comes from external regulation restriction, or maybe account login success/fail process logic.
    * example : when login attemptation fail times up to 3, then lock this account

### The benefits from Event Storming

When you want to divide a monolithic system into microservices, or you want to build up a new system from scratch, the most pain point is there is no idea to clarify the system boundary. Even though you interview with domain experts, and get a whole bunch of requirement documents, it still not easy to start the design. Maybe you are familiar with the classic modeling methodologies such as :

> UP/RUP, OOAD, RAD, Use Case Modeling, ICONIX Processing ...etc

No matter any one of these methodologies, all deeply depends on experts skill, but if you apply Event Storming workshop, you could leverage team's collaboration to acquire requirements with key events. However, these events are most concerned business value by stakeholders. With different color, pin, and diagrams to group the actions, events, and the aggregate context. Naturally forms up the domain boundary.

## Ramp up

If you are new to Event Storming, don't know how to start the domain event exploring journey, recommend to think about a timeline: 

> how many immutable fact events occurred, from you wake up at this morning, until now you read this post.



## Coffee shop business scenario

## Modeling and Development

## Running on AWS

> This workshop explained  is running on AWS. By using Lambda and cloudwatch event to present how can we keep model classes at the core, and leverage the outside adapter to interact with other domain

## Design each Microservices in Port-adapter concept

![image](documents/images/implementation.png)

> The famous Port-Adapter pattern is best suite for developing microservices. Focus on core domain problem, and switch any infrastructure or communication tools as you need.

![image](documents/images/orderdomain.png)

> For this workshop demo, design a order domain object, and leverage AWS services to do persistent, http request accept and handler, and event propagation.

## Using Lambda function as the entry point

You can easily export a lambda function to accept the incomg command, and do some stuff.

## Using CloudWatch Event as the integration Event

If cross boundary event did occured in current domain, never call other domain service directly, just publish a cross-domain-event. On AWS, the most appropriate one is using CloudWatch Event, it's a near-real-time event, high performance and scalable.

## Using DynamoDB as the Write Model/ Read Model persistent Repository

Once capture Model with Domain Experts, you can design Write Model first, and create Query usage Read Model.

## Launch this workshop example on AWS

![image](documents/images/eventhandling.png)

This workshop example explained a Coffee shop use case, go through a customer order coffee and barista accept the order then make coffee.

### Prerequisite

1. [Create a Lambda function Execution Role](https://docs.aws.amazon.com/lambda/latest/dg/intro-permission-model.html#lambda-intro-execution-role)
2. [Enable Amazon CloudWatch Logs for API Gateway](https://aws.amazon.com/premiumsupport/knowledge-center/api-gateway-cloudwatch-logs/)
3. [Install AWS CLI](https://aws.amazon.com/cli/)
4. [Install SAM CLI](https://github.com/awslabs/aws-sam-cli)
5. Get used to do Unit Test - mvn test
6. Prepare Java/Maven env by IDE or commandline
7. [Configuring the AWS CLI](https://docs.aws.amazon.com/cli/latest/userguide/cli-chap-getting-started.html)

### Deployment instructions

#### 01 - Define CloudWatchEvent for the Order-created Event

[Define CloudWatch Event for Coffee Order Created Event](/documents/01-define-cloudwatchevent/README.MD)

#### 02 - Deploy Order Domain lambda function with proxy integration on API Gateway

[Deploy Order Domain](/documents/02-deploy-order-domain/README.MD)

#### 03 - Deploy MakeUp Domain lambda function with proxy integration on API Gateway

[Deploy MakeUp Domain](/documents/03-deploy-makeup-domain/README.MD)

#### 04 - Experience and Test the Demo

[Experience Coffee order demo](/documents/04-experience/README.MD)



## Further Information

- Vernon, Vaughn. “Ch. 7, Event Storming.” Domain-Driven Design Distilled, Addison-Wesley, 2016. - https://www.amazon.com/Domain-Driven-Design-Distilled-Vaughn-Vernon/dp/0134434420
- Brandolini, Alberto. Introducing EventStorming. Leanpub, to be released, eventstorming.com/ - https://leanpub.com/introducing_eventstorming
- Brandolini, Alberto. “Ziobrando’s Lair.” Introducing Event Storming, Nov. 2013, ziobrando.blogspot.de/2013/11/introducing-event-storming.html.
- Brandolini, Alberto. Event Storming Recipes. SlideShare, 21 June 2014, de.slideshare.net/ziobrando/event-storming-recipes.
- Rayner, Paul. Event Storming. SlideShare, 26 May 2017, [www.slideshare.net/AgileDenver/event-storming-76390807](http://www.slideshare.net/AgileDenver/event-storming-76390807).
- Brandolini, Alberto. Model Storming. SlideShare, 19 Sept. 2013, [www.slideshare.net/ziobrando/model-storming](http://www.slideshare.net/ziobrando/model-storming).
- Brandolini, Alberto. 50.000 Orange Stickies Later, 7 November 2018, https://www.youtube.com/watch?v=NGXl1D-KwRI
- Business Rules, https://medium.com/plexiti/business-rules-367e430ee168
- How to use Example Mapping & Event Storming, https://hiptest.com/blog/bdd/how-to-use-example-mapping-event-storming/
- What is the Aggregate, https://twitter.com/mathiasverraes/status/1141242508892155904?s=20
- How to monitor Domain Events for Product management, https://xebia.com/blog/eventstorming-and-how-to-monitor-domain-events-for-product-management/



## TODO

- Using AWS SDK for Java v2 to have better performance
- Put on X-ray inspection diagram
- Try to use Dagger2 as the DI framework to have better cold start time