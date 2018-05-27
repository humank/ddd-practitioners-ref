package solid.humank.adapters;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import solid.humank.domains.Order;

public class OrderRepository {

    final AmazonDynamoDB client = AmazonDynamoDBClientBuilder.defaultClient();
    final DynamoDB ddb = new DynamoDB(client);
    final private String tableName = "Order";

    public String saveOrder(Order purchasedOrder) {

        Table table = ddb.getTable(tableName);

        Item item = new Item()
                .withPrimaryKey("establishtime", purchasedOrder.getEstablishedTime())
                .withBoolean("isHereOrToGo",purchasedOrder.isHereOrToGo())
                .withString("productItemName", "Americano")
                .withNumber("price", 80)
                .withNumber("quantity", 2)
                .withBoolean("here", purchasedOrder.isHereOrToGo())
                .withString("seatno", purchasedOrder.getSeatNo())
                .withNumber("temperature", 70);

        table.putItem(item);

        return purchasedOrder.getEstablishedTime();
    }
}
