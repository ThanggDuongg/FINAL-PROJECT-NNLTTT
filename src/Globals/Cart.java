package Globals;

import DTO.CartDTO;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private static List<CartDTO> ordersList = new ArrayList<>();

    public static List<CartDTO> getOrdersList() {
        return ordersList;
    }

    public static void setOrdersList(List<CartDTO> ordersList) {
        Cart.ordersList.addAll(ordersList);
    }
}
