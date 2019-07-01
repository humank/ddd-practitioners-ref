package solid.humank.domain;

public class CoffeeItem {

    private String name;
    private int price;
    private int quantity;

    public CoffeeItem(){

    }
    public CoffeeItem(String name) {
        this.name = name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(int price) {

        this.price = price;
    }

    public int getPrice() {
        return this.price;
    }
}
