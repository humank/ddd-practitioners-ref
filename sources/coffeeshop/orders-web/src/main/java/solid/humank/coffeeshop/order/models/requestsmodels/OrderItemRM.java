package solid.humank.coffeeshop.order.models.requestsmodels;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItemRM {

    String productId;
    int qty;
    BigDecimal price;
}
