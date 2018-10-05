# Running DDD on AWS

![image](/documents/images/coffee.jpg)

```bash
picture license free from : Pexels
https://www.pexels.com/photo/background-beverage-breakfast-brown-414645/
```

>This demo is running on AWS. By using Lambda and cloudwatch event to present how can we keep model classes at the core, and leverage the outside adapter to interact with other domain.

## Why Event Storming

As the Complexity Problem domain growing, it's hard to have a fluent way to help Product Owner and Developer team to collaborate to produce a requriments all fulfilled Service/system.

This problem is not a new story, from 2003, Eric Evans has already introduced the "Domain Driven Design" implementing approach to help developers, but it's really hard to implement from the Strategy and Tactic abstraction level.

After several years, Alberto Brandolini introduced the Event Storming approach to go through the DDD concept and make it easy to implement. 

Event storming is a rapid, lightweight, and underappreciated group modeling technique that is intense, fun, and useful for accelerating development teams. It's a synthesis of facilitated group learning practices from Gamestorming and the principles of domain-driven design (DDD). The technique isn't limited to software development. Frankly speaking it is recommend to invite all the stakeholders to join the storming workshop, collect each opinions from each viewpoints.

You can apply it to practically any technical or business domain, especially those that are large, complex, or both.

## What is the benefit

## How to implement DDD via Event Storming approach

## Using Lambda function as the entry point

You can easily export a lambda function to accept the incomg command, and do some stuff.

## Using CloudWatch Event as the integration Event

If cross boundary event did occured in current domain, never call other domain service directly, just publish a cross-domain-event. On AWS, the most appropriate one is using CloudWatch Event, it's a near-real-time event, high performance and scalable.

## Using DynamoDB as the Write Model/ Read Model persistent Repository

Once capture Model with Domain Experts, you can design Write Model first, and create Query usage Read Model.

## TODO

* Add installation instructions for the application
* Using AWS SDK for Java v2 to have better performance
* Put on X-ray inspection diagram
* Try to use Dagger2 as the DI framework to have better cold start time
