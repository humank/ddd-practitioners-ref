package solid.humank.coffeeshop.coffee.applications;

import solid.humank.coffeeshop.coffee.commands.MakeCoffee;
import solid.humank.coffeeshop.coffee.datacontracts.messages.MakeCoffeeMsg;
import solid.humank.coffeeshop.coffee.datacontracts.results.CoffeeItemRst;
import solid.humank.coffeeshop.coffee.interfaces.ICoffeeRepository;
import solid.humank.coffeeshop.coffee.models.Coffee;
import solid.humank.coffeeshop.coffee.models.CoffeeItem;
import solid.humank.ddd.commons.interfaces.ITranslator;

import javax.inject.Inject;
import java.util.List;

public class MakeCoffeeSvc {

    @Inject
    public ICoffeeRepository repository;

    @Inject
    public ITranslator<List<CoffeeItem>, List<CoffeeItemRst>> translator;

    public void makeCoffee(MakeCoffeeMsg request){

        List<CoffeeItem> items = translator.translate(request.getItems());
        MakeCoffee cmd = new MakeCoffee();

        Coffee.make(cmd);
    }
}
