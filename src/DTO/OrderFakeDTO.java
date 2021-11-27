package DTO;

public class OrderFakeDTO {
    private Integer productId;
    private int quantity;
    private String tag;

    public OrderFakeDTO() {
    }

    public OrderFakeDTO(Integer productId, int quantity, String tag) {
        this.productId = productId;
        this.quantity = quantity;
        this.tag = tag;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
