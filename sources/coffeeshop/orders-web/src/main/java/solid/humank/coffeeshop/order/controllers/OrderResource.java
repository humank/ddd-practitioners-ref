package solid.humank.coffeeshop.order.controllers;

import solid.humank.coffeeshop.order.applications.CreateOrderSvc;
import solid.humank.coffeeshop.order.datacontracts.messages.CreateOrderMsg;
import solid.humank.coffeeshop.order.datacontracts.results.OrderItemRst;
import solid.humank.coffeeshop.order.datacontracts.results.OrderRst;
import solid.humank.coffeeshop.order.models.requests.AddOrderReq;
import solid.humank.coffeeshop.order.models.requestsmodels.OrderItemRM;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("/order")
public class OrderResource {

    @Inject
    CreateOrderSvc service;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String sayHello() {
        return "hello 12000";
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public OrderRst createOrder(AddOrderReq request) {

        CreateOrderMsg cmd = new CreateOrderMsg("0", this.transformToOrderItemVM(request.getItems()));
        OrderRst orderRst = service.establishOrder(cmd);
        return orderRst;

    }

    private List<OrderItemRst> transformToOrderItemVM(List<OrderItemRM> items) {
        List<OrderItemRst> result = new ArrayList<>();
        items.forEach(orderItemRM -> {
            result.add(new OrderItemRst(orderItemRM.getProductId(), orderItemRM.getQty(), orderItemRM.getPrice()));
        });

        return result;
    }
}
