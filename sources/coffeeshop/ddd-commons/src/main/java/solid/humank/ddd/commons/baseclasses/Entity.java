package solid.humank.ddd.commons.baseclasses;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

public abstract class Entity<T extends EntityId> extends PropertyComparer<Entity> {

    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PROTECTED)
    public EntityId id;

    private boolean suppressEvent = false;
    protected List<DomainEvent<? extends EntityId>> domainEvents = new ArrayList<>();

    protected void applyEvent(DomainEvent<? extends EntityId> event) {
        if (suppressEvent) return;
        this.domainEvents.add(event);
    }

    protected void suppressEvent() {
        suppressEvent = true;
    }

    protected void unSuppressedEvent() {
        suppressEvent = false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entity<?> entity = (Entity<?>) o;
        return id.equals(entity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getClass(), id);
    }
}
