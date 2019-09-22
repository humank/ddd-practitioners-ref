package solid.humank.coffeeshop.order.domainservices;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import solid.humank.coffeeshop.order.datacontracts.results.OrderTicket;

public class ProxyResponseWrapper {
    public static APIGatewayProxyResponseEvent wrap(OrderTicket orderTicket) {

        APIGatewayProxyResponseEvent responseEvent = new APIGatewayProxyResponseEvent();
        responseEvent.setBody("{\"orderString\":" + "\"" + orderTicket.getUuid().toString() + "\"}");
        responseEvent.setStatusCode(200);

        return responseEvent;
    }
}
