package solid.humank.adapters;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import solid.humank.domains.Order;

public class OrderHandler implements RequestHandler<OrderHandler.Request, OrderHandler.Response> {

    @Override
    public OrderHandler.Response handleRequest(OrderHandler.Request request, Context context) {
        String greetingString = String.format("Hello %s %s.", request.firstName, request.lastName);
        LambdaLogger logger = context.getLogger();
        logger.log(String.format("Log output: Greeting is '%s'\n", greetingString));
        return new OrderHandler.Response(greetingString);
    }

    static class Request {
        String firstName;
        String lastName;

        public Request(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public Request() {
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }
    }

    static class Response {
        String greetings;

        public Response(String greetings) {
            this.greetings = greetings;
        }

        public Response() {
        }

        public String getGreetings() {
            return greetings;
        }

        public void setGreetings(String greetings) {
            this.greetings = greetings;
        }
    }
}
