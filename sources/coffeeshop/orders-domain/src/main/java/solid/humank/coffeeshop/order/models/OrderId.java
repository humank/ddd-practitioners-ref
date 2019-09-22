package solid.humank.coffeeshop.order.models;

import solid.humank.ddd.commons.baseclasses.EntityId;

public class OrderId extends EntityId {
    @Override
    protected Iterable<Object> getEqualityComponents() {
        return null;
    }
}
