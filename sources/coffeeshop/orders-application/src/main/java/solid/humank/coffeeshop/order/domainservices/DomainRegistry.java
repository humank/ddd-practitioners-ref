package solid.humank.coffeeshop.order.domainservices;

import solid.humank.coffeeshop.order.applications.CreateOrderSvc;
import solid.humank.coffeeshop.order.repositories.OrderRepository;

public class DomainRegistry {

    public static CreateOrderSvc orderService(){
        return new CreateOrderSvc(new OrderRepository());
    }
}
