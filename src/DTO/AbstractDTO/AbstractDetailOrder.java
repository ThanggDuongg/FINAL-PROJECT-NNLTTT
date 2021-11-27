package DTO.AbstractDTO;

public class AbstractDetailOrder {
    private Integer Id;
    private Integer IdOrder;
    private Integer IdProduct;
    private int quantity;

    public AbstractDetailOrder() {
    }

    public AbstractDetailOrder(Integer idOrder, Integer idProduct, int quantity) {
        IdOrder = idOrder;
        IdProduct = idProduct;
        this.quantity = quantity;
    }

    public AbstractDetailOrder(Integer id, Integer idOrder, Integer idProduct, int quantity) {
        Id = id;
        IdOrder = idOrder;
        IdProduct = idProduct;
        this.quantity = quantity;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public Integer getIdOrder() {
        return IdOrder;
    }

    public void setIdOrder(Integer idOrder) {
        IdOrder = idOrder;
    }

    public Integer getIdProduct() {
        return IdProduct;
    }

    public void setIdProduct(Integer idProduct) {
        IdProduct = idProduct;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
