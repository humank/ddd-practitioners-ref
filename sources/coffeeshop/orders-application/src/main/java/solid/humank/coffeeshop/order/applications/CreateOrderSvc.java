package solid.humank.coffeeshop.order.applications;

import solid.humank.coffeeshop.order.commands.CreateOrder;
import solid.humank.coffeeshop.order.datacontracts.messages.CreateOrderMsg;
import solid.humank.coffeeshop.order.datacontracts.results.OrderItemRst;
import solid.humank.coffeeshop.order.datacontracts.results.OrderRst;
import solid.humank.coffeeshop.order.domainevents.OrderCreated;
import solid.humank.coffeeshop.order.domainservices.DomainEventPublisher;
import solid.humank.coffeeshop.order.exceptions.AggregateException;
import solid.humank.coffeeshop.order.models.Order;
import solid.humank.coffeeshop.order.models.OrderId;
import solid.humank.coffeeshop.order.models.OrderItem;
import solid.humank.coffeeshop.order.models.OrderStatus;
import solid.humank.coffeeshop.order.repositories.OrderRepository;
import solid.humank.ddd.commons.interfaces.ITranslator;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;


@ApplicationScoped
public class CreateOrderSvc implements Serializable {

    public CreateOrderSvc() {
    }

    @Inject
    public OrderRepository repository;

    @Inject
    public ITranslator<List<OrderItem>, List<OrderItemRst>> translator;

    @Inject
    DomainEventPublisher domainEventPublisher;

    public CreateOrderSvc(OrderRepository repository, ITranslator<List<OrderItemRst>, List<OrderItem>> itemsTranslator) {
        this.repository = repository;
    }

    public OrderRst establishOrder(CreateOrderMsg request) throws AggregateException {

        OrderId id = this.repository.generateOrderId();
        List<OrderItem> items = translator.translate(request.getItems());
        CreateOrder cmd = new CreateOrder(id, request.getTableNo(), OrderStatus.INITIAL, items);
        Order createdOrder = Order.create(cmd);
        this.repository.save(createdOrder);

        domainEventPublisher.publish(new OrderCreated<>(id, request.getTableNo(), items, createdOrder.getCreatedDate()));

        return new OrderRst(createdOrder);

    }
}
