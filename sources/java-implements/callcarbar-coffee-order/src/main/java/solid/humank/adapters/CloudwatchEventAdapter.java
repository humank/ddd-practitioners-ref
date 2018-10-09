package solid.humank.adapters;

import com.amazonaws.services.cloudwatchevents.AmazonCloudWatchEvents;
import com.amazonaws.services.cloudwatchevents.model.PutEventsRequest;
import com.amazonaws.services.cloudwatchevents.model.PutEventsRequestEntry;
import com.amazonaws.services.cloudwatchevents.model.PutEventsResult;
import solid.humank.events.DomainEvent;

public class CloudwatchEventAdapter {

    final String EVENT_TYPE = "customevent";
    final String EVENT_SOURCE = "solid.humank.eventstormingddd";
    final String RESOURCE_ARN = "arn:aws:events:ap-northeast-1:584518143473:rule/OrderCreatedEvent";
    private AmazonCloudWatchEvents cwe;

    public CloudwatchEventAdapter(AmazonCloudWatchEvents cwe) {
        this.cwe = cwe;
    }

    public String publishEvent(DomainEvent occuredEvent) {
        return putEvent(occuredEvent.getEventContent());
    }

    private String putEvent(String eventContent) {

        PutEventsRequestEntry request_entry = new PutEventsRequestEntry()
                .withDetail(eventContent)
                .withDetailType(EVENT_TYPE)
                .withResources(RESOURCE_ARN)
                .withSource(EVENT_SOURCE);

        PutEventsRequest request = new PutEventsRequest()
                .withEntries(request_entry);

        PutEventsResult response = cwe.putEvents(request);

        return response.getSdkResponseMetadata().toString();
    }
}
