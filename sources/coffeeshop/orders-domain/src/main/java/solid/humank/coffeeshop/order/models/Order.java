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

package solid.humank.coffeeshop.order.models;

import lombok.Data;
import org.joda.time.LocalDateTime;
import solid.humank.coffeeshop.order.commands.CreateOrder;

import java.util.UUID;

@Data
public class Order {

    private UUID uuid;
    private int quantity;
    private String seatNo;
    private boolean drinkHere;
    private int price;
    private String itemName;
    private String establishTime;
    private int drinkTemperature;

    public Order() {

    }

    public void serveConfirm() {
        if (drinkHere) {
            drinkTemperature = 70;
        }
    }

    public Order(String seatNo, boolean isHere, String itemName, int quantity, int price) {
        this.seatNo = seatNo;
        this.drinkHere = isHere;
        this.itemName = itemName;
        this.quantity = quantity;
        this.price = price;
    }

    public int payAmount() {
        return this.getQuantity() * this.getPrice();

    }

    public static Order create(CreateOrder cmd) {
        //把 orderDTO 轉為 Order
        Order purchaseOrder = new Order();
        purchaseOrder.setDrinkHere(cmd.isDrinkHere());
        purchaseOrder.setItemName(cmd.getItemName());
        purchaseOrder.setPrice(cmd.getPrice());
        purchaseOrder.setQuantity(cmd.getQuantity());
        purchaseOrder.setSeatNo(cmd.getSeatNo());
        purchaseOrder.setEstablishTime(new LocalDateTime().toString("yyyy-MM-dd:HH:mm:ss"));
        purchaseOrder.serveConfirm();
        return purchaseOrder;
    }
}
