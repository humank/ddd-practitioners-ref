/*
 * Copyright 2017 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file except
 * in compliance with the License. A copy of the License is located at
 *
 * http://aws.amazon.com/apache2.0
 *
 * or in the "license" file accompanying this file. This file is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

package solid.humank;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import com.amazonaws.services.lambda.runtime.LambdaLogger;

public class Order implements RequestHandler<Order.Request, Order.Response> {
    public Order.Response handleRequest(Order.Request request, Context context) {
        String greetingString = String.format("Hello %s %s.", request.firstName, request.lastName);
        LambdaLogger logger = context.getLogger();
        logger.log(String.format("Log output: Greeting is '%s'\n", greetingString));
        return new Order.Response(greetingString);
    }

    static class Request {
        String firstName;
        String lastName;

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

        public Request(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public Request() {
        }
    }

    static class Response {
        String greetings;

        public String getGreetings() {
            return greetings;
        }

        public void setGreetings(String greetings) {
            this.greetings = greetings;
        }

        public Response(String greetings) {
            this.greetings = greetings;
        }

        public Response() {
        }
    }
}
