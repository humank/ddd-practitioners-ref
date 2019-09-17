package solid.humank.coffeeshop.order.domains;

import com.amazonaws.services.cloudwatchevents.AmazonCloudWatchEvents;
import com.amazonaws.services.cloudwatchevents.AmazonCloudWatchEventsClientBuilder;
import solid.humank.coffeeshop.order.domainservices.CloudWatchEventAdapter;
import solid.humank.coffeeshop.order.domainservices.PublishResult;

public class DomainEventPublisher {
    final static AmazonCloudWatchEvents cwe =
            AmazonCloudWatchEventsClientBuilder.defaultClient();

    public static PublishResult publish(OrderEstablishedEvent orderEstablishedEvent) {

        CloudWatchEventAdapter cweAdapter = new CloudWatchEventAdapter(cwe);
        return cweAdapter.publishEvent(orderEstablishedEvent);


    }
}
