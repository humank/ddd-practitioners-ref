package solid.humank.coffeeshop.order.domains;

public class OrderReadModel {

    public OrderReadModel(String orderString) {
        this.orderString = orderString;
    }

    public String getOrderString() {
        return orderString;
    }

    public void setOrderString(String orderString) {
        this.orderString = orderString;
    }

    private String orderString;
}
