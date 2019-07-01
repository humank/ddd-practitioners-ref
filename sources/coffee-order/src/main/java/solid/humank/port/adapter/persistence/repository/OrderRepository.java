package solid.humank.port.adapter.persistence.repository;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import solid.humank.domain.model.Order;

public class OrderRepository {

    final private String tableName = "Order";
    final AmazonDynamoDB client = AmazonDynamoDBClientBuilder.defaultClient();
    final DynamoDB ddb = new DynamoDB(client);


    public Order saveOrder(Order purchasedOrder) {

        System.out.println("item name is : " + purchasedOrder.getItemName());

        Table table = ddb.getTable(tableName);

        Item item = new Item()
                .withPrimaryKey("establishtime", purchasedOrder.getEstablishTime())
                .withBoolean("drinkHere", purchasedOrder.isDrinkHere())
                .withString("itemName", purchasedOrder.getItemName())
                .withNumber("price", purchasedOrder.getPrice())
                .withNumber("quantity", 2)
                .withString("seatno", purchasedOrder.getSeatNo())
                .withNumber("drinkTemperature", purchasedOrder.getDrinkTemperature());

        table.putItem(item);

        return purchasedOrder;
    }
}
