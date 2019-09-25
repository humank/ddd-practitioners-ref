package solid.humank.coffeeshop.order.datacontracts.results;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import solid.humank.coffeeshop.order.models.OrderItem;
import solid.humank.ddd.commons.baseclasses.PropertyComparer;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class OrderItemRst extends PropertyComparer<OrderItemRst> {

    @Getter @Setter(AccessLevel.PRIVATE)
    private  String productId;

    @Getter @Setter(AccessLevel.PRIVATE)
    private  int qty;

    @Getter @Setter(AccessLevel.PRIVATE)
    private  BigDecimal price;

    @Getter @Setter(AccessLevel.PRIVATE)
    private  BigDecimal fee;

    public OrderItemRst(String productId, int qty, BigDecimal price) {
        this.productId = productId;
        this.qty = qty;
        this.price = price;
        this.fee = this.price.multiply(BigDecimal.valueOf(qty));
    }

    public OrderItemRst(OrderItem orderItem) {
        this.productId = orderItem.getProductId();
        this.qty = orderItem.getQty();
        this.price = orderItem.getPrice();
        this.fee = orderItem.getFee();
    }

    @Override
    protected Iterable<Object> getEqualityComponents() {
        List<Object> collection = new ArrayList<>();
        collection.add(this.price);
        collection.add(qty);
        collection.add(productId);
        collection.add(fee);

        return collection;
    }
}
