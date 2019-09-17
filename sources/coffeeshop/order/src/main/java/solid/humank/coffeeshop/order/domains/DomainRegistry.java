package solid.humank.coffeeshop.order.domains;

import solid.humank.coffeeshop.order.repositories.OrderRepository;

public class DomainRegistry {

    public static OrderService orderService(){
        return new OrderService(new OrderRepository());
    }
}
