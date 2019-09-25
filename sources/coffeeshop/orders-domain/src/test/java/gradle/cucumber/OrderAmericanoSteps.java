package gradle.cucumber;

import com.amazonaws.services.cloudwatchevents.AmazonCloudWatchEvents;
import com.amazonaws.services.cloudwatchevents.AmazonCloudWatchEventsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import io.cucumber.java8.En;
import solid.humank.coffeeshop.order.commands.CreateOrder;
import solid.humank.coffeeshop.order.models.Order;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class OrderAmericanoSteps implements En {

    int priceOfAmericano;
    int orderCups;
    boolean isHere;
    Order order;

    final AmazonCloudWatchEvents cwe =
            AmazonCloudWatchEventsClientBuilder.defaultClient();

    final AmazonDynamoDB client = AmazonDynamoDBClientBuilder.defaultClient();
    final DynamoDB ddb = new DynamoDB(client);

    public OrderAmericanoSteps() {
//        Given("the price of a cup of Americano is {int}", (Integer price) -> {
//            priceOfAmericano = price;
//        });
//
//        When("I order {int} cups of Americano", (Integer cups) -> {
//            orderCups = cups;
//        });
//
//        When("decided to have it Here", () -> {
//            isHere = true;
//        });
//
//        When("the order is established", () -> {
//            CreateOrder orderDTO = new CreateOrder("2c", true, "Americano", 2, 80);
//            //order = DomainRegistry.orderService().establishOrder(orderDTO);
//        });
//
//        Then("the total price should be {int}", (Integer sum) -> {
//            assertEquals(sum, order.payAmount());
//        });
//
//        Then("the coffee temperature should be {int} degree c", (Integer degree) -> {
//            assertEquals(degree, order.getDrinkTemperature());
//        });

    }
}
