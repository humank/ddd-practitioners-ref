package cucumber;

import io.cucumber.java.Before;
import io.cucumber.java8.En;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import solid.humank.coffeeshop.order.applications.CreateOrderSvc;
import solid.humank.coffeeshop.order.datacontracts.messages.CreateOrderMsg;
import solid.humank.coffeeshop.order.datacontracts.results.OrderItemRst;
import solid.humank.coffeeshop.order.datacontracts.results.OrderRst;
import solid.humank.coffeeshop.order.domainservices.OrderItemsTranslator;
import solid.humank.coffeeshop.order.models.OrderId;
import solid.humank.coffeeshop.order.repositories.OrderRepository;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

//TODO Finish mock tests.

public class OrderAmericanoSteps implements En {

    CreateOrderMsg request;
    OrderRst orderRst;
    OrderItemRst orderItemRst;

    @Mock
    OrderRepository repositoryMock;

    @Mock
    OrderItemsTranslator orderItemsTranslator;

    @InjectMocks
    CreateOrderSvc service = new CreateOrderSvc();

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    public OrderAmericanoSteps() {

        Given("customer wants to order coffee with the following detail", (io.cucumber.datatable.DataTable dataTable) -> {
            List<Map<String, String>> testData = dataTable.asMaps(String.class, String.class);
            Map<String, String> sample = testData.get(0);

            String productId = sample.get("coffee");
            int quantity = Integer.valueOf(sample.get("quantity"));
            BigDecimal price = new BigDecimal(sample.get("price"));

            orderItemRst = new OrderItemRst(productId, quantity, price);
            List<OrderItemRst> items = new ArrayList<>();
            items.add(orderItemRst);

            request = new CreateOrderMsg("0", items);
        });

        When("the order is confirmed", () -> {

            OrderId orderId = new OrderId(1, OffsetDateTime.now());
            when(repositoryMock.generateOrderId()).thenReturn(orderId);

            orderRst = service.establishOrder(request);
        });

        Then("the total fee should be {int}l", (Integer int1) -> {
            BigDecimal totalFee = orderItemRst.getFee();
            assertEquals(totalFee.longValue(), int1.longValue());
        });
    }
}
