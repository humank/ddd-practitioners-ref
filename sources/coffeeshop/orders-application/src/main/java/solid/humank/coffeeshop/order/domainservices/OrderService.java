package solid.humank.coffeeshop.order.domainservices;

import solid.humank.coffeeshop.order.domainevents.OrderEstablishedEvent;
import solid.humank.coffeeshop.order.models.Order;
import solid.humank.coffeeshop.order.commands.CreateOrder;
import solid.humank.coffeeshop.order.repositories.OrderRepository;

public class OrderService {

    // Domain Service有個責任，把跨 layer傳入的DTO 在這裡翻譯成領域物件

    private OrderRepository repository;


    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    public Order establishOrder(CreateOrder createOrder) {

        Order purchaseOrder = Order.create(createOrder);
        Order order = repository.saveOrder(purchaseOrder);

        DomainEventPublisher.publish(new OrderEstablishedEvent(order));

        return order;

    }

}
