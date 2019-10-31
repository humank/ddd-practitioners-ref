import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import solid.humank.coffeeshop.cofee.sls.orders.datacontracts.OrderCreated;

import java.io.IOException;

public class JsonTest {

    @Test
    public void test() throws IOException {
        String data="{\"orderId\":{\"abbr\":\"ord\",\"seqNo\":62,\"occurredDate\":{\"dateTime\":{\"date\":{\"year\":2019,\"month\":10,\"day\":31},\"time\":{\"hour\":10,\"minute\":51,\"second\":10,\"nano\":52000000}},\"offset\":{\"totalSeconds\":28800}}},\"tableNo\":\"0\",\"orderItems\":[{\"productId\":\"123\",\"qty\":2,\"price\":200}],\"createdDate\":{\"dateTime\":{\"date\":{\"year\":2019,\"month\":10,\"day\":31},\"time\":{\"hour\":10,\"minute\":51,\"second\":10,\"nano\":64000000}},\"offset\":{\"totalSeconds\":28800}},\"eventId\":\"ba3d8335-f4f0-4e1a-aec7-102ecf9c96ab\",\"occurredDate\":{\"dateTime\":{\"date\":{\"year\":2019,\"month\":10,\"day\":31},\"time\":{\"hour\":10,\"minute\":51,\"second\":10,\"nano\":73000000}},\"offset\":{\"totalSeconds\":28800}}}";

        //TODO : 應該必須在這個sls 當中直接處理掉翻譯的事情

        ObjectMapper mapper = new ObjectMapper();
        OrderCreated created = mapper.readValue(data, OrderCreated.class);
    }
}
