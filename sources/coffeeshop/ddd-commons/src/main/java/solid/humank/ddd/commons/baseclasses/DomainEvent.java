package solid.humank.ddd.commons.baseclasses;

import solid.humank.ddd.commons.interfaces.IDomainEvent;
import solid.humank.ddd.commons.interfaces.IEntityId;

public abstract class DomainEvent<T extends IEntityId> extends ValueObject<DomainEvent> implements IDomainEvent {

}
