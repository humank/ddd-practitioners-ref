package solid.humank.adapters;

import com.amazonaws.services.cloudwatchevents.AmazonCloudWatchEvents;
import com.amazonaws.services.cloudwatchevents.AmazonCloudWatchEventsClient;
import com.amazonaws.services.cloudwatchevents.AmazonCloudWatchEventsClientBuilder;
import com.amazonaws.services.cloudwatchevents.model.PutEventsRequest;
import com.amazonaws.services.cloudwatchevents.model.PutEventsRequestEntry;
import com.amazonaws.services.cloudwatchevents.model.PutEventsResult;

public class CloudwatchEventAdapter {

    final AmazonCloudWatchEvents cwe =
            AmazonCloudWatchEventsClientBuilder.defaultClient();

    final String RESOURCE_ARN = "arn:aws:events:ap-northeast-1:584518143473:rule/hellocwe";

    public String putEvent(String eventContent, String eventType, String packageName){


        PutEventsRequestEntry request_entry = new PutEventsRequestEntry()
                .withDetail(eventContent)
                .withDetailType("customeevent")
                .withResources(RESOURCE_ARN)
                .withSource("solid.humank.eventstormingddd");

        PutEventsRequest request = new PutEventsRequest()
                .withEntries(request_entry);

        PutEventsResult response = cwe.putEvents(request);

        return response.getSdkResponseMetadata().toString();
    }
}
