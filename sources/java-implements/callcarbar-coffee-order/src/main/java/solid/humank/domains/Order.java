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

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Data
public class Order{

    private List<CoffeeItem> acceptedOrderCoffeeItems;

    public boolean isHereOrToGo() {
        return hereOrToGo;
    }

    public String getSeatNo() {
        return seatNo;
    }

    private boolean hereOrToGo;

    public List<CoffeeItem> getAcceptedOrderCoffeeItems() {
        return acceptedOrderCoffeeItems;
    }

    public String getEstablishedTime() {
        return establishedTime;
    }

    private String establishedTime;
    private String seatNo;

    public Order(){
        acceptedOrderCoffeeItems = new ArrayList<CoffeeItem>();
    }


    public void accept(CoffeeItem americano) {
        acceptedOrderCoffeeItems.add(americano);
    }

    public void setHereOrToGo(boolean hereOrToGo) {
        this.hereOrToGo = hereOrToGo;
    }

    public int payAmount() {
        AtomicInteger payAmount = new AtomicInteger();
        acceptedOrderCoffeeItems.forEach(coffeeItem -> {
            payAmount.addAndGet(coffeeItem.getPrice());
        });

        return payAmount.get();
    }

    public int establish() {

        this.establishedTime = new LocalDateTime().toString("yyyy-MM-dd:HH:mm:ss");

        //save to repo
        OrderRepository repository = new OrderRepository();
        repository.saveOrder(this);

        //send event to makeup
        String json = "{\"eventname\":\"coffeepurchased\"}";
        new CloudwatchEventAdapter().putEvent(json,"Cofee",this.getClass().getPackage().getName());

        return 0;
    }

    public void setSeatNo(String seatNo) {
        this.seatNo = seatNo;
    }


}
