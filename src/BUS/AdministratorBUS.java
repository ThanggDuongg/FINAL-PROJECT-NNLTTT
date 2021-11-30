package BUS;

import DAO.AdministratorDAO;
import DTO.AdministratorDTO;
import DTO.CustomerDTO;
import DTO.ShipperDTO;
import Globals.Globals;

import java.util.List;

public class AdministratorBUS {
    private AdministratorDAO administratorDAO = new AdministratorDAO();

    public static List<AdministratorDTO> getAll_Search(String search) {
        return AdministratorDAO.getAll_Search(search);
    }

    public int insertAccount(String firstname, String lastname, String phone, String gender, int age, String email, String password){
        if (checkNullFieldsRegister(firstname, lastname, phone, gender, age, email, password)) {
            AdministratorDTO administratorDTO = new AdministratorDTO(firstname, lastname, phone, gender, age, email, password);
            if (this.administratorDAO.insert(administratorDTO)) {
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

    public static AdministratorDTO findByEmail(String email) {
        return AdministratorDAO.findByEmail(email);
    }

    public List<AdministratorDTO> administratorDTOList() {
        return this.administratorDAO.getAll();
    }

    public static int deleteByEmail(String email) {
        if (!email.equals("")) {
            if (AdministratorDAO.deleteByEmail(email)) {
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

    public AdministratorDTO findById(Integer AdministratorId) {
//        Integer AdministratorId = Globals.getGlobalAdministratorId();
        AdministratorDTO administratorDTO = this.administratorDAO.findById(AdministratorId);
        return administratorDTO;
    }

    public int updateAccount(Integer Id, String firstname, String lastname, String phone, String gender, int age, String email, String password) {
        if (checkNullFieldsRegister(firstname, lastname, phone, gender, age, email, password)) {
            AdministratorDTO administratorDTO = new AdministratorDTO(Id, firstname, lastname, phone, gender, age, email, password);
            if (this.administratorDAO.update(administratorDTO)) {
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

    private static boolean checkNullFieldsRegister(String firstname, String lastname, String phone, String gender, int age, String email, String password) {
        if (firstname.equals("") || lastname.equals("") || phone.equals("") || gender.equals("") || age < 0 ||
        email.equals("") || password.equals("")) {
            return false;
        }
        return true;
    }

    public static int checkLogin(String email, String password) {
        if (BUS.checkNullFieldsLogin(email, password)) {
            if (AdministratorDAO.checkLogin(email, password)) {
                AdministratorDTO administratorDTO = AdministratorDAO.findByEmail(email);
                Globals.setGlobalAdministratorId(administratorDTO.getID());
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
