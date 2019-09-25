package solid.humank.coffeeshop.order.repositories;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import solid.humank.coffeeshop.order.interfaces.IOrderRepository;
import solid.humank.coffeeshop.order.models.Order;
import solid.humank.coffeeshop.order.models.OrderId;
import solid.humank.ddd.commons.baseclasses.Specification;
import solid.humank.ddd.commons.interfaces.IRepository;

import java.time.OffsetDateTime;
import java.util.List;

public class OrderRepository implements IOrderRepository {

    final private String tableName = "Order";
    final AmazonDynamoDB client = AmazonDynamoDBClientBuilder.defaultClient();
    final DynamoDB ddb = new DynamoDB(client);

    private IRepository<Order, OrderId> repository;

    public OrderRepository(){

    }
    public OrderRepository(IRepository<Order, OrderId> repository) {
        this.repository = repository;
    }

    @Override
    public List<Order> get(Specification<Order> specification, int pageNo, int pageSize) {
        return null;
    }

    @Override
    public OrderId generateOrderId() {
        return new OrderId(this.repository.count(), OffsetDateTime.now());
    }

    @Override
    public Order getBy(OrderId id) {
        return this.repository.get(id);
    }

    @Override
    public Order save(Order order) {
        return this.repository.create(order);

        //DDB的實作如下
//        Table table = ddb.getTable(tableName);
//
//        Item item = new Item()
//                .withPrimaryKey("establishtime", purchasedOrder.getEstablishTime())
//                .withBoolean("drinkHere", purchasedOrder.isDrinkHere())
//                .withString("itemName", purchasedOrder.getItemName())
//                .withNumber("price", purchasedOrder.getPrice())
//                .withNumber("quantity", 2)
//                .withString("seatno", purchasedOrder.getSeatNo())
//                .withNumber("drinkTemperature", purchasedOrder.getDrinkTemperature());
//
//        table.putItem(item);

    }
}
