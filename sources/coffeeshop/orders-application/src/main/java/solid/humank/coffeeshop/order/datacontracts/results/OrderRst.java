package solid.humank.coffeeshop.order.datacontracts.results;

import lombok.Data;
import lombok.EqualsAndHashCode;
import solid.humank.coffeeshop.order.models.Order;
import solid.humank.coffeeshop.order.models.OrderStatus;

import java.time.OffsetDateTime;
import java.util.List;

@Data

public class OrderRst{

    private List<OrderItemRst> items;
    private int status;
    private String id;
    private OffsetDateTime createdDate;
    private OffsetDateTime modifiedDate;

    public OrderRst() {
    }

    public OrderRst(String id, OrderStatus status, List<OrderItemRst> items, OffsetDateTime createDate, OffsetDateTime modifiedDate) {

        this.id = id;
        this.status = status.getValue();
        this.items = items;
        this.createdDate = createDate;
        this.modifiedDate = modifiedDate;
    }

    public OrderRst(Order order) {

        this.id = order.getId().toString();
        this.status = order.getStatus().getValue();
        order.getOrderItems().stream().forEach(orderItem -> {
            orderItem.toString();
            items.add(new OrderItemRst(orderItem));
        });
        this.createdDate = order.getCreatedDate();
        this.modifiedDate = order.getModifiedDate();
    }
}
