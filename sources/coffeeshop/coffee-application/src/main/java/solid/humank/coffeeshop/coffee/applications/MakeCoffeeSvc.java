package solid.humank.coffeeshop.coffee.applications;

import solid.humank.coffeeshop.coffee.commands.MakeCoffee;
import solid.humank.coffeeshop.coffee.datacontracts.messages.MakeCoffeeMsg;
import solid.humank.coffeeshop.coffee.datacontracts.results.CoffeeItemRst;
import solid.humank.coffeeshop.coffee.datacontracts.results.CoffeeRst;
import solid.humank.coffeeshop.coffee.interfaces.ICoffeeRepository;
import solid.humank.coffeeshop.coffee.models.Coffee;
import solid.humank.coffeeshop.coffee.models.CoffeeId;
import solid.humank.coffeeshop.coffee.models.CoffeeItem;
import solid.humank.ddd.commons.interfaces.ITranslator;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class MakeCoffeeSvc {

    @Inject
    public ICoffeeRepository repository;

    @Inject
    public ITranslator<List<CoffeeItem>, List<CoffeeItemRst>> translator;

    public CoffeeRst makeCoffee(MakeCoffeeMsg request){

        List<CoffeeItem> items = translator.translate(request.getItems());
        List<Coffee> madeCoffees = new ArrayList<>();

        items.stream().forEach(coffeeItem -> {
            CoffeeId coffeeId = this.repository.generateCoffeeId();
            MakeCoffee cmd = new MakeCoffee();
            Coffee madeCoffee = Coffee.make(cmd);
            madeCoffees.add(madeCoffee);
            this.repository.save(madeCoffee);
        });

        return new CoffeeRst(madeCoffees);
    }
}
