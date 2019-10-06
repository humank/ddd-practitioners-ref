package solid.humank.coffeeshop.order.models.requestsmodels;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class OrderItemRM {

    String productId;
    int qty;
    BigDecimal price;
}
