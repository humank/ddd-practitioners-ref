package solid.humank.coffeeshop.cofee.sls.orders;

import lombok.Data;
import lombok.EqualsAndHashCode;
import solid.humank.ddd.commons.baseclasses.DomainEvent;
import solid.humank.ddd.commons.baseclasses.EntityId;

import java.time.OffsetDateTime;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class OrderCreated<OrderId extends EntityId> extends DomainEvent<OrderId> {

    OrderId orderId;
    String tableNo;
    List<OrderItem> orderItems;
    OffsetDateTime createdDate;

    public OrderCreated(OrderId orderId, String tableNo, List<OrderItem> orderItems, OffsetDateTime createdDate) {
        this.orderId = orderId;
        this.tableNo = tableNo;
        this.orderItems = orderItems;
        this.createdDate = createdDate;

    }


}
