package solid.humank.coffeeshop.infra.repositories;

// due to ddb need to specify each data structure before crud actions,
// soe need to leave the 4 interfaces as parameter from outside.
//        PutItemSpec
//        GetItemSpec
//        DeleteItemSpec
//        UpdateItemSpec

import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import solid.humank.ddd.commons.baseclasses.AggregateRoot;
import solid.humank.ddd.commons.baseclasses.EntityId;
import solid.humank.ddd.commons.baseclasses.Specification;
import solid.humank.ddd.commons.interfaces.IRepository;
import solid.humank.ddd.commons.interfaces.ISelector;

import java.util.List;

public class DDBRepositoryBase<T extends AggregateRoot, U extends EntityId> implements IRepository<T, U> {

    DynamoDbClient ddb;

    @Override
    public List<T> all() {
        return null;
    }

    @Override
    public T get(U entityId) {
        return null;
    }

    @Override
    public T get(FunctionalInterface func, Specification<U> by) {
        return null;
    }

    @Override
    public T get(ISelector selector, Specification<U> by) {
        return null;
    }

    @Override
    public T first(ISelector selector, Specification<U> by) {
        return null;
    }

    @Override
    public boolean any(Specification<T> by) {
        return false;
    }

    @Override
    public long count(Specification<T> by) {
        return 0;
    }

    @Override
    public void create(T aggregateRoot) {

    }

    @Override
    public int update(T aggregateRoot) {
        return 0;
    }

    @Override
    public void remove(T aggregateRoot) {

    }

}
