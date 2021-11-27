package DTO;

import java.sql.Timestamp;
import java.util.Date;

public class OrderDTO {
    private Integer Id;
    private Timestamp dateOrder;
    private int quantity;
    private float total;
    private Integer IdCustomer;
    private Integer IdShipper;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    private boolean status;

    public OrderDTO(Timestamp dateOrder, int quantity, float total, Integer idCustomer, Integer idShipper, boolean status) {
        this.dateOrder = dateOrder;
        this.quantity = quantity;
        this.total = total;
        IdCustomer = idCustomer;
        IdShipper = idShipper;
        this.status = status;
    }

    public OrderDTO(Integer id, Timestamp dateOrder, int quantity, float total, Integer idCustomer, Integer idShipper, boolean status) {
        Id = id;
        this.dateOrder = dateOrder;
        this.quantity = quantity;
        this.total = total;
        IdCustomer = idCustomer;
        IdShipper = idShipper;
        this.status = status;
    }

    public OrderDTO() {
    }

    public OrderDTO(Timestamp dateOrder, int quantity, float total, Integer idCustomer, boolean status) {
        this.dateOrder = dateOrder;
        this.quantity = quantity;
        this.total = total;
        IdCustomer = idCustomer;
        this.status = status;
    }

    public OrderDTO(Integer id, Timestamp dateOrder, int quantity, float total, Integer idCustomer, boolean status) {
        Id = id;
        this.dateOrder = dateOrder;
        this.quantity = quantity;
        this.total = total;
        IdCustomer = idCustomer;
        this.status = status;
    }

    public Integer getIdShipper() {
        return IdShipper;
    }

    public void setIdShipper(Integer idShipper) {
        IdShipper = idShipper;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public Timestamp getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(Timestamp dateOrder) {
        this.dateOrder = dateOrder;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public Integer getIdCustomer() {
        return IdCustomer;
    }

    public void setIdCustomer(Integer idCustomer) {
        IdCustomer = idCustomer;
    }
}
