package BUS;

import DAO.AdministratorDAO;
import DAO.CustomerDAO;
import DTO.AdministratorDTO;
import Globals.Globals;

public class AdministratorBUS {
    private AdministratorDAO administratorDAO = new AdministratorDAO();

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
