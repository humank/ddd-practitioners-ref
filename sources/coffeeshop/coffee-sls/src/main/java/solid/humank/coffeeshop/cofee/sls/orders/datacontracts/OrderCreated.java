package solid.humank.coffeeshop.cofee.sls.orders.datacontracts;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import solid.humank.coffeeshop.infra.serializations.EntityId;

import java.time.OffsetDateTime;
import java.util.List;


@Data
@NoArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@EqualsAndHashCode(callSuper = false)
public class OrderCreated {

    String eventId;
    OffsetDateTime occurredDate;
    EntityId entityId;
    String tableNo;
    List<OrderItem> orderItems;
    OffsetDateTime createdDate;


}
