package solid.humank.coffeeshop.order.repositories;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;
import solid.humank.coffeeshop.order.interfaces.IOrderRepository;
import solid.humank.coffeeshop.order.models.Order;
import solid.humank.coffeeshop.order.models.OrderId;
import solid.humank.ddd.commons.baseclasses.Specification;
import solid.humank.ddd.commons.interfaces.IRepository;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.List;

@Dependent
public class OrderRepository implements IOrderRepository {

    @Inject
    IRepository<Order, OrderId> repository;

    final private String tableName = "Order";
    DynamoDbClient ddb;

    Logger logger = LoggerFactory.getLogger(OrderRepository.class);

    public OrderRepository() {
        ddb = DynamoDbClient.create();
    }

    public OrderRepository(IRepository<Order, OrderId> repository) {
        this.repository = repository;
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
        Gson gson = new GsonBuilder().create();

        String orderItemsJson = gson.toJson(order.getOrderItems());

        logger.info("orderItemsJson is:" + orderItemsJson);
        HashMap<String, AttributeValue> item_values = new HashMap<String, AttributeValue>();

        item_values.put("seqNo", AttributeValue.builder().n(String.valueOf(order.getId().getSeqNo())).build());
        item_values.put("tableNo", AttributeValue.builder().n(order.getTableNo()).build());
        item_values.put("orderStatus", AttributeValue.builder().n(String.valueOf(order.getStatus().getValue())).build());
        item_values.put("items", AttributeValue.builder().s(orderItemsJson).build());

        //item_values.put("items", AttributeValue.builder().s("[{\"productId\":\"123\",\"qty\":2,\"price\":200}]").build());
        logger.info("item_values.get(\"items\") is : " + item_values.get("items"));

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

    @Override
    public List<Order> get(Specification<Order> specification, int pageNo, int pageSize) {
        return null;
    }

    public OrderId generateOrderId() {

        return new OrderId(this.repository.count(),OffsetDateTime.now());

        // Not sure if the scan will cost much. Take an alternative way as query select count

        ScanRequest scanRequest = ScanRequest.builder()
                .tableName(tableName)
                .select(Select.COUNT)
                .build();

        ScanResponse scanResponse = ddb.scan(scanRequest);
        long currentCount = scanResponse.count();

        return new OrderId(currentCount + 1, OffsetDateTime.now());
    }

    @Override
    public Order getBy(OrderId id) {
        return null;
    }
}
