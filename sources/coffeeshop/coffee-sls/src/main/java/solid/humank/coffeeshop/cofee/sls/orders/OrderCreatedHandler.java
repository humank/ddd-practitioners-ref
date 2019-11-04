package solid.humank.coffeeshop.cofee.sls.orders;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import solid.humank.coffeeshop.cofee.sls.coffee.models.CoffeeRM;
import solid.humank.coffeeshop.coffee.models.Barista;
import solid.humank.coffeeshop.coffee.models.Coffee;

import java.util.List;
import java.util.Map;

public class OrderCreatedHandler implements RequestHandler<Map, CoffeeRM> {

    @Override
    public CoffeeRM handleRequest(Map orderCreated, Context context) {
        LambdaLogger logger = context.getLogger();

        String response = String.format("Order Received: %s", orderCreated.toString());
        logger.log(response);

        //TODO : 調用咖啡師的服務, 參照需求文檔找製作美式咖啡的需求
        List<Coffee> coffees = transformToCoffeeItems(orderCreated);
        Barista.make(coffees);
        //TODO : 在咖啡師製作的過程中，發現手邊原料不足時，去扣庫存, 提供web api調度以及 application service


        return new CoffeeRM();
    }

    private List<Coffee> transformToCoffeeItems(Map receivedOrder){
        return null;
    }
}
