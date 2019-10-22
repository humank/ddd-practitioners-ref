package solid.humank.coffeeshop.order.models;

import solid.humank.ddd.commons.baseclasses.EntityId;

import javax.enterprise.context.Dependent;
import java.time.OffsetDateTime;

public class OrderId extends EntityId {

    public OrderId() {
        this.setAbbr("ord");
    }

    public OrderId(long seqNo, OffsetDateTime createdDate) {
        super(seqNo, createdDate);
        this.setAbbr("ord");
    }
}
