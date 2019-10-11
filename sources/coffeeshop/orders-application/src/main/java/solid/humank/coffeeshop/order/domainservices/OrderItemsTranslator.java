package solid.humank.coffeeshop.order.domainservices;

import solid.humank.coffeeshop.order.datacontracts.results.OrderItemRst;
import solid.humank.coffeeshop.order.models.OrderItem;
import solid.humank.ddd.commons.interfaces.ITranslator;

import java.util.ArrayList;
import java.util.List;

public class OrderItemsTranslator implements ITranslator<OrderItem, OrderItemRst> {

    @Override
    public List<OrderItem> translate(List<OrderItemRst> transRequest) {

        List<OrderItem> orderItemList = new ArrayList<>();

        for (OrderItemRst orderItemRst : transRequest) {

            orderItemList.add(
                    new OrderItem(orderItemRst.getProductId(),




                            orderItemRst.getQty(),
                            orderItemRst.getPrice())
            );
        }

        return orderItemList;
    }
}
