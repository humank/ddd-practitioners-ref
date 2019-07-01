package solid.humank.domain;

import com.amazonaws.services.cloudwatchevents.AmazonCloudWatchEvents;
import com.amazonaws.services.cloudwatchevents.AmazonCloudWatchEventsClientBuilder;
import solid.humank.adapter.CloudWatchEventAdapter;
import solid.humank.adapter.PublishResult;
import solid.humank.events.OrderEstablishedEvent;

public class DomainEventPublisher {
    final static AmazonCloudWatchEvents cwe =
            AmazonCloudWatchEventsClientBuilder.defaultClient();

    public static PublishResult publish(OrderEstablishedEvent orderEstablishedEvent) {

        CloudWatchEventAdapter cweAdapter = new CloudWatchEventAdapter(cwe);
        return cweAdapter.publishEvent(orderEstablishedEvent);


    }
}
