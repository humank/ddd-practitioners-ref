package solid.humank.ddd.commons.baseclasses;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public abstract class DomainEvent<T extends EntityId> extends ValueObject<DomainEvent> {

    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PRIVATE)
    private UUID eventId;

    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PRIVATE)
    private OffsetDateTime occurredDate;

    @Getter(AccessLevel.PRIVATE)
    @Setter(AccessLevel.PUBLIC)
    private EntityId entityId;

    protected DomainEvent(EntityId entityId, Optional<OffsetDateTime> occurredDate) {
        this.eventId = UUID.randomUUID();
        this.occurredDate = (occurredDate == null) ? OffsetDateTime.now() : occurredDate.get();
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