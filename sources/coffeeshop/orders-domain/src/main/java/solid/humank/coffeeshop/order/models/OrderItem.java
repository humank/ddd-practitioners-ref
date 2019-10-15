package solid.humank.coffeeshop.order.models;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import solid.humank.ddd.commons.baseclasses.ValueObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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

    public BigDecimal fee(){
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
