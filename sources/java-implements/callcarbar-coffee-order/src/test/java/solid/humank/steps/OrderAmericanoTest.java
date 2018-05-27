package solid.humank.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import solid.humank.domains.Order;

import static org.junit.Assert.assertEquals;

public class OrderAmericanoTest {

    int priceOfAmericano;
    int orderCups;
    boolean isHere;
    int totalPrice;

    Order order;
    String orderString;

    @Given("^the price of a cup of Americano is (\\d+)$")
    public void the_price_of_a_cup_of_Americano_is(int price) throws Throwable {

        priceOfAmericano = price;
    }

    @When("^I order (\\d+) cups of Americano$")
    public void i_order_cups_of_Americano(int cups) throws Throwable {
        orderCups = cups;
    }

    @When("^decided to have it Here$")
    public void decided_to_have_it_Here() throws Throwable {
        isHere = true;
    }

    @When("^the order is established$")
    public void the_order_is_established() throws Throwable {

        order = new Order("2c",true,"Americano",2,80);
        orderString = order.establish();

    }

    @Then("^the total price should be (\\d+)$")
    public void the_total_price_should_be(int sum) throws Throwable {
        assertEquals(sum,order.payAmount());
    }

    @Then("^the coffee temperatuere should be (\\d+) degree c$")
    public void the_coffee_temperatuere_should_be_degree_c(int degree) throws Throwable {
        assertEquals(degree, order.getSeatDrinkDegree());
    }

}
