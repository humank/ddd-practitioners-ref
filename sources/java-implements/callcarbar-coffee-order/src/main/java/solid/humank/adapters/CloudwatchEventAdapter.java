package solid.humank.adapters;

import com.amazonaws.services.cloudwatchevents.AmazonCloudWatchEvents;
import com.amazonaws.services.cloudwatchevents.model.PutEventsRequest;
import com.amazonaws.services.cloudwatchevents.model.PutEventsRequestEntry;
import com.amazonaws.services.cloudwatchevents.model.PutEventsResult;
import solid.humank.events.DomainEvent;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class CloudwatchEventAdapter {

    final String propFileName = "cloudwatchevents.properties";
    private AmazonCloudWatchEvents cwe;

    public CloudwatchEventAdapter(AmazonCloudWatchEvents cwe) {
        this.cwe = cwe;
    }

    public String publishEvent(DomainEvent occuredEvent) {
        return putEvent(occuredEvent.getEventContent());
    }

    private String putEvent(String eventContent) {

        HashMap<String, String> cweMap = null;

        try {
            cweMap = getCWEParameters();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return "AWS Cloud Watch Events configuration is not correct. The event is not published:" + eventContent;
        }


        PutEventsRequestEntry request_entry = new PutEventsRequestEntry()
                .withDetail(eventContent)
                .withDetailType(cweMap.get("EVENT_TYPE"))
                .withResources(cweMap.get("RESOURCE_ARN"))
                .withSource(cweMap.get("EVENT_SOURCE"));

        PutEventsRequest request = new PutEventsRequest()
                .withEntries(request_entry);

        PutEventsResult response = cwe.putEvents(request);

        return response.getSdkResponseMetadata().toString();
    }

    //TODO : Move the parameters to AWS ParameterStore

    private HashMap<String, String> getCWEParameters() throws FileNotFoundException {

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

        return map;
    }
}
