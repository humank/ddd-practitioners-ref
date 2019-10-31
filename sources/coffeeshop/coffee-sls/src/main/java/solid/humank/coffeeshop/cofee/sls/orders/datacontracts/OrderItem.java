package solid.humank.coffeeshop.cofee.sls.orders.datacontracts;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@AllArgsConstructor
@Data
public class OrderItem {

    String productId;
    int qty;
    BigDecimal price;

    public BigDecimal fee() {
        return this.price.multiply(BigDecimal.valueOf(qty));
    }


}