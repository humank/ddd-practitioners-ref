package solid.humank.ddd.commons.interfaces;

import solid.humank.ddd.commons.baseclasses.AggregateRoot;
import solid.humank.ddd.commons.baseclasses.EntityId;
import solid.humank.ddd.commons.baseclasses.Specification;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface IRepository<T extends AggregateRoot, U extends EntityId> {

    //TODO: find a object for C# Iqueryable<T> collection
    //IQueryable<T> all;

    List<T> all();

    T get(U entityId);

    /**
     * Just pass a functional interface, leave the most wide dev freedom for builder.
     * pros: leave the most freedom.
     * cons: lack of type constraints from FunctionalInterface
     */
    T get(FunctionalInterface func, Specification<U> by);

    //provide a predefined functional interface method, leave the mandatory method and type constraints to be implemented.
    T get(ISelector selector, Specification<U> by);

    T first(ISelector selector, Specification<U> by);

    boolean any(Specification<T> by);

    long count(Specification<T> by);

    void create(T aggregateRoot);

    int update(T aggregateRoot);

    void remove(T aggregateRoot);










}
