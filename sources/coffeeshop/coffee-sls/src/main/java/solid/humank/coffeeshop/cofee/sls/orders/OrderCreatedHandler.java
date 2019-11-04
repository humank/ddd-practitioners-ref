package solid.humank.coffeeshop.cofee.sls.orders;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;

import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import solid.humank.coffeeshop.cofee.sls.orders.datacontracts.OrderCreated;
import solid.humank.coffeeshop.coffee.models.Barista;
import solid.humank.coffeeshop.coffee.models.Coffee;
import solid.humank.ddd.commons.utilities.DomainModelMapper;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class OrderCreatedHandler implements RequestStreamHandler {


    @Override
    public void handleRequest(InputStream inputStream, OutputStream outputStream, @NotNull Context context) throws IOException {

        LambdaLogger logger = context.getLogger();

        DomainModelMapper mapper = new DomainModelMapper();
        OrderCreated orderCreated = mapper.readValue(inputStream, OrderCreated.class);

        String response = String.format("Order Received: %s", orderCreated.toString());
        logger.log(response);

        //TODO : 調用咖啡師的服務, 參照需求文檔找製作美式咖啡的需求
        List<Coffee> coffees = transformToCoffeeItems(orderCreated);
        Barista.make(coffees);
        //TODO : 在咖啡師製作的過程中，發現手邊原料不足時，去扣庫存, 提供web api調度以及 application service
    }

    @Nullable
    @Contract(pure = true)
    private List<Coffee> transformToCoffeeItems(OrderCreated receivedOrder){
        return null;
    }
}
