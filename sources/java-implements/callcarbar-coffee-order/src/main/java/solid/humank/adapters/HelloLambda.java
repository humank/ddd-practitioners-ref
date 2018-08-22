package solid.humank.adapters;


import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class HelloLambda implements RequestHandler<Request, Response> {

    public Response handleRequest(Request request, Context context) {
        LambdaLogger logger = context.getLogger();
        String greetingString = String.format("Hello %s %s.", request.getFirstName(), request.getLastName());
        logger.log("check this one.");
        logger.log("greetingString is : " + greetingString);
        return new Response(greetingString);
    }
}


class Request {
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

class Response {
    boolean isBase64Encoded = false;
    int statusCode = 200;
    //String headers;
    String greetings;
    String body;

    public Response(String greetings) {
        this.greetings = greetings;
        this.body = greetings;
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
