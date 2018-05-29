package solid.humank.adapters;

import com.amazonaws.services.cloudwatchevents.AmazonCloudWatchEvents;
import com.amazonaws.services.cloudwatchevents.AmazonCloudWatchEventsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import solid.humank.domains.Order;

import java.io.*;

public class OrderPrxoyHandler implements RequestStreamHandler {

    final AmazonCloudWatchEvents cwe =
            AmazonCloudWatchEventsClientBuilder.defaultClient();
    final AmazonDynamoDB client = AmazonDynamoDBClientBuilder.defaultClient();
    final DynamoDB ddb = new DynamoDB(client);

//    ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//    ;
//    Map headers = new HashMap<>();


    JSONParser parser = new JSONParser();

    public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context) throws IOException {

        LambdaLogger logger = context.getLogger();
        logger.log("Loading Java Lambda handler of ProxyWithStream");


        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        JSONObject responseJson = new JSONObject();
        int responseCode = 200;

        Order order = new Order();
        try {
            JSONObject event = (JSONObject) parser.parse(reader);

            if (event.get("body") != null) {
                JSONObject body = (JSONObject) parser.parse((String) event.get("body"));

                if (body.get("seatNo") != null) {
                    order.setSeatNo((String) body.get("seatNo"));
                }
                if (body.get("drinkhere") != null) {
                    order.setDrinkHere(Boolean.valueOf((String) body.get("drinkhere")));
                }
                if (body.get("itemName") != null) {
                    order.setItemName((String) body.get("itemName"));
                }
                if (body.get("quantity") != null) {
                    order.setQuantity(Integer.parseInt((String) body.get("quantity")));
                }
                if (body.get("price") != null) {
                    order.setPrice(Integer.parseInt((String) body.get("price")));
                }

            }

            System.out.println("check order info .................");
            System.out.println("check order info .................");
            System.out.println(order.toString());

            String orderString = order.establish(cwe, ddb);

            JSONObject responseBody = new JSONObject();
            responseBody.put("orderNo", orderString);

            JSONObject headerJson = new JSONObject();
            headerJson.put("Content-Type", "application/json");
            responseJson.put("isBase64Encoded", false);
            responseJson.put("statusCode", responseCode);
            responseJson.put("headers", headerJson);
            responseJson.put("body", responseBody.toString());

        } catch (ParseException pex) {
            responseJson.put("statusCode", "400");
            responseJson.put("exception", pex);
        }

        System.out.println(responseJson.toJSONString());
        OutputStreamWriter writer = new OutputStreamWriter(outputStream, "UTF-8");
        writer.write(responseJson.toJSONString());
        writer.close();
    }


}