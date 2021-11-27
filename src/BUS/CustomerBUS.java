package BUS;

import DAO.AdministratorDAO;
import DAO.CustomerDAO;
import DTO.CustomerDTO;
import Globals.Globals;

import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.List;

public class CustomerBUS {
    private CustomerDAO customerDAO = new CustomerDAO();

    public int insertAccount(String firstname, String lastname, String phone, String gender, int age, String address, String email, String password){
        double distance = this.randomeDistance();
        if (this.checkNullFieldsRegister(firstname, lastname, phone, gender, age, address, email, password, password)) {
            CustomerDTO customerDTO = new CustomerDTO(firstname, lastname, phone, gender, age, email, password, address, distance);
            if (this.customerDAO.insert(customerDTO)) {
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

    public static CustomerDTO findByEmail(String Email) {
        return CustomerDAO.findByEmail(Email.trim());
    }

    public List<CustomerDTO> customerDTOArrayList() {
        return this.customerDAO.getAll();
    }

    public CustomerDTO findById(Integer customerId) {
        //Integer customerId = Globals.getGlobalCustomerId();
        CustomerDTO customerDTO = this.customerDAO.findById(customerId);
        return customerDTO;
    }

    public int updateAccount(Integer Id, String firstname, String lastname, String phone, String gender, int age, String address, String email, String password, Double distance) {
        if (checkNullFieldsRegister(firstname, lastname, phone, gender, age, address, email, password, "a")) {
            CustomerDTO customerDTO = new CustomerDTO(Id, firstname, lastname, phone, gender, age, email, password, address, distance);
            if (this.customerDAO.update(customerDTO)) {
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

    public int forgotPassword(String email, String newpassword, String Retypenewpassword) {
        //check null
        if (BUS.checkNullFieldsLogin(email, newpassword) && BUS.checkNullFieldsLogin(email, Retypenewpassword)) {
            //check Existed mail
            if (!checkUniqueEmail(email)) {
                System.out.println("Email not existed");
                return 1;
            }
            else {
                CustomerDTO customerDTO = CustomerDAO.findByEmail(email);
                customerDTO.setPassword(newpassword);
                if (this.customerDAO.update(customerDTO)) {
                    System.out.println("Sucessfully");
                    return 2;
                }
                else {
                    System.out.println("Unsucessfully");
                    return 3;
                }
            }
        }
        else {
            System.out.println("Null fields");
            return 4;
        }
    }

    public static int checkLogin(String email, String password) {
        if (BUS.checkNullFieldsLogin(email, password)) {
            if (CustomerDAO.checkLogin(email, password)) {
                CustomerDTO customerDTO = CustomerDAO.findByEmail(email);
                Globals.setGlobalCustomerId(customerDTO.getID());
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

    public boolean checkNullFieldsRegister(String firstname, String lastname, String phone, String gender, int age, String address, String email, String password, String retypePassword) {
        if (firstname.equals("") || lastname.equals("") || phone.equals("") || gender.equals("") ||
        age == 0 || address.equals("") || email.equals("") || password.equals("") || retypePassword.equals("")) {
            return false;
        }
        return true;
    }

    public boolean check_Retype_Password(String password, String retypePassword) {
        return password.equals(retypePassword);
    }

    public static boolean checkUniqueEmail(String email) {
        return CustomerDAO.checkExistedEmail(email);
    }

    private double randomeDistance() {
        return (Math.random() * (50 - 1)) + 1;
    }

    public int Insert(String firstname, String lastname, String phone, String gender, int age, String address, String email, String password, String retypePassword) {
        if (this.checkNullFieldsRegister(firstname, lastname, phone, gender, age, address, email, password, retypePassword)) {
            //check password
            if (this.check_Retype_Password(password, retypePassword)) {
                //check email existed if true -> existed
                if (!this.checkUniqueEmail(email)) {
                    double distance = this.randomeDistance();
                    CustomerDTO customerDTO = new CustomerDTO(firstname, lastname, phone, gender, age, email, password, address, distance);
                    if (this.customerDAO.insert(customerDTO)) {
                        System.out.println("Sucessfully");
                        return 1;
                    }
                    else {
                        System.out.println("Unsucessfully");
                        return 2;
                    }
                }
                else {
                    System.out.println("email existed");
                    return 3;
                }
            }
            else {
                System.out.println("Password not match");
                return 4;
            }
        }
        else {
            System.out.println("Null Fields");
            return 5;
        }
    }
}
