package solid.humank.ddd.commons.baseclasses;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import solid.humank.ddd.commons.interfaces.IDomainEvent;
import solid.humank.ddd.commons.interfaces.IEntity;

import java.util.Comparator;
import java.util.List;

public abstract class Entity<T extends EntityId> implements IEntity, Comparator<Entity> {

    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PRIVATE)
    public EntityId id;

    private boolean suppressEvent = false;
    protected List<IDomainEvent> domainEvents;

    protected void applyEvent(IDomainEvent event) {
        domainEvents.add(event);
    }

    protected void suppressEvent() {
        suppressEvent = true;
    }

    protected void unSuppressedEvent() {
        suppressEvent = false;
    }
}
