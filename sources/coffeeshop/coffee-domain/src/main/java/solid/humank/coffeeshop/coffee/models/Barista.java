package solid.humank.coffeeshop.coffee.models;


import solid.humank.coffeeshop.orders.Order;

public class Barista {

    public void acceptOrder(Order receievedOrder){

        //Accept order
        //Verify raw material is enough or not
        //makeup Coffee
    }

    public Coffee makeupCoffee(Order receievedOrder){

        // Receieve order, and makeup it then deliver to customer.
        return new Coffee();
    }
}
