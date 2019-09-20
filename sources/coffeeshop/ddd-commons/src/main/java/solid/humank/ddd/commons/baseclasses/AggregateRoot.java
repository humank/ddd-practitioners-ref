package solid.humank.ddd.commons.baseclasses;

import solid.humank.ddd.commons.interfaces.IAggregateRoot;

public abstract class AggregateRoot<T extends EntityId> extends Entity<EntityId> implements IAggregateRoot {

}