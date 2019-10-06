import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import solid.humank.coffeeshop.infra.repositories.DDBRepositoryBase;
import solid.humank.coffeeshop.infra.repositories.ItemConfig;
import solid.humank.coffeeshop.order.models.Order;
import solid.humank.coffeeshop.order.models.OrderId;
import solid.humank.coffeeshop.order.persistence.OrderPO;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.time.OffsetDateTime;
import java.util.*;

public class DDBTest {

    @Test
    public void objTest(){
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();
        DynamoDBMapper mapper = new DynamoDBMapper(client);
        OrderPO orderPO =new OrderPO(new Order());
        mapper.save(orderPO);
    }

    @Test
    public void orderJsonTest() throws JsonProcessingException {
        Order order = new Order();


        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(Order.class);
            for (PropertyDescriptor propertyDesc : beanInfo.getPropertyDescriptors()) {
                String propertyName = propertyDesc.getName();
                System.out.println(propertyDesc.getPropertyType().getCanonicalName());
                System.out.println(propertyDesc.getPropertyType().getName());
                Object value = propertyDesc.getReadMethod().invoke(order);
            }


        } catch (IntrospectionException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        //System.out.println(new ObjectMapper().writeValueAsString(order));
    }


    @Test
    public void getByPK() {

        ItemConfig config = new ItemConfig(
                "order2",
                new GetItemSpec()
                        .withPrimaryKey("SeqNo", 456));
        DDBRepositoryBase<Order, OrderId> ddbRepositoryBase = new DDBRepositoryBase<Order, OrderId>(config, () -> new Order());

        OrderId orderId = new OrderId(456, OffsetDateTime.now());

        ddbRepositoryBase.get(orderId);

    }

    @Test
    public void complexityObject() {
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();
        DynamoDB dynamoDB = new DynamoDB(client);

        Table table = dynamoDB.getTable("ProductCatalog");

// Build a list of related items
        List<Number> relatedItems = new ArrayList<Number>();
        relatedItems.add(341);
        relatedItems.add(472);
        relatedItems.add(649);

//Build a map of product pictures
        Map<String, String> pictures = new HashMap<String, String>();
        pictures.put("FrontView", "http://example.com/products/123_front.jpg");
        pictures.put("RearView", "http://example.com/products/123_rear.jpg");
        pictures.put("SideView", "http://example.com/products/123_left_side.jpg");

//Build a map of product reviews
        Map<String, List<String>> reviews = new HashMap<String, List<String>>();

        List<String> fiveStarReviews = new ArrayList<String>();
        fiveStarReviews.add("Excellent! Can't recommend it highly enough!  Buy it!");
        fiveStarReviews.add("Do yourself a favor and buy this");
        reviews.put("FiveStar", fiveStarReviews);

        List<String> oneStarReviews = new ArrayList<String>();
        oneStarReviews.add("Terrible product!  Do not buy this.");
        reviews.put("OneStar", oneStarReviews);

// Build the item
        Item item = new Item()
                .withPrimaryKey("Id", 123)
                .withString("Title", "Bicycle 123")
                .withString("Description", "123 description")
                .withString("BicycleType", "Hybrid")
                .withString("Brand", "Brand-Company C")
                .withNumber("Price", 500)
                .withStringSet("Color", new HashSet<String>(Arrays.asList("Red", "Black")))
                .withString("ProductCategory", "Bicycle")
                .withBoolean("InStock", true)
                .withNull("QuantityOnHand")
                .withList("RelatedItems", relatedItems)
                .withMap("Pictures", pictures)
                .withMap("Reviews", reviews);

// Write the item to the table
        PutItemOutcome outcome = table.putItem(item);

    }

}