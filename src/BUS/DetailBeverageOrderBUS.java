package BUS;

import DAO.DetailBeverageOrderDAO;
import DTO.DetailBeverageOrderDTO;

import java.util.List;

public class DetailBeverageOrderBUS {
    private DetailBeverageOrderDAO detailBeverageOrderDAO = new DetailBeverageOrderDAO();

    public static List<DetailBeverageOrderDTO> getAllDetailByOrderId(Integer orderId) {
        return DetailBeverageOrderDAO.getAllByOrderId(orderId);
    }
}
