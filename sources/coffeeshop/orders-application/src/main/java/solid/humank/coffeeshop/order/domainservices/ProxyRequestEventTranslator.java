package solid.humank.coffeeshop.order.domainservices;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import solid.humank.coffeeshop.order.commands.CreateOrder;

import java.io.IOException;

public class ProxyRequestEventTranslator {
    public static CreateOrder translate(APIGatewayProxyRequestEvent apiGatewayProxyRequestEvent) {

        ObjectMapper objectMapper = new ObjectMapper();
        CreateOrder createOrder = null;
        try {
            createOrder = objectMapper.readValue(apiGatewayProxyRequestEvent.getBody(), CreateOrder.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return createOrder;
    }
}
