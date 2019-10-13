package solid.humank.coffeeshop.infra.repositories;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.*;
import com.amazonaws.services.dynamodbv2.document.spec.DeleteItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.PutItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.UpdateItemSpec;
import com.fasterxml.jackson.databind.ObjectMapper;
import solid.humank.ddd.commons.baseclasses.AggregateRoot;
import solid.humank.ddd.commons.baseclasses.EntityId;
import solid.humank.ddd.commons.baseclasses.Specification;
import solid.humank.ddd.commons.interfaces.IRepository;
import solid.humank.ddd.commons.interfaces.ISelector;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.function.Supplier;

// due to ddb need to specify each data structure before crud actions,
// soe need to leave the 4 interfaces as parameter from outside.
//        PutItemSpec
//        GetItemSpec
//        DeleteItemSpec
//        UpdateItemSpec

//public class DDBRepositoryBase<T extends AggregateRoot, U extends EntityId> implements IRepository<T, U> {
    public class DDBRepositoryBase {
//    final AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();
//    final DynamoDB dynamoDB = new DynamoDB(client);
//    final ItemConfig config;
//
//    private Supplier<T> supplier;
//
//    public DDBRepositoryBase(ItemConfig config, Supplier<T> supplier) {
//        this.supplier = supplier;
//        this.config = config;
//
//
//    }
//
//    @Override
//    public T get(U entityId) {
//        Table table = dynamoDB.getTable(config.getTableName());
//        GetItemSpec spec = new GetItemSpec()
//                .withPrimaryKey(new PrimaryKey("SeqNo", entityId.getSeqNo()));
//        Item item = table.getItem(spec);
//        ObjectMapper mapper = new ObjectMapper();
//        try {
//            System.out.println(item.toJSONPretty());
//
//            supplier = mapper.readValue(item.toJSONPretty(), supplier.getClass());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return supplier.get();
//    }
//
//    @Override
//    public void remove(T aggregateRoot) {
//
//        DeleteItemSpec spec = new DeleteItemSpec()
//                .withPrimaryKey(new PrimaryKey("SeqNo", aggregateRoot.getId().getSeqNo()));
//
//        Table table = dynamoDB.getTable(config.getTableName());
//        DeleteItemOutcome outcome = table.deleteItem(spec);
//    }
//
//    @Override
//    public T first(ISelector selector, Specification<U> by) {
//        return null;
//    }
//
//    @Override
//    public T create(T aggregateRoot) {
//
//        Table table = dynamoDB.getTable(config.getTableName());
//        Item item = new Item();
//        try {
//            BeanInfo beanInfo = Introspector.getBeanInfo(aggregateRoot.getClass());
//            for (PropertyDescriptor propertyDesc : beanInfo.getPropertyDescriptors()) {
//                String propertyName = propertyDesc.getName();
//                propertyDesc.getPropertyType();
//                Object value = propertyDesc.getReadMethod().invoke(aggregateRoot);
//            }
//
//
//        } catch (IntrospectionException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        }
//        table.putItem(item);
//
//        return aggregateRoot;
//    }
//
//    @Override
//    public long count() {
//        return 0;
//    }
//
//    @Override
//    public boolean any(Specification<T> by) {
//        return false;
//    }
//
//    @Override
//    public T get(FunctionalInterface func, Specification<U> by) {
//        return null;
//    }
//
//    @Override
//    public T get(ISelector selector, Specification<U> by) {
//        return null;
//    }

}
