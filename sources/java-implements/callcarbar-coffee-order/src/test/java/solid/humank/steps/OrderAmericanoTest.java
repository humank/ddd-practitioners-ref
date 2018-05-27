package solid.humank.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import solid.humank.domains.Order;
import solid.humank.domains.CoffeeItem;

import static org.junit.Assert.assertEquals;

public class OrderAmericanoTest {

    int price;
    int orderCups;
    int temperature;
    int totalPrice;
    CoffeeItem americano;

    Order order = new Order();

    @Given("^the price of a Americano is (\\d+)$")
    public void the_price_of_a_Americano_is(int price) throws Throwable {
        this.price = price;

        americano = new CoffeeItem("Americano");
        americano.setPrice(price);

    }

    @When("^I order (\\d+) cups of Americano$")
    public void i_order_cups_of_Americano(int cups) throws Throwable {

        order.accept(americano);
        order.accept(americano);
        this.orderCups = cups;

    }

    @When("^decided to have it HERE$")
    public void decided_to_have_it_HERE() throws Throwable {

        order.setHereOrToGo(true);
        order.setSeatNo("1a");
        temperature = 70;
    }

    @Then("^the total price should be (\\d+) \\* (\\d+)$")
    public void the_total_price_should_be(int cups, int price) throws Throwable {
        totalPrice = cups * price;
        assertEquals(totalPrice,order.payAmount());
        int orderNo = order.establish();

    }

    @Then("^the temperature should be (\\d+)$")
    public void the_temperature_should_be(int drinkTemperature) throws Throwable {
        assertEquals(temperature, drinkTemperature);
    }
}
