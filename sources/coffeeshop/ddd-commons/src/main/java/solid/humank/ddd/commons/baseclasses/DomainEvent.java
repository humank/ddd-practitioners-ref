package solid.humank.ddd.commons.baseclasses;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class DomainEvent<T extends EntityId> extends PropertyComparer<DomainEvent> {

    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PRIVATE)
    private UUID eventId;

    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PRIVATE)
    private OffsetDateTime occurredDate;

    @Getter(AccessLevel.PRIVATE)
    @Setter(AccessLevel.PUBLIC)
    protected T entityId;

    protected DomainEvent() {
        this.eventId = UUID.randomUUID();
        this.occurredDate = OffsetDateTime.now();
    }

    protected abstract Iterable<Object> getDerivedEventEqualityComponents();

    @Override
    protected Iterable<Object> getEqualityComponents() {
        List<Object> collection = new ArrayList<Object>();
        collection.add(this.eventId);
        collection.add(this.occurredDate);
        collection.add(this.entityId);

        for (Object property : this.getDerivedEventEqualityComponents()) {
            collection.add(property);
        }
        return collection;
    }

}