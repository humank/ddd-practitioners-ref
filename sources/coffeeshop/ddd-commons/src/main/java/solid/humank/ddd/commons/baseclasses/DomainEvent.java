package solid.humank.ddd.commons.baseclasses;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import solid.humank.ddd.commons.interfaces.IDomainEvent;

import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.UUID;

public abstract class DomainEvent<T extends EntityId> extends ValueObject<DomainEvent> implements IDomainEvent {

    @Getter(AccessLevel.PUBLIC) @Setter(AccessLevel.PRIVATE)
    private UUID eventId;

    @Getter(AccessLevel.PUBLIC) @Setter(AccessLevel.PRIVATE)
    private OffsetDateTime occurredDate;

    @Getter(AccessLevel.PRIVATE) @Setter(AccessLevel.PUBLIC)
    private EntityId entityId;

    protected abstract DomainEvent raise(EntityId entityId, Optional<OffsetDateTime> occurredDate);

    //TODO : why we need to design a getEqualityComponents method.
    //protected Iterable<Object> getEqualityComponents()
}
