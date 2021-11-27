package DAO;

import DAO.DAOImpl.GenericDAO;
import DTO.AdministratorDTO;
import DTO.CustomerDTO;
import DTO.ShipperDTO;
import SQLConnection.MySQL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AdministratorDAO implements GenericDAO<AdministratorDTO> {

    @Override
    public List<AdministratorDTO> getAll() {
        ArrayList<AdministratorDTO> administratorDTOArrayList = new ArrayList<AdministratorDTO>();
        try {
            //insert query
            String query = "SELECT * FROM persons WHERE Role = 1";

            //create the mysql insert preparedstatement
            PreparedStatement preparedStmt = MySQL.getConnection().prepareStatement(query);

            System.out.println(preparedStmt);
            //execute the preparedstatement
            // execute the query, and get a java resultset
            ResultSet resultSet = preparedStmt.executeQuery(query);

            // iterate through the java resultset
            while (resultSet.next())
            {
                Integer ID = resultSet.getInt(1);
                String firstname = resultSet.getString(2);
                String lastname = resultSet.getString(3);
                String phone = resultSet.getString(4);
                String gender = resultSet.getString(5);
                int age = resultSet.getInt(6);
                String email = resultSet.getString(10);
                String password = resultSet.getString(11);

                AdministratorDTO administratorDTO = new AdministratorDTO(ID, firstname, lastname, phone, gender, age, email, password);
                administratorDTOArrayList.add(administratorDTO);
            }

            MySQL.getConnection().close();
        }
        catch (Exception ex) {
            System.err.println("Got an exception!");
            System.err.println(ex.getMessage());
        }
        finally {
            return administratorDTOArrayList;
        }
    }

    @Override
    public Boolean update(AdministratorDTO DTO) {
        boolean status = false;
        try {
            //insert query
            String query = "update persons set Firstname = ?, Lastname = ?, Phone = ?, Gender = ?, Age = ?, Email = ?, Password = ? where Id = ?";

            //create the mysql insert preparedstatement
            PreparedStatement preparedStmt = MySQL.getConnection().prepareStatement(query);
            preparedStmt.setInt (8, DTO.getID());
            preparedStmt.setString (1, DTO.getFirstname());
            preparedStmt.setString (2, DTO.getLastname());
            preparedStmt.setString  (3, DTO.getPhone());
            preparedStmt.setString(4, DTO.getGender());
            preparedStmt.setInt(5, DTO.getAge());
            preparedStmt.setString(6, DTO.getEmail());
            preparedStmt.setString(7, DTO.getPassword());

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
    public Boolean insert(AdministratorDTO DTO) {
        boolean status = false;
        try {
            //insert query
            String query = "INSERT INTO persons(Firstname, Lastname, Phone, Gender, Age, Email, Password, Role) " +
                    "values (?, ?, ?, ?, ?, ?, ?, ?)";

            //create the mysql insert preparedstatement
            PreparedStatement preparedStmt = MySQL.getConnection().prepareStatement(query);
            preparedStmt.setString (1, DTO.getFirstname());
            preparedStmt.setString (2, DTO.getLastname());
            preparedStmt.setString  (3, DTO.getPhone());
            preparedStmt.setString(4, DTO.getGender());
            preparedStmt.setInt(5, DTO.getAge());
            preparedStmt.setString(6, DTO.getEmail());
            preparedStmt.setString(7, DTO.getPassword());
            preparedStmt.setInt(8, 1);

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
    public AdministratorDTO findById(Integer Id) {
        AdministratorDTO administratorDTO = new AdministratorDTO();
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
                String email = resultSet.getString(10);
                String password = resultSet.getString(11);

                // print the results
                administratorDTO = new AdministratorDTO(Id, firstname, lastname, phone, gender, age, email, password);
            }

            MySQL.getConnection().close();
        }
        catch (Exception ex) {
            System.err.println("Got an exception!");
            System.err.println(ex.getMessage());
        }
        finally {
            return administratorDTO;
        }
    }

    @Override
    public boolean delete(Integer Id) {
        boolean status = false;
        try {
            //insert query
            String query = "DELETE FROM persons where Id = ?";

            //create the mysql insert preparedstatement
            PreparedStatement preparedStmt = MySQL.getConnection().prepareStatement(query);
            preparedStmt.setInt (1, Id);

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
            String query = "SELECT count(*) FROM persons Where Email = ? and Password = ? and Role = 1";

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

    public static AdministratorDTO findByEmail(String email) {
        AdministratorDTO administratorDTO = new AdministratorDTO();
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
                String password = resultSet.getString(11);

                // print the results
                administratorDTO = new AdministratorDTO(Id, firstname, lastname, phone, gender, age, email, password);
            }

            MySQL.getConnection().close();
        }
        catch (Exception ex) {
            System.err.println("Got an exception!");
            System.err.println(ex.getMessage());
        }
        finally {
            return administratorDTO;
        }
    }

    public static boolean deleteByEmail(String email) {
        boolean status = false;
        try {
            //insert query
            String query = "DELETE FROM persons where Email = ?";

            //create the mysql insert preparedstatement
            PreparedStatement preparedStmt = MySQL.getConnection().prepareStatement(query);
            preparedStmt.setString (1, email);

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
}
