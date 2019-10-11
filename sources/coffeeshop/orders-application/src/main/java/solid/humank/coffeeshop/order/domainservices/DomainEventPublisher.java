package solid.humank.coffeeshop.order.domainservices;

import solid.humank.coffeeshop.infra.adapters.CloudWatchEventAdapter;
import solid.humank.coffeeshop.infra.adapters.PublishResult;
import solid.humank.coffeeshop.order.domainevents.OrderCreated;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class DomainEventPublisher {

    @Inject
    CloudWatchEventAdapter cweAdapter;

    public PublishResult publish(OrderCreated orderCreated) {
        return cweAdapter.publishEvent(orderCreated);
    }
}
