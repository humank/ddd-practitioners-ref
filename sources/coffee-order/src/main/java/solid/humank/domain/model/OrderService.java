package solid.humank.domain.model;

import org.joda.time.LocalDateTime;
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

        //把 orderDTO 轉為 Order
        Order purchaseOrder = new Order();
        purchaseOrder.setDrinkHere(orderDTO.isDrinkHere());
        purchaseOrder.setItemName(orderDTO.getItemName());
        purchaseOrder.setPrice(orderDTO.getPrice());
        purchaseOrder.setQuantity(orderDTO.getQuantity());
        purchaseOrder.setSeatNo(orderDTO.getSeatNo());

        purchaseOrder.setEstablishTime(new LocalDateTime().toString("yyyy-MM-dd:HH:mm:ss"));
        purchaseOrder.serveConfirm();
        //同時要寫入repository, 也要發布event

        Order order = repository.saveOrder(purchaseOrder);

        DomainEventPublisher.publish(new OrderEstablishedEvent(order));

        return order;

    }
}
