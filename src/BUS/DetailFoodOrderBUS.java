package BUS;

import DAO.DetailFoodOrderDAO;
import DTO.DetailFoodOrderDTO;

import java.util.List;

public class DetailFoodOrderBUS {
    private DetailFoodOrderDAO detailFoodOrderDAO = new DetailFoodOrderDAO();

    public static List<DetailFoodOrderDTO> getAllDetailByOrderId(Integer orderId) {
        return DetailFoodOrderDAO.getAllByOrderId(orderId);
    }
}
