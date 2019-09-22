package solid.humank.coffeeshop.order.models;

import solid.humank.ddd.commons.baseclasses.ValueObject;

public class OrderItem extends ValueObject<OrderItem> {

    @Override
    protected Iterable<Object> getEqualityComponents() {
        return null;
    }
}
