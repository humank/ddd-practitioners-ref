package solid.humank.adapters;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import solid.humank.domains.OrderReadModel;
import solid.humank.domains.Order;

public class OrderHandler implements RequestHandler<Order, OrderReadModel> {

    @Override
    public OrderReadModel handleRequest(Order order, Context context) {

        LambdaLogger logger = context.getLogger();

        logger.log(String.format("Log output: order line is '%s'\n", order.toString()));


        order.establish();
        OrderReadModel orderReadModel = new OrderReadModel();

        return orderReadModel;
    }


}
