package solid.humank.coffeeshop.order.models;

import lombok.Getter;
import solid.humank.ddd.commons.baseclasses.EntityId;

import java.time.OffsetDateTime;

public class OrderId extends EntityId {

    @Getter
    final String abbr = "ord";

    public OrderId() {

    }

    public OrderId(long seqNo, OffsetDateTime createdDate) {
        super(seqNo, createdDate);

    }
}
