package solid.humank.coffeeshop.order.models.requests;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Data;
import lombok.NoArgsConstructor;
import solid.humank.coffeeshop.order.models.requestsmodels.OrderItemRM;

import java.util.List;

@Data
@RegisterForReflection
@NoArgsConstructor
public class AddOrderReq {

    List<OrderItemRM> items;
}
