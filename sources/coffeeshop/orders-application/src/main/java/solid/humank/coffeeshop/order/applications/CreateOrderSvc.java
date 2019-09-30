package solid.humank.coffeeshop.order.applications;

import solid.humank.coffeeshop.order.commands.CreateOrder;
import solid.humank.coffeeshop.order.datacontracts.messages.CreateOrderMsg;
import solid.humank.coffeeshop.order.datacontracts.results.OrderItemRst;
import solid.humank.coffeeshop.order.datacontracts.results.OrderRst;
import solid.humank.coffeeshop.order.domainevents.OrderCreated;
import solid.humank.coffeeshop.order.domainservices.DomainEventPublisher;
import solid.humank.coffeeshop.order.models.Order;
import solid.humank.coffeeshop.order.models.OrderId;
import solid.humank.coffeeshop.order.models.OrderItem;
import solid.humank.coffeeshop.order.models.OrderStatus;
import solid.humank.coffeeshop.order.repositories.OrderRepository;
import solid.humank.ddd.commons.interfaces.ITranslator;

import javax.enterprise.context.Dependent;
import java.util.List;
@Dependent
public class CreateOrderSvc {

    public CreateOrderSvc(){}

    // Domain Service有個責任，把跨 layer傳入的DTO 在這裡翻譯成領域物件

    private OrderRepository repository;

    private ITranslator<List<OrderItemRst>, List<OrderItem>> itemsTranslator;

    public CreateOrderSvc(OrderRepository repository, ITranslator<List<OrderItemRst>, List<OrderItem>> itemsTranslator) {
        this.repository = repository;
        this.itemsTranslator = itemsTranslator;
    }

    public CreateOrderSvc(OrderRepository orderRepository) {
        this.repository = orderRepository;
    }

    public OrderRst establishOrder(CreateOrderMsg request) {

        OrderId id = this.repository.generateOrderId();
        List<OrderItem> items = this.itemsTranslator.translate(request.getItems());
        CreateOrder cmd = new CreateOrder(id, request.getTableNo(), OrderStatus.INITIAL, items);
        Order createdOrder = Order.create(cmd);
        this.repository.save(createdOrder);

        DomainEventPublisher.publish(new OrderCreated<>(id, request.getTableNo(), items, createdOrder.getCreatedDate()));

        return new OrderRst(createdOrder);

    }
}
