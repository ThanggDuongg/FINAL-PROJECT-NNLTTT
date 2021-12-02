package BUS;

import DAO.ReviewDAO;
import DTO.ReviewDTO;

import java.util.List;

public class ReviewBUS {
    private ReviewDAO reviewDAO = new ReviewDAO();

    public List<ReviewDTO> getAll() {
        return reviewDAO.getAll();
    }

    public static ReviewDTO getByCustomerAndOrder(Integer IdOrder, Integer IdCustomer) {
        return ReviewDAO.getByCustomerAndOrder(IdOrder, IdCustomer);
    }

    public boolean insert(ReviewDTO reviewDTO) {
        if (this.checkNullReview(reviewDTO)) {
            this.reviewDAO.insert(reviewDTO);
            return true;
        }
        else {
            return false;
        }
    }

    private boolean checkNullReview(ReviewDTO reviewDTO) {
        if (reviewDTO.getRating() < 0 || reviewDTO.getRating() > 5 || reviewDTO.getComment().equals("")
        || reviewDTO.getIdOrder() == null) {
            return false;
        }
        return true;
    }
}
