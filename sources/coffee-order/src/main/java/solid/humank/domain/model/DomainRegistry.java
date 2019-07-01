package solid.humank.domain.model;

import solid.humank.port.adapter.persistence.repository.OrderRepository;

public class DomainRegistry {

    public static OrderService orderService(){
        return new OrderService(new OrderRepository());
    }
}
