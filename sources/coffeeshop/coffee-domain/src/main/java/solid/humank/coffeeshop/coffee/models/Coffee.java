package solid.humank.coffeeshop.coffee.models;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import solid.humank.coffeeshop.coffee.commands.MakeCoffee;
import solid.humank.ddd.commons.baseclasses.AggregateRoot;

public class Coffee extends AggregateRoot<CoffeeId> {

    final static Logger logger = LoggerFactory.getLogger(Coffee.class);
    final static String ORDER_DATE_FORMAT = "yyyyMMddHHmmss";

    @Getter @Setter(AccessLevel.PRIVATE)
    private String tableNo;

    @Getter @Setter(AccessLevel.PRIVATE)
    private CoffeeStatus status;

    String orderId;

    public Coffee(String productId) {
    }

    public static Coffee make(MakeCoffee cmd) {
        return null;
    }
}
