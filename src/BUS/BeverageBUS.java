package BUS;

import DAO.BeverageDAO;
import DTO.BeverageDTO;

import java.util.List;

public class BeverageBUS {
    private BeverageDAO beverageDAO = new BeverageDAO();

    public boolean delete(Integer Id) {
        if (this.beverageDAO.delete(Id)) {
            return true;
        }
        return false;
    }

    public int update(Integer Id, String name, String manufacturer, int quantity, float price, float ABV) {
        BeverageDTO beverageDTO = new BeverageDTO(Id, name, price, quantity, manufacturer, ABV);
        if (checkNull(name, manufacturer, price, ABV)) {
            if (this.beverageDAO.update(beverageDTO)) {
                System.out.println("Successfully");
                return 1;
            }
            else {
                System.out.println("Unsuccessfully");
                return 2;
            }
        }
        else {
            System.out.println("Fields Null");
            return 3;
        }
    }

    public int insert(String name, String manufacturer, int quantity, float price, float ABV) {
        if (checkNull(name, manufacturer, price, ABV)) {
            BeverageDTO beverageDTO = new BeverageDTO(name, price, quantity, manufacturer, ABV);
            if (this.beverageDAO.insert(beverageDTO)) {
                System.out.println("Successfully");
                return 1;
            }
            else {
                System.out.println("Unsuccessfully");
                return 2;
            }
        }
        else {
            System.out.println("Fields Null");
            return 3;
        }
    }

    private static boolean checkNull(String name, String manufacturer, float price, float ABV) {
        if (name.equals("") || manufacturer.equals("") || price == 0 ) {
            return false;
        }
        return true;
    }

    public BeverageDTO findById(Integer Id) {
        return this.beverageDAO.findById(Id);
    }

    public List<BeverageDTO> beverageDTOList() {
        return this.beverageDAO.getAll();
    }
}
