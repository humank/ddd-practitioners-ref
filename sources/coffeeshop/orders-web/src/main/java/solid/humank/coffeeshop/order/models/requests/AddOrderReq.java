package solid.humank.coffeeshop.order.models.requests;

import lombok.Data;
import solid.humank.coffeeshop.order.models.requestsmodels.OrderItemRM;

import java.util.List;

@Data
public class AddOrderReq {

    List<OrderItemRM> items;
}
