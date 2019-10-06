package solid.humank.coffeeshop.order.repositories;

import solid.humank.coffeeshop.order.interfaces.IOrderRepository;
import solid.humank.coffeeshop.order.models.Order;
import solid.humank.coffeeshop.order.models.OrderId;
import solid.humank.ddd.commons.baseclasses.Specification;
import solid.humank.ddd.commons.interfaces.IRepository;

import javax.enterprise.context.Dependent;
import java.time.OffsetDateTime;
import java.util.List;

@Dependent
public class OrderRepository implements IOrderRepository {

    final private String tableName = "Order";


    private IRepository<Order, OrderId> repository;

    public OrderRepository() {

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
