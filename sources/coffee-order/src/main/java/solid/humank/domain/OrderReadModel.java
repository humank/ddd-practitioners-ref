package solid.humank.domain;

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
