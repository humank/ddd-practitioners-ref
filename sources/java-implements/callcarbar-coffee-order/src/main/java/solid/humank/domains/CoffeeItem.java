package solid.humank.domains;

public class CoffeeItem {

    private String name;
    private int price;

    public CoffeeItem(String name) {
        this.name = name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public void setPrice(int price) {

        this.price = price;
    }

    public int getPrice() {
        return this.price;
    }
}
