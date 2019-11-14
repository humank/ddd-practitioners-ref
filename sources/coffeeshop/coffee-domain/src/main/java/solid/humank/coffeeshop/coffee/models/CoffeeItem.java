package solid.humank.coffeeshop.coffee.models;

import lombok.Data;
import solid.humank.ddd.commons.baseclasses.ValueObject;

@Data
public class CoffeeItem  extends ValueObject<CoffeeItem> {

    private String name;
    private int price;
    private int quantity;

    public CoffeeItem() {

    }

    public CoffeeItem(String name) {
        this.name = name;
    }

}
