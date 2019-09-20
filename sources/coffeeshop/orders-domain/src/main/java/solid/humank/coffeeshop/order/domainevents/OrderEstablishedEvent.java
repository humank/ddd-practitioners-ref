package solid.humank.coffeeshop.order.domainevents;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import solid.humank.coffeeshop.order.models.Order;
import solid.humank.ddd.commons.baseclasses.DomainEvent;
import solid.humank.ddd.commons.baseclasses.EntityId;

import java.util.Optional;

@Data
public class OrderEstablishedEvent extends DomainEvent {

    private Order establishedOrder;

    public OrderEstablishedEvent(Order order) {
        this.establishedOrder = order;
    }

    public String getEventContent() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(establishedOrder);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "{\"error\":\"Data error\"}";
    }

    @Override
    protected DomainEvent raise(EntityId entityId, Optional occurredDate) {
        return null;
    }

    @Override
    protected Iterable<Object> getEqualityComponents() {
        return null;
    }
}
