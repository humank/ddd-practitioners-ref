package solid.humank.adapters;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import solid.humank.domains.EstablishedOrder;
import solid.humank.domains.Order;

public class OrderHandler implements RequestHandler<Order, EstablishedOrder> {

    @Override
    public EstablishedOrder handleRequest(Order order, Context context) {

        LambdaLogger logger = context.getLogger();

        logger.log(String.format("Log output: order line is '%s'\n", order.toString()));


        EstablishedOrder establishedOrder = new EstablishedOrder();

        return establishedOrder;
    }


}
