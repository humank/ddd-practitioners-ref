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

package solid.humank.domains;

import com.amazonaws.services.cloudwatchevents.AmazonCloudWatchEvents;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import lombok.Data;
import org.joda.time.LocalDateTime;
import solid.humank.adapters.CloudwatchEventAdapter;
import solid.humank.adapters.OrderRepository;
import solid.humank.events.OrderEstablishedEvent;

@Data
public class Order {

    private int quantity;
    private String seatNo;
    private boolean drinkHere;
    private int price;
    private String itemName;
    private String establishTime;
    private int drinktemperature;

    public Order() {

    }

    public Order(String seatNo, boolean ishere, String itemName, int quantity, int price) {
        this.seatNo = seatNo;
        this.drinkHere = ishere;
        this.itemName = itemName;
        this.quantity = quantity;
        this.price = price;
    }

    public int payAmount() {
        return this.getQuantity() * this.getPrice();

    }

    public String establish(AmazonCloudWatchEvents cwe, DynamoDB ddb) {

        this.establishTime = new LocalDateTime().toString("yyyy-MM-dd:HH:mm:ss");

        if (drinkHere) {
            this.drinktemperature = 70;
        } else {
            this.drinktemperature = 100;
        }

        //save to repo
        OrderRepository repository = new OrderRepository(ddb);
        String orderString = repository.saveOrder(this);

        //send event to makeup

        CloudwatchEventAdapter cweAdapter = new CloudwatchEventAdapter(cwe);
        String publishResult = cweAdapter.publishEvent(new OrderEstablishedEvent(this));

        System.out.println("publish result : " + publishResult);
        return orderString;
    }
}
