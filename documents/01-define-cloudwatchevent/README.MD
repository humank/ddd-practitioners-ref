# Domain Event definition

## Basic for CloudWatch Event

In this example, we leverage the AWS CloudWatch Event to publish the domain immutable fact, treat it as an domain event.

The CloudWatch Events support pretty much different event types, at first the events support AWS Service events:
[Supported AWS Service Event Tyes](https://docs.aws.amazon.com/AmazonCloudWatch/latest/events/EventTypes.html).

## Problem Domain Immutable Fact Event

But if you want to define your own problem domain event, you could define the
> CloudWatch Rule with Event Pattern

The most easy way to define the domain event in AWS CloudWatch Event is to just add CloudWatch Rule to reflect the domain immutable fact. Of course, you still can just run the following command to achieve the goal.

```bash
aws events put-rule --name "OrderCreatedEvent" --event-pattern "{\"detail-type\": [ \"customevent\" ],\"source\": [\"solid.humank.eventstormingddd\"]}"
```

output would be:

```json
{
    "RuleArn": "arn:aws:events:ap-northeast-1:<<AWS Account ID>>:rule/OrderCreatedEvent"
}
```