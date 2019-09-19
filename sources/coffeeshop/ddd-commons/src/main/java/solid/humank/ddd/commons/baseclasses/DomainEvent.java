package solid.humank.ddd.commons.baseclasses;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import solid.humank.ddd.commons.interfaces.IDomainEvent;
import solid.humank.ddd.commons.interfaces.IEntityId;

import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.UUID;

public abstract class DomainEvent<T extends IEntityId> extends ValueObject<DomainEvent> implements IDomainEvent {

    @Getter(AccessLevel.PUBLIC) @Setter(AccessLevel.PRIVATE)
    private UUID eventId;

    @Getter(AccessLevel.PUBLIC) @Setter(AccessLevel.PRIVATE)
    private OffsetDateTime occurredDate;
    private void setEventId(UUID eventId){
        this.eventId = eventId;

    }

    abstract DomainEvent raise(IEntityId entityId, Optional<OffsetDateTime> occurredDate);
}
