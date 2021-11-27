package DTO;

public class ReviewDTO {
    private Integer Id;
    private int rating;
    private String comment;
    private Integer IdCustomer;
    private Integer IdOrder;

    public ReviewDTO() {
    }

    public ReviewDTO(int rating, String comment, Integer idCustomer, Integer idOrder) {
        this.rating = rating;
        this.comment = comment;
        IdCustomer = idCustomer;
        this.IdOrder = idOrder;
    }

    public ReviewDTO(Integer id, int rating, String comment, Integer idCustomer, Integer idOrder) {
        Id = id;
        this.rating = rating;
        this.comment = comment;
        IdCustomer = idCustomer;
        this.IdOrder = idOrder;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getIdCustomer() {
        return IdCustomer;
    }

    public void setIdCustomer(Integer idCustomer) {
        IdCustomer = idCustomer;
    }

    public Integer getIdOrder() {
        return IdOrder;
    }

    public void setIdOrder(Integer idOrder) {
        IdOrder = idOrder;
    }
}
