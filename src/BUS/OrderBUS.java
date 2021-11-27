package BUS;

import DAO.DetailBeverageOrderDAO;
import DAO.DetailFoodOrderDAO;
import DAO.OrderDAO;
import DTO.*;
import Globals.Globals;
import com.mysql.cj.x.protobuf.MysqlxCrud;

import java.sql.Timestamp;
import java.util.List;

public class OrderBUS {
    private OrderDAO orderDAO = new OrderDAO();
    private DetailBeverageOrderDAO detailBeverageOrderDAO = new DetailBeverageOrderDAO();
    private DetailFoodOrderDAO detailFoodOrderDAO = new DetailFoodOrderDAO();
    private FoodBUS foodBUS = new FoodBUS();
    private BeverageBUS beverageBUS = new BeverageBUS();

    public static List<OrderDTO> getAllOrdersWithStatusTrue(int index) {
        return OrderDAO.getAllOrdersWithStatusTrue(index);
    }

    public float realCost(Integer IdOrder) {
        float realCost = 0;
        List<DetailFoodOrderDTO> detailFoodOrderDTOList = DetailFoodOrderBUS.getAllDetailByOrderId(IdOrder);
        for (DetailFoodOrderDTO detailFoodOrderDTO: detailFoodOrderDTOList) {
            FoodDTO foodDTO = foodBUS.findById(detailFoodOrderDTO.getIdProduct());
            float priceFood = foodDTO.getPrice();
            realCost += priceFood * detailFoodOrderDTO.getQuantity();
        }

        List<DetailBeverageOrderDTO> detailBeverageOrderDTOList = DetailBeverageOrderBUS.getAllDetailByOrderId(IdOrder);
        for (DetailBeverageOrderDTO detailBeverageOrderDTO: detailBeverageOrderDTOList) {
            float priceBeverage = beverageBUS.findById(detailBeverageOrderDTO.getIdProduct()).getPrice();
            realCost += priceBeverage * detailBeverageOrderDTO.getQuantity();
        }

        return realCost;
    }

    public static List<OrderDTO> getAllOrderOfShipper_StatusFalse(Integer IdShipper) {
        return OrderDAO.getAllOrderOfShipper_StatusFalse(IdShipper);
    }

    public boolean updateShipperForOrder(OrderDTO orderDTO) {
        return this.orderDAO.update(orderDTO);
    }

    public static int numOfOrdersByShipper_Today(Integer IdShipper) {
        return OrderDAO.numOfOrdersByShipper_Today(IdShipper);
    }

    public static List<OrderDTO> getAllOrder_noShipper() {
        return OrderDAO.getAllOrder_noShipper();
    }

    public OrderDTO findById(Integer orderId) {
        return this.orderDAO.findById(orderId);
    }

    public boolean insert(List<OrderFakeDTO> orderFakeDTOList, float total) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        OrderDTO orderDTO = new OrderDTO(timestamp, orderFakeDTOList.size(), total, Globals.getGlobalCustomerId(), false);
        int insertReturnId = this.orderDAO.insertReturnId(orderDTO);
        if (insertReturnId != -1) {
            for (OrderFakeDTO orderFakeDTO : orderFakeDTOList) {
                if (orderFakeDTO.getTag().equals("Food")) {
                    DetailFoodOrderDTO detailFoodOrderDTO = new DetailFoodOrderDTO(insertReturnId, orderFakeDTO.getProductId(), orderFakeDTO.getQuantity());
                    this.detailFoodOrderDAO.insert(detailFoodOrderDTO);
                }
                else {
                    DetailBeverageOrderDTO detailBeverageOrderDTO = new DetailBeverageOrderDTO(insertReturnId, orderFakeDTO.getProductId(), orderFakeDTO.getQuantity());
                    this.detailBeverageOrderDAO.insert(detailBeverageOrderDTO);
                }
            }
            return true;
        }
        return false;
    }

    public static List<OrderDTO> getAllOrderByIdCustomer() {
        return OrderDAO.getAllByIdCustomerHistory(Globals.getGlobalCustomerId());
    }
}
