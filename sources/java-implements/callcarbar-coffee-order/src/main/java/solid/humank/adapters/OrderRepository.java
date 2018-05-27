package solid.humank.adapters;

import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import solid.humank.domains.Order;

public class OrderRepository {

    final private String tableName = "Order";

    private DynamoDB ddb;

    public OrderRepository(DynamoDB ddb) {
        this.ddb = ddb;
    }

    public String saveOrder(Order purchasedOrder) {

        System.out.println("item name is : " + purchasedOrder.getItemName());

        Table table = ddb.getTable(tableName);

        Item item = new Item()
                .withPrimaryKey("establishtime", purchasedOrder.getEstablishTime())
                .withBoolean("drinkHere",purchasedOrder.isDrinkHere())
                .withString("itemName", purchasedOrder.getItemName())
                .withNumber("price", purchasedOrder.getPrice())
                .withNumber("quantity", 2)
                .withString("seatno", purchasedOrder.getSeatNo())
                .withNumber("drinktemperature", purchasedOrder.getDrinktemperature());

        table.putItem(item);

        return purchasedOrder.getEstablishTime();
    }
}
