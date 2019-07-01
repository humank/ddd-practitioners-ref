package solid.humank.port.adapter;

import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
public class OrderCreatedEvent {

    private String version;
    private String id;
    private String detailType;
    private String source;
    private String account;
    private Date time;
    private String region;
    private List<String> resources;
    private Map detail;
}
