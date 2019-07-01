package solid.humank.domain.model;

import com.amazonaws.services.cloudwatchevents.AmazonCloudWatchEvents;
import com.amazonaws.services.cloudwatchevents.AmazonCloudWatchEventsClientBuilder;
import solid.humank.port.adapter.messaging.CloudWatchEventAdapter;
import solid.humank.port.adapter.messaging.PublishResult;
import solid.humank.port.adapter.event.OrderEstablishedEvent;

public class DomainEventPublisher {
    final static AmazonCloudWatchEvents cwe =
            AmazonCloudWatchEventsClientBuilder.defaultClient();

    public static PublishResult publish(OrderEstablishedEvent orderEstablishedEvent) {

        CloudWatchEventAdapter cweAdapter = new CloudWatchEventAdapter(cwe);
        return cweAdapter.publishEvent(orderEstablishedEvent);


    }
}
