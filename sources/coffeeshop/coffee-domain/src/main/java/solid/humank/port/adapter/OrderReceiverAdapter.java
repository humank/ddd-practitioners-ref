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

package solid.humank.port.adapter;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import solid.humank.domain.model.Order;
import solid.humank.domain.model.OrderMakeup;

import java.io.IOException;

public class OrderReceiverAdapter implements RequestHandler<OrderCreatedEvent, OrderMakeup> {

    @Override
    public OrderMakeup handleRequest(OrderCreatedEvent orderCreatedEvent, Context context) {

        //call barista makeup coffee.

        LambdaLogger logger = context.getLogger();

        OrderMakeup madeup = new OrderMakeup();
        logger.log("yoyo");
        logger.log(orderCreatedEvent.toString());

        ObjectMapper mapper = new ObjectMapper();
        try {
            String orderString= mapper.writeValueAsString(orderCreatedEvent.getDetail());
            logger.log(orderString);
            mapper.readValue(orderString, Order.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return null;
    }
}
