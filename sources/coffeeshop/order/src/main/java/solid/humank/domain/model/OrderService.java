package solid.humank.domain.model;

import solid.humank.port.adapter.OrderDTO;
import solid.humank.port.adapter.persistence.repository.OrderRepository;
import solid.humank.port.adapter.event.OrderEstablishedEvent;

public class OrderService {

    // Domain Service有個責任，把跨 layer傳入的DTO 在這裡翻譯成領域物件

    private OrderRepository repository;


    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    public Order establishOrder(OrderDTO orderDTO) {

        Order purchaseOrder = Order.create(orderDTO);
        Order order = repository.saveOrder(purchaseOrder);

        DomainEventPublisher.publish(new OrderEstablishedEvent(order));

        return order;

    }

}
