package solid.humank.port.adapter;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class ProxyRequestEventTranslator {
    public static OrderDTO translate(APIGatewayProxyRequestEvent apiGatewayProxyRequestEvent) {

        ObjectMapper objectMapper = new ObjectMapper();
        OrderDTO orderDTO = null;
        try {
            orderDTO = objectMapper.readValue(apiGatewayProxyRequestEvent.getBody(), OrderDTO.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return orderDTO;
    }
}
