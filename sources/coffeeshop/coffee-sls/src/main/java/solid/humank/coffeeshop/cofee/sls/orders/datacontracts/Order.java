package solid.humank.coffeeshop.cofee.sls.orders.datacontracts;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

public class Order {

    @Getter
    @Setter(AccessLevel.PRIVATE)
    private String tableNo;


    @Getter
    @Setter(AccessLevel.PRIVATE)
    private OrderStatus status;


    @Getter
    @Setter(AccessLevel.PRIVATE)
    private List<OrderItem> orderItems;


    public BigDecimal totalFee() {
        return this.getOrderItems()
                .stream()
                .map(orderItem -> orderItem.fee())
                .reduce(BigDecimal.ZERO, (b1, b2) -> b1.add(b2));
    }


    @Getter
    @Setter(AccessLevel.PRIVATE)
    private String createdDate;


    @Getter
    @Setter(AccessLevel.PRIVATE)
    private String modifiedDate;

}
