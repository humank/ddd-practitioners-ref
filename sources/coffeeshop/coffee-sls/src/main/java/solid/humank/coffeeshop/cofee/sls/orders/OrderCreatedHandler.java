package solid.humank.coffeeshop.cofee.sls.orders;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class OrderCreatedHandler implements RequestHandler<OrderCreated, String> {

    @Override
    public String handleRequest(OrderCreated orderCreated, Context context) {

        LambdaLogger logger = context.getLogger();
        String response = String.format("Order Received: %s", orderCreated.getOrderId().getSeqNo());

        //TODO : 調用咖啡師的服務，去冰箱拿原料以及去調用Orders 的服務去更新狀態
        return response;
    }
}
