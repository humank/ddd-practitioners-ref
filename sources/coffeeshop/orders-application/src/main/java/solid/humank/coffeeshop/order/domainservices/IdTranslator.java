package solid.humank.coffeeshop.order.domainservices;

import solid.humank.coffeeshop.order.models.OrderId;
import solid.humank.ddd.commons.interfaces.ITranslator;

public class IdTranslator implements ITranslator<OrderId, String> {
    @Override
    public String translate(OrderId transRequest) {
        return null;
    }
}
