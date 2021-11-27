package BUS;

import DAO.MenuBeverageDAO;
import DAO.MenuFoodDAO;
import DTO.MenuBeverageDTO;
import DTO.MenuFoodDTO;

import java.util.List;

public class MenuBeverageBUS {
    MenuBeverageDAO beverageDAO = new MenuBeverageDAO();

    public static boolean removeFromMenu(Integer id_beverage, int daysinweek) {
        return MenuBeverageDAO.deleteMenuBeverage(id_beverage, daysinweek);
    }

    public void handleInsertFormListFoodToMenu(Integer id_beverage, int daysinweek) {
        if (checkFoodExistedInMeneAndDaysInWeek(id_beverage, daysinweek)) {
            MenuBeverageDTO menuBeverageDTO = getByDaysAndId(id_beverage, daysinweek);
            this.update(id_beverage, daysinweek, menuBeverageDTO.getQuantity() + 1);
        }
        else {
            this.insert(id_beverage, daysinweek);
        }
    }

    public static MenuBeverageDTO getByDaysAndId(Integer id_food, int daysinweek) {
        return MenuBeverageDAO.getByDaysAndId(id_food, daysinweek);
    }

    public boolean update(Integer id_food, int daysinweek, int quantity) {
        MenuBeverageDTO menuBeverageDTO = new MenuBeverageDTO(daysinweek, id_food, quantity);
        return this.beverageDAO.update(menuBeverageDTO);
    }

    public boolean insert(Integer id_food, int daysinweek) {
        MenuBeverageDTO menuBeverageDTO = new MenuBeverageDTO(daysinweek, id_food, 1);
        return this.beverageDAO.insert(menuBeverageDTO);
    }

    public static boolean checkFoodExistedInMeneAndDaysInWeek(Integer id_food, int daysinweek) {
        return MenuBeverageDAO.checkFoodExistedInMenuAndDaysInWeek(id_food, daysinweek);
    }

    public static List<MenuBeverageDTO> getMenuBeverageByDaysInWeek(int daysinweek) {
        return MenuBeverageDAO.getAllByDaysInWeek(daysinweek);
    }
}
