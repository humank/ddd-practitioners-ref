package solid.humank.coffeeshop.order.domainservices;

public class OrderDTO {
    private int quantity;
    private String seatNo;
    private boolean drinkHere;
    private int price;
    private String itemName;
    private int drinktemperature;

    public OrderDTO(String seatNo, boolean ishere, String itemName, int quantity, int price) {
        this.seatNo = seatNo;
        this.drinkHere = ishere;
        this.itemName = itemName;
        this.quantity = quantity;
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getSeatNo() {
        return seatNo;
    }

    public void setSeatNo(String seatNo) {
        this.seatNo = seatNo;
    }

    public boolean isDrinkHere() {
        return drinkHere;
    }

    public void setDrinkHere(boolean drinkHere) {
        this.drinkHere = drinkHere;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getDrinktemperature() {
        return drinktemperature;
    }

    public void setDrinktemperature(int drinktemperature) {
        this.drinktemperature = drinktemperature;
    }
}
