package solid.humank.domain;

import solid.humank.adapter.OrderRepository;

public class DomainRegistry {

    public static OrderService orderService(){
        return new OrderService(new OrderRepository());
    }
}
