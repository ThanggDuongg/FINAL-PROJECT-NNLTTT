package BUS;

import DAO.ShipperDAO;
import DTO.ShipperDTO;
import Globals.Globals;

public class ShipperBUS {
    private ShipperDAO shipperDAO = new ShipperDAO();

    public int indexGender(String gender) {
        if (gender.equals("Male")) {
            return 0;
        }
        else if (gender.equals("Female")) {
            return 1;
        }
        else {
            return 2;
        }
    }

    public ShipperDTO findById() {
        Integer ShipperId = Globals.getGlobalShipperId();
        ShipperDTO shipperDTO = this.shipperDAO.findById(ShipperId);
        return shipperDTO;
    }

    public int updateAccount(String firstname, String lastname, String phone, String gender, int age, double salary, String email, String password) {
        if (checkNullFieldsAccount(firstname, lastname, phone, gender, age, email, password)) {
            Integer shipperId = Globals.getGlobalShipperId();
            ShipperDTO shipperDTO = new ShipperDTO(shipperId, firstname, lastname, phone, gender, age, email, password, salary);
            if (this.shipperDAO.update(shipperDTO)) {
                System.out.println("Sucessfully");
                return 1;
            }
            else {
                System.out.println("Unsucessfully");
                return 2;
            }
        }
        else {
            System.out.println("Fields Null");
            return 3;
        }
    }

    public static boolean checkNullFieldsAccount(String firstname, String lastname, String phone, String gender, int age, String email, String password) {
        if (firstname.equals("") || lastname.equals("") || phone.equals("") || gender.equals("") ||
                age == 0 || email.equals("") || password.equals("")) {
            return false;
        }
        return true;
    }


    public static int checkLogin(String email, String password) {
        if (BUS.checkNullFieldsLogin(email, password)) {
            if (ShipperDAO.checkLogin(email, password)) {
                ShipperDTO shipperDTO = ShipperDAO.findByEmail(email);
                Globals.setGlobalShipperId(shipperDTO.getID());
                System.out.println("Sucessfully");
                return 1;
            }
            else {
                System.out.println("Unsucessfully");
                return 2;
            }
        }
        else {
            System.out.println("Null fields");
            return 3;
        }
    }
}
