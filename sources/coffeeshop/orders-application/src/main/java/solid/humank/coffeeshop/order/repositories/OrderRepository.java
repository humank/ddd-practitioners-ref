package solid.humank.coffeeshop.order.repositories;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;
import solid.humank.coffeeshop.order.models.Order;
import solid.humank.coffeeshop.order.models.OrderId;

import javax.enterprise.context.Dependent;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.HashMap;

@Dependent
//public class OrderRepository implements IOrderRepository {
public class OrderRepository implements Serializable {
    final private String tableName = "Order";
    DynamoDbClient ddb;


    //private IRepository<Order, OrderId> repository;

    public OrderRepository() {
        ddb = DynamoDbClient.create();

    }

//    public OrderRepository(IRepository<Order, OrderId> repository) {
//        this.repository = repository;
//    }
//
//
//    public List<Order> get(Specification<Order> specification, int pageNo, int pageSize) {
//        return null;
//    }
//
//
//    public OrderId generateOrderId() {
//        return new OrderId(this.repository.count(), OffsetDateTime.now());
//    }
//
//
//    public Order getBy(OrderId id) {
//        return this.repository.get(id);
//    }


    public Order save(Order order) {
        //return this.repository.create(order);

        //DDB的實作如下

        ObjectMapper mapper = new ObjectMapper();
        String orderItemsJson = "default-json";
        try {
            orderItemsJson = mapper.writeValueAsString(order.getOrderItems());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        HashMap<String, AttributeValue> item_values = new HashMap<String, AttributeValue>();

        item_values.put("seqNo", AttributeValue.builder().n(String.valueOf(order.getId().getSeqNo())).build());
        item_values.put("tableNo", AttributeValue.builder().n(String.valueOf(order.getId().getSeqNo())).build());
        item_values.put("orderStatus", AttributeValue.builder().n(String.valueOf(order.getStatus().getValue())).build());
        item_values.put("items", AttributeValue.builder().s(orderItemsJson).build());
        item_values.put("totalFee", AttributeValue.builder().n(String.valueOf(order.totalFee())).build());
        item_values.put("createDate", AttributeValue.builder().s(order.createdDateString()).build());
        item_values.put("modifyDate", AttributeValue.builder().s(order.modifiedDateString()).build());

        DynamoDbClient ddb = DynamoDbClient.create();
        PutItemRequest request = PutItemRequest.builder()
                .tableName(tableName)
                .item(item_values)
                .build();
        ddb.putItem(request);

        return order;
    }

    public OrderId generateOrderId() {

        // Not sure if the scan will cost much. Take an alternative way as query select count

        ScanRequest scanRequest = ScanRequest.builder()
                .tableName(tableName)
                .select(Select.COUNT)
                .build();

        ScanResponse scanResponse = ddb.scan(scanRequest);
        long currentCount = scanResponse.count();

        return new OrderId(currentCount + 1, OffsetDateTime.now());
    }
}
