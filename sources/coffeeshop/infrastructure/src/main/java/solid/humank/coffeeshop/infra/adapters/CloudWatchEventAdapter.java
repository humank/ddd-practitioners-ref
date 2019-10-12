package solid.humank.coffeeshop.infra.adapters;

import software.amazon.awssdk.services.cloudwatchevents.CloudWatchEventsClient;
import software.amazon.awssdk.services.cloudwatchevents.model.PutEventsRequest;
import software.amazon.awssdk.services.cloudwatchevents.model.PutEventsRequestEntry;
import software.amazon.awssdk.services.cloudwatchevents.model.PutEventsResponse;
import solid.humank.ddd.commons.baseclasses.DomainEvent;

import javax.enterprise.context.RequestScoped;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

@RequestScoped
public class CloudWatchEventAdapter {

    String propFileName = "cloudwatchevents.properties";
    CloudWatchEventsClient cwe = CloudWatchEventsClient.builder().build();

    public CloudWatchEventAdapter() {

    }

    public PublishResult publishEvent(DomainEvent occurredEvent) {
        return putEvent(occurredEvent.toString());
    }

    private PublishResult putEvent(String eventContent) {

        try {
            Properties cweProp = getCWEParameters();

            PutEventsRequestEntry request_entry = PutEventsRequestEntry.builder()
                    .detail(eventContent)
                    .detailType(cweProp.getProperty("EVENT_TYPE"))
                    .resources(cweProp.getProperty("RESOURCE_ARN"))
                    .source(cweProp.getProperty("EVENT_SOURCE")).build();

            PutEventsRequest request = PutEventsRequest.builder()
                    .entries(request_entry).build();
            PutEventsResponse response = cwe.putEvents(request);
            return new PublishResult(response.responseMetadata().toString());

        } catch (FileNotFoundException e) {
            e.printStackTrace();

        }
        return new PublishResult("AWS Cloud Watch Events configuration is not correct. The event is not published:" + eventContent);
    }

    //TODO : Move the parameters to AWS ParameterStore

    private Properties getCWEParameters() throws FileNotFoundException {

        InputStream inputStream;
        Properties prop = new Properties();
        HashMap<String, String> map = new HashMap<String, String>();

        inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

        if (inputStream != null) {
            try {
                prop.load(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
        }

        return prop;
    }
}
