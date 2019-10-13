package solid.humank.coffeeshop.order.repositories;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import solid.humank.coffeeshop.order.models.Order;
import solid.humank.coffeeshop.order.models.OrderId;

import javax.enterprise.context.Dependent;
import java.io.Serializable;
import java.time.OffsetDateTime;

@Dependent
//public class OrderRepository implements IOrderRepository {
public class OrderRepository implements Serializable {
    final private String tableName = "Order";
    AmazonDynamoDB client;
    DynamoDB ddb;

    //private IRepository<Order, OrderId> repository;

    public OrderRepository() {
        client = AmazonDynamoDBClientBuilder.standard().build();
        ddb = new DynamoDB(client);
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
        Table table = ddb.getTable(tableName);

        ObjectMapper mapper = new ObjectMapper();
        String orderItemsJson = "default-json";
        try {
            orderItemsJson = mapper.writeValueAsString(order.getOrderItems());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        Item item = new Item()
                .withPrimaryKey("seqNo", order.getId().getSeqNo())
                .withString("tableNo", order.getTableNo())
                .withNumber("orderStatus", order.getStatus().getValue())
                .withString("items",  orderItemsJson)
                .withNumber("totalFee", order.totalFee())
                .withString("createDate", order.createdDateString())
                .withString("modifyDate", order.modifiedDateString());

        table.putItem(item);

        return order;
    }

    public OrderId generateOrderId() {
        AmazonDynamoDB cc = AmazonDynamoDBClientBuilder.standard().build();
        DynamoDB dd = new DynamoDB(cc);

        // Not sure if the scan will cost much. Take an alternative way as query select count
        ScanRequest scanRequest = new ScanRequest().withTableName(tableName);
        ScanResult scanResult = client.scan(scanRequest);
        long currentCount = scanResult.getScannedCount();
        return new OrderId(currentCount + 1, OffsetDateTime.now());
    }
}
