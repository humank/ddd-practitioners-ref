package solid.humank.events;

import lombok.Data;
import solid.humank.domains.Order;

@Data
public class OrderEstablishedEvent implements DomainEvent {

    private Order establishedOrder;

    public OrderEstablishedEvent(Order order) {
        this.establishedOrder = order;
    }

    @Override
    public String getEventContent() {
        return establishedOrder.toString();
    }
}
