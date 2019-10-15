package solid.humank.coffeeshop.order.models;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Data;
import lombok.EqualsAndHashCode;
import solid.humank.ddd.commons.baseclasses.ValueObject;

import java.math.BigDecimal;

@RegisterForReflection
@Data
@EqualsAndHashCode(callSuper = false)

public class OrderItem extends ValueObject<OrderItem> {

    String productId;
    int qty;
    BigDecimal price;

    public BigDecimal fee() {
        return this.price.multiply(BigDecimal.valueOf(qty));
    }

    public OrderItem() {
    }

    public OrderItem(String productId, int qty, BigDecimal prices) {
        this.productId = productId;
        this.qty = qty;
        this.price = prices;
    }

}
