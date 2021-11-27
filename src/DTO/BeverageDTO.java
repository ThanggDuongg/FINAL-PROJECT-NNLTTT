package DTO;

import DTO.InterfaceDTO.IProductDTO;

public class BeverageDTO implements IProductDTO {
    private Integer Id;
    private String Name;
    private float Price;
    private int Quantity;
    private String Manufacturer;
    private float AcoholeByVolume;

    public BeverageDTO() {
    }

    public BeverageDTO(String name, float price, int quantity, String manufacturer, float acoholeByVolume) {
        Name = name;
        Price = price;
        Quantity = quantity;
        Manufacturer = manufacturer;
        AcoholeByVolume = acoholeByVolume;
    }

    public BeverageDTO(Integer id, String name, float price, int quantity, String manufacturer, float acoholeByVolume) {
        Id = id;
        Name = name;
        Price = price;
        Quantity = quantity;
        Manufacturer = manufacturer;
        AcoholeByVolume = acoholeByVolume;
    }

    public String getManufacturer() {
        return Manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        Manufacturer = manufacturer;
    }

    public float getAcoholeByVolume() {
        return AcoholeByVolume;
    }

    public void setAcoholeByVolume(float acoholeByVolume) {
        AcoholeByVolume = acoholeByVolume;
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
