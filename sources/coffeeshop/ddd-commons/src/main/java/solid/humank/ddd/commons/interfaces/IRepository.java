package solid.humank.ddd.commons.interfaces;

import solid.humank.ddd.commons.baseclasses.EntityId;
import solid.humank.ddd.commons.baseclasses.Specification;

import java.io.Serializable;
import java.util.function.Predicate;

public interface IRepository<T extends IAggregateRoot, U extends EntityId> {

    T get(U entityId);

    void remove(T aggregateRoot);

    void create(T aggregateRoot);

    long count(Specification<T> by);

    boolean any(Specification<T> by);

    /**
     * Just pass a functional interface, leave the most wide dev freedom for builder.
     * pros: leave the most freedom.
     * cons: lack of type constraints from FunctionalInterface
     */
    T get(FunctionalInterface func, Specification<U> by);

    //provide a predefined functional interface method, leave the mandatory method and type constraints to be implemented.
    T get(ISelector selector, Specification<U> by);

    T first(ISelector selector, Specification<U> by);
}