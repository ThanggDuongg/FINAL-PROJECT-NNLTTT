package DTO.AbstractDTO;

public class AbstractMenu {
    private int daysinweek;
    private Integer id_product;

    public Integer getId_product() {
        return id_product;
    }

    public void setId_product(Integer id_product) {
        this.id_product = id_product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    private int quantity;

    public AbstractMenu() {
    }

    public AbstractMenu(int daysinweek, Integer id_product, int quantity) {
        this.daysinweek = daysinweek;
        this.id_product = id_product;
        this.quantity = quantity;
    }

    public int getDaysinweek() {
        return daysinweek;
    }

    public void setDaysinweek(int daysinweek) {
        this.daysinweek = daysinweek;
    }

}
