package solid.humank.coffeeshop.cofee.sls.orders.datacontracts;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class OrderCreated{

    String abbr;
    long seqNo;
    String createdDate;
    String occurredDate;
    String tableNo;
    List<OrderItem> orderItems;


}
