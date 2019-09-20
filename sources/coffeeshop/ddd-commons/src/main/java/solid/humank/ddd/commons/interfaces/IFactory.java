package solid.humank.ddd.commons.interfaces;

import solid.humank.ddd.commons.baseclasses.EntityId;

public interface IFactory<T extends IAggregateRoot,U extends EntityId>{

    T create(U entityId);
}
