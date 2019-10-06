package solid.humank.coffeeshop.order.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import lombok.Getter;
import solid.humank.ddd.commons.baseclasses.EntityId;

import java.time.OffsetDateTime;

public class OrderId extends EntityId {

    @DynamoDBAttribute(attributeName="abbr")
    @Getter
    final String abbr = "ord";

    public OrderId() {

    }

    public OrderId(long seqNo, OffsetDateTime createdDate) {
        super(seqNo, createdDate);

    }
}
