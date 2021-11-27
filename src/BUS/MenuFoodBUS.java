package BUS;

import DAO.MenuFoodDAO;
import DTO.MenuFoodDTO;

import java.util.List;

public class MenuFoodBUS {
    private MenuFoodDAO menuFoodDAO = new MenuFoodDAO();

    public static boolean removeFromMenu(Integer id_food, int daysinweek) {
        return MenuFoodDAO.deleteMenuFood(id_food, daysinweek);
    }

    public void handleInsertFormListFoodToMenu(Integer id_food, int daysinweek) {
        if (checkFoodExistedInMeneAndDaysInWeek(id_food, daysinweek)) {
            MenuFoodDTO menuFoodDTO = getByDaysAndId(id_food, daysinweek);
            this.update(id_food, daysinweek, menuFoodDTO.getQuantity() + 1);
        }
        else {
            this.insert(id_food, daysinweek);
        }
    }

    public static MenuFoodDTO getByDaysAndId(Integer id_food, int daysinweek) {
        return MenuFoodDAO.getByDaysAndId(id_food, daysinweek);
    }

    public boolean update(Integer id_food, int daysinweek, int quantity) {
        MenuFoodDTO menuFoodDTO = new MenuFoodDTO(daysinweek, id_food, quantity);
        return this.menuFoodDAO.update(menuFoodDTO);
    }

    public boolean insert(Integer id_food, int daysinweek) {
        MenuFoodDTO menuFoodDTO = new MenuFoodDTO(daysinweek, id_food, 1);
        return this.menuFoodDAO.insert(menuFoodDTO);
    }

    public static boolean checkFoodExistedInMeneAndDaysInWeek(Integer id_food, int daysinweek) {
        return MenuFoodDAO.checkFoodExistedInMenuAndDaysInWeek(id_food, daysinweek);
    }

    public static List<MenuFoodDTO> getMenuFoodByDaysInWeek(int daysinweek) {
        return MenuFoodDAO.getAllByDaysInWeek(daysinweek);
    }
}
