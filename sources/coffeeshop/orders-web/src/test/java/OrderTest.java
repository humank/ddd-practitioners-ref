import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import solid.humank.coffeeshop.order.applications.CreateOrderSvc;
import solid.humank.coffeeshop.order.datacontracts.messages.CreateOrderMsg;
import solid.humank.coffeeshop.order.datacontracts.results.OrderItemRst;
import solid.humank.coffeeshop.order.models.requests.AddOrderReq;
import solid.humank.coffeeshop.order.models.requestsmodels.OrderItemRM;
import solid.humank.coffeeshop.order.repositories.OrderRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class OrderTest {


    @Test
    public void addOrderTest() {

        AddOrderReq req = new AddOrderReq();
        List<OrderItemRM> list = new ArrayList<>();
        list.add(new OrderItemRM("123", 2, new BigDecimal(200)));

        req.setItems(list);

        given().body(list)
                .when().post("/order")
                .then()
                .statusCode(200);
    }

    @Test
    public void createTest(){
        CreateOrderSvc service = new CreateOrderSvc(new OrderRepository());
        AddOrderReq req = new AddOrderReq();
        List<OrderItemRM> list = new ArrayList<>();
        list.add(new OrderItemRM("123", 2, new BigDecimal(200)));

        CreateOrderMsg cmd = new CreateOrderMsg("0", transformToOrderItemVM(list));
        service.establishOrder(cmd);
    }
    private List<OrderItemRst> transformToOrderItemVM(List<OrderItemRM> items) {
        List<OrderItemRst> result = new ArrayList<>();
        items.forEach(orderItemRM -> {
            result.add(new OrderItemRst(orderItemRM.getProductId(), orderItemRM.getQty(), orderItemRM.getPrice()));
        });

        return result;
    }
}
