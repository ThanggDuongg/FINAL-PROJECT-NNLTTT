package DAO;

import DAO.DAOImpl.GenericDAO;
import DTO.CustomerDTO;
import DTO.ShipperDTO;
import SQLConnection.MySQL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class ShipperDAO implements GenericDAO<ShipperDTO> {
    @Override
    public List<ShipperDTO> getAll() {
        return null;
    }

    @Override
    public Boolean update(ShipperDTO DTO) {
        boolean status = false;
        try {
            //insert query
            String query = "update persons set Firstname = ?, Lastname = ?, Phone = ?, Gender = ?, Age = ?, Salary = ?, Email = ?, Password = ? where Id = ?";

            //create the mysql insert preparedstatement
            PreparedStatement preparedStmt = MySQL.getConnection().prepareStatement(query);
            preparedStmt.setInt (9, DTO.getID());
            preparedStmt.setString (1, DTO.getFirstname());
            preparedStmt.setString (2, DTO.getLastname());
            preparedStmt.setString  (3, DTO.getPhone());
            preparedStmt.setString(4, DTO.getGender());
            preparedStmt.setInt(5, DTO.getAge());
            preparedStmt.setDouble(6, DTO.getSalary());
            preparedStmt.setString(7, DTO.getEmail());
            preparedStmt.setString(8, DTO.getPassword());

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
    public Boolean insert(ShipperDTO DTO) {
        return null;
    }

    @Override
    public ShipperDTO findById(Integer Id) {
        ShipperDTO shipperDTO = new ShipperDTO();
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
                Double salary = resultSet.getDouble(9);
                String email = resultSet.getString(10);
                String password = resultSet.getString(11);

                // print the results
                shipperDTO = new ShipperDTO(Id, firstname, lastname, phone, gender, age, email, password, salary);
            }

            MySQL.getConnection().close();
        }
        catch (Exception ex) {
            System.err.println("Got an exception!");
            System.err.println(ex.getMessage());
        }
        finally {
            return shipperDTO;
        }
    }

    @Override
    public boolean delete(Integer Id) {
        return false;
    }

    public static boolean checkLogin(String email, String password) {
        CustomerDTO customerDTO = new CustomerDTO();
        boolean flag = true;
        try {
            //insert query
            String query = "SELECT count(*) FROM persons Where Email = ? and Password = ? and Role = 3";

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

    public static ShipperDTO findByEmail(String email) {
        ShipperDTO shipperDTO = new ShipperDTO();
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
                Double salary = resultSet.getDouble(9);
                String password = resultSet.getString(11);

                // print the results
                shipperDTO = new ShipperDTO(Id, firstname, lastname, phone, gender, age, email, password, salary);
            }

            MySQL.getConnection().close();
        }
        catch (Exception ex) {
            System.err.println("Got an exception!");
            System.err.println(ex.getMessage());
        }
        finally {
            return shipperDTO;
        }
    }
}
