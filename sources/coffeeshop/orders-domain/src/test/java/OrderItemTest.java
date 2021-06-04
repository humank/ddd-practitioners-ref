import org.junit.Assert;
import org.junit.jupiter.api.Test;
import solid.humank.coffeeshop.order.models.OrderItem;

import java.math.BigDecimal;

public class OrderItemTest {
    @Test
    void valueObject_replaceable_test() {
        OrderItem orderItem;
        orderItem = new OrderItem("Test", 100, new BigDecimal(100));

        orderItem = new OrderItem("Test", 120, new BigDecimal(500));
    }
}

