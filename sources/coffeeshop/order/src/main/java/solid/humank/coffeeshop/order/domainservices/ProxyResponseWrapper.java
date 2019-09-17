package solid.humank.coffeeshop.order.domainservices;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import solid.humank.coffeeshop.order.domains.OrderTicket;

public class ProxyResponseWrapper {
    public static APIGatewayProxyResponseEvent wrap(OrderTicket orderTicket) {

        APIGatewayProxyResponseEvent responseEvent = new APIGatewayProxyResponseEvent();
        responseEvent.setBody("{\"orderString\":" + "\"" + orderTicket.value() + "\"}");
        responseEvent.setStatusCode(200);

        return responseEvent;
    }
}
