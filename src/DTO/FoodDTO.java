package DTO;

import DTO.InterfaceDTO.IProductDTO;

public class FoodDTO implements IProductDTO {
    private Integer Id;
    private String Name;
    private float Price;
    private int Quantity;

    public FoodDTO() {
    }

    public FoodDTO(String name, float price, int quantity) {
        Name = name;
        Price = price;
        Quantity = quantity;
    }

    public FoodDTO(Integer id, String name, float price, int quantity) {
        Id = id;
        Name = name;
        Price = price;
        Quantity = quantity;
    }

    @Override
    public Integer getId() {
        return this.Id;
    }

    @Override
    public void setId(Integer Id) {
        this.Id = Id;
    }

    @Override
    public String getName() {
        return this.Name;
    }

    @Override
    public void setName(String Name) {
        this.Name = Name;
    }

    @Override
    public float getPrice() {
        return this.Price;
    }

    @Override
    public void setPrice(float Price) {
        this.Price = Price;
    }

    @Override
    public int getQuantity() {
        return this.Quantity;
    }

    @Override
    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }
}
