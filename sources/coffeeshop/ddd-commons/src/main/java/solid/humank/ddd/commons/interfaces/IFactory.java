package solid.humank.ddd.commons.interfaces;

public interface IFactory<T extends IAggregateRoot,U extends IEntityId>{

    T create(U entityId);
}
