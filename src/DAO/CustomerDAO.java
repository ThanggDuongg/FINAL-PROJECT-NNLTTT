package DAO;

import DAO.DAOImpl.GenericDAO;
import DTO.AdministratorDTO;
import DTO.CustomerDTO;
import SQLConnection.MySQL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class CustomerDAO implements GenericDAO<CustomerDTO> {

    @Override
    public List<CustomerDTO> getAll() {
        return null;
    }

    @Override
    public Boolean update(CustomerDTO DTO) {
        boolean status = false;
        try {
            //insert query
            String query = "update persons set Firstname = ?, Lastname = ?, Phone = ?, Gender = ?, Age = ?, Address = ?, Distance = ?, Email = ?, Password = ? where Id = ?";

            //create the mysql insert preparedstatement
            PreparedStatement preparedStmt = MySQL.getConnection().prepareStatement(query);
            preparedStmt.setInt (10, DTO.getID());
            preparedStmt.setString (1, DTO.getFirstname());
            preparedStmt.setString (2, DTO.getLastname());
            preparedStmt.setString  (3, DTO.getPhone());
            preparedStmt.setString(4, DTO.getGender());
            preparedStmt.setInt(5, DTO.getAge());
            preparedStmt.setString(6, DTO.getAddress());
            preparedStmt.setDouble(7, DTO.getDistance());
            preparedStmt.setString(8, DTO.getEmail());
            preparedStmt.setString(9, DTO.getPassword());

            System.out.println(preparedStmt);
            //execute the preparedstatement
            int result = preparedStmt.executeUpdate();

            status = result == 0 ? false : true;

            MySQL.getConnection().close();
        }
        catch (Exception ex) {
            System.err.println("Got an exception!");
            System.err.println(ex.getMessage());
        }
        finally {
            return status;
        }
    }

    @Override
    public Boolean insert(CustomerDTO DTO) {
        boolean status = false;
        try {
            //insert query
            String query = "INSERT INTO persons(Firstname, Lastname, Phone, Gender, Age, Address, Distance, Email, Password, Role) " +
                    "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            //create the mysql insert preparedstatement
            PreparedStatement preparedStmt = MySQL.getConnection().prepareStatement(query);
            preparedStmt.setString (1, DTO.getFirstname());
            preparedStmt.setString (2, DTO.getLastname());
            preparedStmt.setString  (3, DTO.getPhone());
            preparedStmt.setString(4, DTO.getGender());
            preparedStmt.setInt(5, DTO.getAge());
            preparedStmt.setString(6, DTO.getAddress());
            preparedStmt.setDouble(7, DTO.getDistance());
            preparedStmt.setString(8, DTO.getEmail());
            preparedStmt.setString(9, DTO.getPassword());
            preparedStmt.setInt(10, 2);

            System.out.println(preparedStmt);
            //execute the preparedstatement
            int result = preparedStmt.executeUpdate();

            status = result == 0 ? false : true;

            MySQL.getConnection().close();
        }
        catch (Exception ex) {
            System.err.println("Got an exception!");
            System.err.println(ex.getMessage());
        }
        finally {
            return status;
        }
    }

    @Override
    public CustomerDTO findById(Integer Id) {
        CustomerDTO customerDTO = new CustomerDTO();
        try {
            //insert query
            String query = "SELECT * FROM persons Where Id = ?";

            //create the mysql insert preparedstatement
            PreparedStatement preparedStmt = MySQL.getConnection().prepareStatement(query);
            preparedStmt.setInt (1, Id);

            System.out.println(preparedStmt);
            //execute the preparedstatement
            // execute the query, and get a java resultset
            ResultSet resultSet = preparedStmt.executeQuery();

            // iterate through the java resultset
            while (resultSet.next())
            {
                String firstname = resultSet.getString(2);
                String lastname = resultSet.getString(3);
                String phone = resultSet.getString(4);
                String gender = resultSet.getString(5);
                int age = resultSet.getInt(6);
                String address = resultSet.getString(7);
                Double distance = resultSet.getDouble(8);
                String email = resultSet.getString(10);
                String password = resultSet.getString(11);

                // print the results
                customerDTO = new CustomerDTO(Id, firstname, lastname, phone, gender, age, email, password, address, distance);
            }

            MySQL.getConnection().close();
        }
        catch (Exception ex) {
            System.err.println("Got an exception!");
            System.err.println(ex.getMessage());
        }
        finally {
            return customerDTO;
        }
    }

    @Override
    public boolean delete(Integer Id) {
        return false;
    }

    public static boolean checkExistedEmail(String email) {
        CustomerDTO customerDTO = new CustomerDTO();
        boolean flag = true;
        try {
            //insert query
            String query = "SELECT count(*) FROM persons Where Email = ?";

            //create the mysql insert preparedstatement
            PreparedStatement preparedStmt = MySQL.getConnection().prepareStatement(query);
            preparedStmt.setString (1, email);

            System.out.println(preparedStmt);
            //execute the preparedstatement
            // execute the query, and get a java resultset
            ResultSet resultSet = preparedStmt.executeQuery();

            // iterate through the java resultset
            int count = 0;
            while (resultSet.next()) {
                count = resultSet.getInt(1);
            }

            flag = count == 0 ? false : true;

            MySQL.getConnection().close();
        }
        catch (Exception ex) {
            System.err.println("Got an exception!");
            System.err.println(ex.getMessage());
        }
        finally {
            return flag;
        }
    }

    public static boolean checkLogin(String email, String password) {
        CustomerDTO customerDTO = new CustomerDTO();
        boolean flag = true;
        try {
            //insert query
            String query = "SELECT count(*) FROM persons Where Email = ? and Password = ? and Role = 2";

            //create the mysql insert preparedstatement
            PreparedStatement preparedStmt = MySQL.getConnection().prepareStatement(query);
            preparedStmt.setString (1, email);
            preparedStmt.setString(2, password);

            System.out.println(preparedStmt);
            //execute the preparedstatement
            // execute the query, and get a java resultset
            ResultSet resultSet = preparedStmt.executeQuery();

            // iterate through the java resultset
            int count = 0;
            while (resultSet.next()) {
                count = resultSet.getInt(1);
            }

            flag = count == 0 ? false : true;

            MySQL.getConnection().close();
        }
        catch (Exception ex) {
            System.err.println("Got an exception!");
            System.err.println(ex.getMessage());
        }
        finally {
            return flag;
        }
    }

    public static CustomerDTO findByEmail(String email) {
        CustomerDTO customerDTO = new CustomerDTO();
        try {
            //insert query
            String query = "SELECT * FROM persons Where Email = ?";

            //create the mysql insert preparedstatement
            PreparedStatement preparedStmt = MySQL.getConnection().prepareStatement(query);
            preparedStmt.setString (1, email);

            System.out.println(preparedStmt);
            //execute the preparedstatement
            // execute the query, and get a java resultset
            ResultSet resultSet = preparedStmt.executeQuery();

            // iterate through the java resultset
            while (resultSet.next())
            {
                Integer Id = resultSet.getInt(1);
                String firstname = resultSet.getString(2);
                String lastname = resultSet.getString(3);
                String phone = resultSet.getString(4);
                String gender = resultSet.getString(5);
                int age = resultSet.getInt(6);
                String address = resultSet.getString(7);
                Double distance = resultSet.getDouble(8);
                String password = resultSet.getString(11);

                // print the results
                customerDTO = new CustomerDTO(Id, firstname, lastname, phone, gender, age, email, password, address, distance);
            }

            MySQL.getConnection().close();
        }
        catch (Exception ex) {
            System.err.println("Got an exception!");
            System.err.println(ex.getMessage());
        }
        finally {
            return customerDTO;
        }
    }
}
