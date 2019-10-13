package solid.humank.coffeeshop.order.domainservices;

import solid.humank.coffeeshop.infra.adapters.CloudWatchEventAdapter;
import solid.humank.ddd.commons.baseclasses.DomainEvent;
import solid.humank.ddd.commons.baseclasses.EntityId;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;

@RequestScoped
public class DomainEventPublisher {

    public DomainEventPublisher() {
    }

    @Inject
    CloudWatchEventAdapter cweAdapter;

    public void publish(List<DomainEvent<? extends EntityId>> domainEvents) {
        for (DomainEvent de : domainEvents) {
            cweAdapter.publishEvent(de);
        }
    }
}
