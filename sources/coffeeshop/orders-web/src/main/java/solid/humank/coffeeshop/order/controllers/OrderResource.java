package solid.humank.coffeeshop.order.controllers;

import solid.humank.coffeeshop.order.applications.CreateOrderSvc;
import solid.humank.coffeeshop.order.datacontracts.messages.CreateOrderMsg;
import solid.humank.coffeeshop.order.datacontracts.results.OrderItemRst;
import solid.humank.coffeeshop.order.datacontracts.results.OrderRst;
import solid.humank.coffeeshop.order.exceptions.AggregateException;
import solid.humank.coffeeshop.order.models.requests.AddOrderReq;
import solid.humank.coffeeshop.order.models.requestsmodels.OrderItemRM;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createOrder(AddOrderReq request) {

        CreateOrderMsg cmd = new CreateOrderMsg("0", this.transformToOrderItemVM(request.getItems()));
        OrderRst orderRst = null;
        try {
            orderRst = service.establishOrder(cmd);
        } catch (AggregateException e) {

        }
        return Response.ok(orderRst).build();
    }

    private List<OrderItemRst> transformToOrderItemVM(List<OrderItemRM> items) {
        List<OrderItemRst> result = new ArrayList<>();
        items.forEach(orderItemRM -> {
            result.add(new OrderItemRst(orderItemRM.getProductId(), orderItemRM.getQty(), orderItemRM.getPrice()));
        });

        return result;
    }
}
