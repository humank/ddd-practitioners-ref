package solid.humank.domains;

public class ProductItem {

    private String name;
    private int price;

    public ProductItem(String name) {
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
