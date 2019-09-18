package solid.humank.ddd.commons.interfaces;

import java.time.OffsetDateTime;
import java.util.UUID;

public interface IDomainEvent {

    UUID eventId = null;
    OffsetDateTime occurDate = null;

}
