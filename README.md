# Implementing Domain Driven Design (DDD) on AWS

![image](docs/img/coffee.jpg)
_Picture license-free from [Pexels](https://www.pexels.com/photo/background-beverage-breakfast-brown-414645/)_

why you build software ? It's all about solve the business problem. However, there will be a gap from requirements to implementaion, it's a pain point for all stakeholders. As a builder, you may eager to know how to mitigate this gap in a fluent way to deal with it, the Event Storming approach is "The One".

## Table of Contents
- [00 - EventStorming](#eventstorming)
  - [Why EventStorming?](#why-eventstorming)
  - [EventStorming Terms](#eventstorming-terms)
  - [EventStorming Benefits](#eventstorming-benefits)
- [01 - Hands-on: Events exploring](01-hands-on-events-exploring)
- [02 - Coffee shop business scenario](02-coffee-shop-scenario)
- [03 - Modeling and Development](03-modeling-and-development)
- [04 - Roles, Commands, and Events Mapping](04-roles-commands-events-mapping)
- [05 - Domain Driven Design Tactical design pattern guidance](05-ddd-tactical-design-pattern)
- [06 - Actual Implementation](06-actual-implementation)
- [07 - Infrastructure as Code by CDK](07-iaac-cdk)
- [08 - Deploy Serverless application](08-deploy-serverless-app)
- [09 - Deploy Containerized application](09-deploy-containerized-app)
- [10 - Build up CI/CD pipeline](10-build-up-cicd-pipeline)

# EventStorming
**Event storming is a rapid, lightweight, and underappreciated group modeling technique that is intense, fun, and useful for accelerating development teams**. It's a synthesis of facilitated group learning practices from Gamestorming and the principles of domain-driven design (DDD). The technique isn't limited to software development. Frankly speaking it is recommend to invite all the stakeholders to join the storming workshop, collect each opinions from each viewpoints.

You can apply it to practically any technical or business domain, especially those that are large, complex, or both.

## Why EventStorming?

![image](docs/img/problemsolving.png)

As the Complexity Problem domain growing, it's hard to have a fluent way to help Product Owner and Developer team to collaborate to develop a system which fulfill all of the requirements with correct understanding.

This problem is not a new story, from 2003, Eric Evans has already introduced the "Domain Driven Design" implementing approach to help developers, but it's really hard to implement from the Strategy and Tactic abstraction level.

After several years, Alberto Brandolini introduced the Event Storming approach to go through the DDD concept and make it easy to implement.


## EventStorming Terms

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

## EventStorming Benefits

When you want to divide a monolithic system into microservices, or you want to build up a new system from scratch, the most pain point is there is no idea to clarify the system boundary. Even though you interview with domain experts, and get a whole bunch of requirement documents, it still not easy to start the design. Maybe you are familiar with the classic modeling methodologies such as :

> UP/RUP, OOAD, RAD, Use Case Modeling, ICONIX Processing ...etc

No matter any one of these methodologies, all deeply depends on experts skill, but if you apply Event Storming workshop, you could leverage team's collaboration to acquire requirements with key events. However, these events are most concerned business value by stakeholders. With different color, pin, and diagrams to group the actions, events, and the aggregate context. Naturally forms up the domain boundary.

[Next: 01 Hands-on Events Exploring >](01-hands-on-events-exploring)
