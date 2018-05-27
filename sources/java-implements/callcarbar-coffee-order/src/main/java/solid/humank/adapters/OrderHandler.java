package solid.humank.adapters;

import com.amazonaws.services.cloudwatchevents.AmazonCloudWatchEvents;
import com.amazonaws.services.cloudwatchevents.AmazonCloudWatchEventsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import solid.humank.domains.Order;

import java.util.HashMap;
import java.util.Map;

public class OrderHandler implements RequestHandler<Order, LambdaProxyIntegrationOutput> {

    final AmazonCloudWatchEvents cwe =
            AmazonCloudWatchEventsClientBuilder.defaultClient();

    final AmazonDynamoDB client = AmazonDynamoDBClientBuilder.defaultClient();
    final DynamoDB ddb = new DynamoDB(client);

    Map headers = new HashMap<>();

    @Override
    public LambdaProxyIntegrationOutput handleRequest(Order order, Context context) {

        LambdaLogger logger = context.getLogger();
        logger.log(String.format("Log output: order line is '%s'\n", order.toString()));

        String orderString = order.establish(cwe,ddb);
        LambdaProxyIntegrationOutput output = new LambdaProxyIntegrationOutput();
        output.setBody("{\"orderString\" : \"" + orderString +"\"}");
        output.setStatusCode(200);

        headers.put("Content-Type","application/json");
        output.setHeaders(headers);

        return output;
    }


}
