# Implementing Domain Driven Design (DDD) on AWS

![image](docs/img/coffee.jpg)
_Picture license-free from [Pexels](https://www.pexels.com/photo/background-beverage-breakfast-brown-414645/)_

Building software is hard. Understanding the business needs of the software is even harder. In almost every software development project, there will always be some form of gap between the requirements of the business users and the actual implementation.

As a developer, knowing how to narrow this gap can help you go a long way to building applications that are relevant for the users. Using a Domain Driven Design approach, delivered via Event Storming, it can help to reduce the time it takes for everyone in the project team to understand a business domain model.

## Table of Contents
- [00 - Event Storming](#eventstorming)
  - [What is Event Storming?](#what-is-event-storming)
  - [Whom is it for?](#whom-is-it-for)
  - [Event Storming Terms](#event-storming-terms)
  - [Event Storming Benefits](#event-storming-benefits)
  - [Event Storming Outcomes](#event-storming-outcomes)
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

# Event Storming
![image](docs/img/problemsolving.png)

## What is Event Storming?
Event Storming is a **rapid**, **lightweight**, and often under-appreciated group modeling technique that is **intense**, **fun**, and **useful** to **accelerate** project teams. It is typically offered as an interactive **workshop** and it is a synthesis of facilitated group learning practices from Gamestorming, leveraging on the principles of Domain Driven Design (DDD).

You can apply it practically on any technical or business domain, especially those that are large, complex, or both.

## Whom is it for?
Event Storming isn't limited to just for the software development team. In fact, it is recommend to invite all the stakeholders, such as developers, domain experts, business decision makers etc to join the Event Storming workshop to collect viewpoints from each participants.

## Event Storming Terms

![Event Storming](https://storage.googleapis.com/xebia-blog/1/2018/10/From-EventStorming-to-CoDDDing-New-frame-3.jpg)

> Reference from Kenny Bass - https://storage.googleapis.com/xebia-blog/1/2018/10/From-EventStorming-to-CoDDDing-New-frame-3.jpg

Take a look on this diagram, there are a few colored sticky notes with different intention:

* **Domain Events** (Orange sticky note)
  * **Event**, Stands for the fact happened in specific business context
* **Actions** (Blue sticky note)   
  * **Command**, It is a request or intention, raised by a role or time or external system
* **Information** (Green sticky note)  
  * **View Model**, it's a supporting information to help role to make a decision to raise a command
* **Consistent Business Rules** (Yellow sticky note)
  * **Aggregate**
    * Has the responsibility to accept or fulfill the intention of command
    * Represent a specific business capability to support command
    * Shouldn't naming it with role concept, or you will get only the "actor" in the whole business.
    * Should be in small scope
    * Protects business invariants
    * Exposes by ID
    * And communicated by eventual consistency
* **Eventual Consistent Business rules** (Lilac sticky note)
  * Policy
    * It's all about rules, sometimes the rules comes from external regulation restriction, or maybe account login success/fail process logic.
    * example : when login attemptation fail times up to 3, then lock this account

## Event Storming Benefits

Business requirements can be very complex. It is often hard to find a fluent way to help the Product Owner and Development teams to collaborate effectively. Event storming is designed to be **efficient** and **fun**. By bringing key stakeholder into the same room, the process becomes:

- **Efficient:** Everyone coming together in the same room can make decisions and sort out differences quickly. To create a comprehensive business domain model, what used to take many weeks of email, phone call or meeting exchanges can be reduced to a single workshop.

- **Simple:** Event Storming encourages the use of "Ubiquitous language" that both the technical and non-technical stakeholders can understand.

- **Fun:** Domain modeling is fun! Stakeholders get hands-on experience to domain modeling which everyone can participate and interact with each other. It also provides more opportunities to exchange ideas and improve mindsharing, from various perspective across multiple roles.

- **Effective:** Stakeholders are encouraged not to think about the data model, but about the business domain. This puts customers first and working backwards from there, achieves an outcome that is more relevant.

- **Insightful:** Event Storming generate conversations. This helps stakeholders to understand the entire business process better and help to have a more holistic view from various perspective.

## Event Storming Applications

When you want to divide a monolithic system into microservices, or you want to build up a new system from scratch, the most pain point is there is no idea to clarify the system boundary. Even though you interview with domain experts, and get a whole bunch of requirement documents, it still not easy to start the design. Maybe you are familiar with the classic modeling methodologies such as :

> UP/RUP, OOAD, RAD, Use Case Modeling, ICONIX Processing ...etc

No matter any one of these methodologies, all deeply depends on experts skill, but if you apply Event Storming workshop, you could leverage team's collaboration to acquire requirements with key events. However, these events are most concerned business value by stakeholders. With different color, pin, and diagrams to group the actions, events, and the aggregate context. Naturally forms up the domain boundary.

[Next: 01 Hands-on Events Exploring >](docs/01-hands-on-events-exploring)
