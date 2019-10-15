package solid.humank.coffeeshop.order.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.*;
import solid.humank.ddd.commons.baseclasses.ValueObject;

import java.math.BigDecimal;

@RegisterForReflection
@Data
@EqualsAndHashCode(callSuper = false)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class OrderItem extends ValueObject<OrderItem> {

    @Getter
    @Setter(AccessLevel.PRIVATE)
    String productId;

    @Getter
    @Setter(AccessLevel.PRIVATE)
    int qty;

    @Getter
    @Setter(AccessLevel.PRIVATE)
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
