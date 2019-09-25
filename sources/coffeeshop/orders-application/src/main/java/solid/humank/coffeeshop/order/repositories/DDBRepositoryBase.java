package solid.humank.coffeeshop.order.repositories;

import solid.humank.ddd.commons.baseclasses.AggregateRoot;
import solid.humank.ddd.commons.baseclasses.EntityId;
import solid.humank.ddd.commons.baseclasses.Specification;
import solid.humank.ddd.commons.interfaces.IRepository;
import solid.humank.ddd.commons.interfaces.ISelector;

public class DDBRepositoryBase<T extends AggregateRoot, U extends EntityId> implements IRepository<T, U> {

    @Override
    public T get(U entityId) {
        return null;
    }

    @Override
    public T remove(T aggregateRoot) {
        return null;
    }

    @Override
    public T first(ISelector selector, Specification<U> by) {
        return null;
    }

    @Override
    public T create(T aggregateRoot) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public boolean any(Specification<T> by) {
        return false;
    }

    @Override
    public T get(FunctionalInterface func, Specification<U> by) {
        return null;
    }

    @Override
    public T get(ISelector selector, Specification<U> by) {
        return null;
    }
}
