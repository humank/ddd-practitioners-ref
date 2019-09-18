package solid.humank.ddd.commons.interfaces;

import solid.humank.ddd.commons.baseclasses.Specification;

public interface IRepository<T extends IEntity, U extends IEntityId> {

    T get(U entityId);

    void remove(T entity);

    long count(Specification<T> by);

    boolean any(Specification<T> by);

    

}
