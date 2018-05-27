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

import lombok.Data;
import org.joda.time.LocalDateTime;
import solid.humank.adapters.CloudwatchEventAdapter;
import solid.humank.adapters.OrderRepository;
import solid.humank.events.OrderEstablishedEvent;

@Data
public class Order {

    private int quantity;
    private String seatNo;
    private boolean drinkhere;
    private int price;
    private String coffeeItemName;
    private String establishedTime;
    private int drinkDegree;

    public Order(String seatNo, boolean ishere, String coffeeItemName, int quantity, int price) {
        this.seatNo = seatNo;
        this.drinkhere = ishere;
        this.coffeeItemName = coffeeItemName;
        this.quantity = quantity;
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCoffeeItemName() {
        return coffeeItemName;
    }

    public void setCoffeeItemName(String coffeeItemName) {
        this.coffeeItemName = coffeeItemName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean drinkHere() {
        return drinkhere;
    }

    public void setDrinkhere(boolean drinkhere) {
        this.drinkhere = drinkhere;
    }

    public String getSeatNo() {
        return seatNo;
    }

    public void setSeatNo(String seatNo) {
        this.seatNo = seatNo;
    }

    public String getEstablishedTime() {
        return establishedTime;
    }

    public void setEstablishedTime(String establishedTime) {
        this.establishedTime = establishedTime;
    }

    public int payAmount() {
        return this.getQuantity() * this.getPrice();

    }

    public String establish() {

        String orderString=null;
        this.establishedTime = new LocalDateTime().toString("yyyy-MM-dd:HH:mm:ss");

        if(this.drinkHere()){
            this.drinkDegree = 70;
        }else{
            this.drinkDegree = 100;
        }

        //save to repo
        OrderRepository repository = new OrderRepository();
        orderString = repository.saveOrder(this);

        //send event to makeup
        new CloudwatchEventAdapter().publishEvent(new OrderEstablishedEvent(this));

        return orderString;
    }

    public String getCoffeItemName() {
        return this.coffeeItemName;
    }

    public int getSeatDrinkDegree() {
        return this.drinkDegree;
    }
}
