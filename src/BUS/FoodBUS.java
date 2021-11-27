package BUS;

import DAO.FoodDAO;
import DTO.FoodDTO;

import java.util.List;

public class FoodBUS {
    private FoodDAO foodDAO = new FoodDAO();

    public boolean delete(Integer Id) {
        if (this.foodDAO.delete(Id)) {
            System.out.println("Successfully");
            return true;
        }
        return false;
    }

    public int update(Integer Id, String name, float price, int quantity) {
        if (checkNull(name, price)) {
            FoodDTO foodDTO = new FoodDTO(Id, name, price, quantity);
            if (this.foodDAO.update(foodDTO)) {
                System.out.println("Successfully");
                return 1;
            }
            else {
                System.out.println("Unsuccessfuly");
                return 2;
            }
        }
        else {
            System.out.println("Fields Null");
            return 3;
        }
    }

    public int insert(String name, float price, int quantity) {
        if (checkNull(name, price)) {
            FoodDTO foodDTO = new FoodDTO(name, price, quantity);
            if (this.foodDAO.insert(foodDTO)) {
                System.out.println("Successfully");
                return 1;
            }
            else {
                System.out.println("Unsuccessfuly");
                return 2;
            }
        }
        else {
            System.out.println("Fields Null");
            return 3;
        }
    }

    public List<FoodDTO> foodDTOList() {
        return this.foodDAO.getAll();
    }

    public FoodDTO findById(Integer Id) {
        return this.foodDAO.findById(Id);
    }

    private static boolean checkNull(String name, float price) {
        if (name.equals("") || price == 0) {
            return false;
        }
        return true;
    }
}
