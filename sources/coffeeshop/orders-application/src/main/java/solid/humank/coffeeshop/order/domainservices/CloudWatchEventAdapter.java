package solid.humank.coffeeshop.order.domainservices;

import com.amazonaws.services.cloudwatchevents.AmazonCloudWatchEvents;
import com.amazonaws.services.cloudwatchevents.model.PutEventsRequest;
import com.amazonaws.services.cloudwatchevents.model.PutEventsRequestEntry;
import com.amazonaws.services.cloudwatchevents.model.PutEventsResult;
import solid.humank.ddd.commons.baseclasses.DomainEvent;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

public class CloudWatchEventAdapter {

    final String propFileName = "cloudwatchevents.properties";
    private AmazonCloudWatchEvents cwe;

    public CloudWatchEventAdapter(AmazonCloudWatchEvents cwe) {
        this.cwe = cwe;
    }

    public PublishResult publishEvent(DomainEvent occuredEvent) {
        return putEvent(occuredEvent.getEventContent());
    }

    private PublishResult putEvent(String eventContent) {

        try {
            Properties cweProp = getCWEParameters();
            PutEventsRequestEntry request_entry = new PutEventsRequestEntry()
                    .withDetail(eventContent)
                    .withDetailType(cweProp.getProperty("EVENT_TYPE"))
                    .withResources(cweProp.getProperty("RESOURCE_ARN"))
                    .withSource(cweProp.getProperty("EVENT_SOURCE"));

            PutEventsRequest request = new PutEventsRequest()
                    .withEntries(request_entry);

            PutEventsResult response = cwe.putEvents(request);

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
