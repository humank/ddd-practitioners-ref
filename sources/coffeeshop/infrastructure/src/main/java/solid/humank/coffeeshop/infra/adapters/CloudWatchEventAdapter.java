package solid.humank.coffeeshop.infra.adapters;


import com.amazonaws.services.cloudwatchevents.AmazonCloudWatchEvents;
import com.amazonaws.services.cloudwatchevents.AmazonCloudWatchEventsClientBuilder;
import com.amazonaws.services.cloudwatchevents.model.PutEventsRequest;
import com.amazonaws.services.cloudwatchevents.model.PutEventsRequestEntry;
import com.amazonaws.services.cloudwatchevents.model.PutEventsResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import solid.humank.ddd.commons.baseclasses.DomainEvent;

import javax.enterprise.context.RequestScoped;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;

@RequestScoped
public class CloudWatchEventAdapter {

    Logger logger = LoggerFactory.getLogger(CloudWatchEventAdapter.class);

    String propFileName = "cloudwatchevents.properties";

    public PublishResult publishEvent(DomainEvent occurredEvent) {
        String eventJson = null;
        try {
            eventJson = new ObjectMapper().writeValueAsString(occurredEvent);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        if (eventJson == null) {
            return new PublishResult("Malformed format of Event");
        }
        return putEvent(eventJson);
    }

    private PublishResult putEvent(String eventContent) {

        AmazonCloudWatchEvents cwe =
                AmazonCloudWatchEventsClientBuilder.defaultClient();
        try {
            Properties cweProp = getCWEParameters();

            PutEventsRequestEntry request_entry = new PutEventsRequestEntry()
                    .withTime(new Date())
                    .withDetail(eventContent)
                    .withDetailType(cweProp.getProperty("EVENT_TYPE"))
                    .withResources(cweProp.getProperty("RESOURCE_ARN"))
                    .withSource(cweProp.getProperty("EVENT_SOURCE"));

            PutEventsRequest request = new PutEventsRequest()
                    .withEntries(request_entry);

            PutEventsResult response = cwe.putEvents(request);

            logger.info(response.getEntries().get(0).getEventId());

            return new PublishResult(response.getSdkResponseMetadata().toString());

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
