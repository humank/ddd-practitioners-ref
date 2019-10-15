import org.junit.jupiter.api.Test;
import solid.humank.coffeeshop.order.models.OrderId;
import solid.humank.coffeeshop.order.repositories.OrderRepository;

public class OrderRepositoryTest {

    private Object OrderId;

    @Test
    public void getCountTest(){

        OrderRepository repository = new OrderRepository();
        OrderId = repository.generateOrderId();
    }

}
