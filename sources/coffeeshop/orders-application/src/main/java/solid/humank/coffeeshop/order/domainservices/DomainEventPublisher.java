package solid.humank.coffeeshop.order.domainservices;

import com.amazonaws.services.cloudwatchevents.AmazonCloudWatchEvents;
import com.amazonaws.services.cloudwatchevents.AmazonCloudWatchEventsClientBuilder;
import solid.humank.coffeeshop.order.domainevents.OrderCreated;

public class DomainEventPublisher {
    final static AmazonCloudWatchEvents cwe =
            AmazonCloudWatchEventsClientBuilder.defaultClient();

    public static PublishResult publish(OrderCreated orderCreated) {

        CloudWatchEventAdapter cweAdapter = new CloudWatchEventAdapter(cwe);
        return cweAdapter.publishEvent(orderCreated);


    }
}
