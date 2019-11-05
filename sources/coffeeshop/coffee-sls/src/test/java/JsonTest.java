import org.junit.jupiter.api.Test;
import solid.humank.coffeeshop.cofee.sls.orders.datacontracts.OrderCreated;
import solid.humank.ddd.commons.utilities.DomainModelMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {

    @Test
    public void test() {
        String data="{\"eventId\":\"e0c00a3d-9834-42fd-a3b0-b4214be31254\",\"occurredDate\":1572865608.044000000,\"entityId\":{\"abbr\":\"ord\",\"seqNo\":90,\"occurredDate\":1572865608.031000000},\"tableNo\":\"0\",\"orderItems\":[{\"productId\":\"123\",\"qty\":2,\"price\":200}],\"createdDate\":1572865608.039000000}";

        DomainModelMapper mapper = new DomainModelMapper();
        OrderCreated created = mapper.readValue(data, OrderCreated.class);
        assertEquals(data, mapper.writeToJsonString(created) );
    }


}
