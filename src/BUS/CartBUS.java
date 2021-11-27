package BUS;

import DTO.CartDTO;
import DTO.MenuBeverageDTO;
import DTO.MenuFoodDTO;
import Globals.Cart;

import java.util.List;

public class CartBUS {
    private MenuFoodBUS menuFoodBUS = new MenuFoodBUS();
    private MenuBeverageBUS menuBeverageBUS = new MenuBeverageBUS();

    public static void removeCart(Integer productId) {
        for (CartDTO cartDTO:Cart.getOrdersList()) {
            if (cartDTO.getProductId() == productId) {
                Cart.getOrdersList().remove(cartDTO);
                break;
            }
        }
    }

    //return
    public void returnMenu(int daysinweek) {
        for (CartDTO cartDTO:Cart.getOrdersList()) {
            int quantity = cartDTO.getQuantity();
            if (cartDTO.getTag().equals("Food")) {
                MenuFoodDTO menuFoodDTO = MenuFoodBUS.getByDaysAndId(cartDTO.getProductId(), daysinweek);
                this.menuFoodBUS.update(cartDTO.getProductId(), daysinweek, menuFoodDTO.getQuantity() + quantity);
            }
            else {
                MenuBeverageDTO menuBeverageDTO = MenuBeverageBUS.getByDaysAndId(cartDTO.getProductId(), daysinweek);
                this.menuBeverageBUS.update(cartDTO.getProductId(), daysinweek, menuBeverageDTO.getQuantity() + quantity);
            }
        }
        Cart.getOrdersList().clear();
    }

    //method add cart
    public static void addCart(Integer productId, String Tag) {
        int flag = checkExistProductInCart(productId, Tag);
        if (flag == -1) {
            CartDTO cartDTO = new CartDTO(productId,  1, Tag);
            Cart.getOrdersList().add(cartDTO);
        }
        else {
            int index = 0;
            for (CartDTO cartDTO:Cart.getOrdersList()) {
                if (cartDTO.getTag().equals(Tag)) {
                    if (cartDTO.getProductId() == productId) {
                        int quantity = cartDTO.getQuantity() + 1;
                        cartDTO.setQuantity(quantity);
                        break;
                    }
                }
            }
        }
    }

    public static int checkExistProductInCart(Integer productId, String Tag) {
        if (Cart.getOrdersList() != null) {
            for (int i = 0; i < Cart.getOrdersList().size(); i++) {
                if (Cart.getOrdersList().get(i).getTag().equals(Tag)) {
                    if (Cart.getOrdersList().get(i).getProductId() == productId) {
                        return productId;
                    }
                }
            }
            return -1;
        }
        return -1;
    }
}
