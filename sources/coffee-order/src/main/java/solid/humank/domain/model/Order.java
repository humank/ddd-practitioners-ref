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

package solid.humank.domain.model;

import lombok.Data;
import org.joda.time.LocalDateTime;
import solid.humank.port.adapter.OrderDTO;

@Data
public class Order {

    private int quantity;
    private String seatNo;
    private boolean drinkHere;
    private int price;
    private String itemName;
    private String establishTime;
    private int drinkTemperature;
    private OrderTicket orderTicket;

    public Order() {

    }

    public void serveConfirm(){
        if(drinkHere){
            drinkTemperature = 70;
        }
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

    public OrderTicket orderTicket() {
        return this.orderTicket;
    }

    public static Order create(OrderDTO orderDTO) {
        //把 orderDTO 轉為 Order
        Order purchaseOrder = new Order();
        purchaseOrder.setDrinkHere(orderDTO.isDrinkHere());
        purchaseOrder.setItemName(orderDTO.getItemName());
        purchaseOrder.setPrice(orderDTO.getPrice());
        purchaseOrder.setQuantity(orderDTO.getQuantity());
        purchaseOrder.setSeatNo(orderDTO.getSeatNo());

        purchaseOrder.setEstablishTime(new LocalDateTime().toString("yyyy-MM-dd:HH:mm:ss"));
        purchaseOrder.serveConfirm();
        return purchaseOrder;
    }
}
