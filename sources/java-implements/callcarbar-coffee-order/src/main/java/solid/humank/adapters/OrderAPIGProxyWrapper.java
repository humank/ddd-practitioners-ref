package solid.humank.adapters;

import com.amazonaws.services.cloudwatchevents.AmazonCloudWatchEvents;
import com.amazonaws.services.cloudwatchevents.AmazonCloudWatchEventsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import solid.humank.domains.Order;

import java.io.IOException;

public class OrderAPIGProxyWrapper implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    final AmazonCloudWatchEvents cwe =
            AmazonCloudWatchEventsClientBuilder.defaultClient();
    final AmazonDynamoDB client = AmazonDynamoDBClientBuilder.defaultClient();
    final DynamoDB ddb = new DynamoDB(client);


    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent apiGatewayProxyRequestEvent, Context context) {

        LambdaLogger logger = context.getLogger();
        logger.log("Loading Java Lambda handler of ProxyIntegration mode \r\n");


        APIGatewayProxyResponseEvent responseEvent = new APIGatewayProxyResponseEvent();

        System.out.println(apiGatewayProxyRequestEvent.getBody());

        ObjectMapper objectMapper = new ObjectMapper();
        Order order = null;
        try {
            order = objectMapper.readValue(apiGatewayProxyRequestEvent.getBody(), Order.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String orderString = order.establish(cwe, ddb);

        responseEvent.setBody("{\"orderString:\"" + "\"" + orderString + "\"}");
        responseEvent.setStatusCode(200);

        return responseEvent;
    }
}
