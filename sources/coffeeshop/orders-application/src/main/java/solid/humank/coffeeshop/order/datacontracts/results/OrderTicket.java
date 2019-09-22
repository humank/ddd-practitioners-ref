package solid.humank.coffeeshop.order.datacontracts.results;

import lombok.Data;

import java.util.UUID;

@Data
public class OrderTicket {

    private UUID uuid;
    public OrderTicket(UUID uuid) {
        this.uuid = uuid;
    }

}
