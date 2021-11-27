package BUS;

import DAO.AdministratorDAO;
import DAO.CustomerDAO;
import DAO.ShipperDAO;
import DAO.StatusOfShipperDAO;
import DTO.CustomerDTO;
import DTO.ShipperDTO;
import DTO.StatusOfShipperDTO;
import Globals.Globals;

import java.util.List;

public class ShipperBUS {
    private ShipperDAO shipperDAO = new ShipperDAO();
    private StatusOfShipperDAO statusOfShipperDAO = new StatusOfShipperDAO();

    public int insertAccount(String firstname, String lastname, String phone, String gender, int age, String email, String password, double salary){
        if (checkNullFieldsAccount(firstname, lastname, phone, gender, age, email, password) && salary != 0) {
            ShipperDTO shipperDTO = new ShipperDTO(firstname, lastname, phone, gender, age, email, password, salary);
            int idShipper = ShipperDAO.insertReturnId(shipperDTO);
            if (idShipper != -1) {
                statusOfShipperDAO.insert(new StatusOfShipperDTO(idShipper, false));
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

    public static int deleteByEmail(String email) {
        if (!email.equals("")) {
            if (CustomerDAO.deleteByEmail(email)) {
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

    public static ShipperDTO findByEmail(String Email) {
        return ShipperDAO.findByEmail(Email.trim());
    }

    public List<ShipperDTO> shipperDTOList() {
        return this.shipperDAO.getAll();
    }

    public ShipperDTO findById(Integer shipperId) {
        //Integer ShipperId = Globals.getGlobalShipperId();
        ShipperDTO shipperDTO = this.shipperDAO.findById(shipperId);
        return shipperDTO;
    }

    public int updateAccount(Integer Id, String firstname, String lastname, String phone, String gender, int age, double salary, String email, String password) {
        if (checkNullFieldsAccount(firstname, lastname, phone, gender, age, email, password)) {
            ShipperDTO shipperDTO = new ShipperDTO(Id, firstname, lastname, phone, gender, age, email, password, salary);
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
