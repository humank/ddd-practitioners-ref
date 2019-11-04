package solid.humank.coffee.inventory.controllers;

import javax.ws.rs.PUT;
import javax.ws.rs.Path;

@Path("/inventory")
public class InventoryResource {

    @PUT
    public String takeOut(CoffeeBean coffeeBean){

        return "success-200";
    }
}
