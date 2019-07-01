package solid.humank.port.adapter;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import solid.humank.domain.model.DomainRegistry;
import solid.humank.domain.model.OrderTicket;

public class OrderAPIGProxyWrapper implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent apiGatewayProxyRequestEvent, Context context) {

        LambdaLogger logger = context.getLogger();
        logger.log("Yes oh my !!! Loading Java Lambda handler of ProxyIntegration mode \r\n");
        logger.log(apiGatewayProxyRequestEvent.getBody());

        OrderDTO orderDTO = ProxyRequestEventTranslator.translate(apiGatewayProxyRequestEvent);

        OrderTicket orderTicket = DomainRegistry.orderService().establishOrder(orderDTO).orderTicket();

        APIGatewayProxyResponseEvent responseEvent = ProxyResponseWrapper.wrap(orderTicket);
        

        return responseEvent;
    }
}
