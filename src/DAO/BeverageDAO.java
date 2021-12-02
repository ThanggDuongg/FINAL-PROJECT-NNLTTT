package DAO;

import DAO.DAOImpl.GenericDAO;
import DTO.AdministratorDTO;
import DTO.BeverageDTO;
import SQLConnection.MySQL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BeverageDAO implements GenericDAO<BeverageDTO> {
    @Override
    public List<BeverageDTO> getAll() {
        ArrayList<BeverageDTO> beverageDTOArrayList = new ArrayList<BeverageDTO>();
        try {
            //insert query
            String query = "SELECT * FROM beverages WHERE Status = 1";

            //create the mysql insert preparedstatement
            PreparedStatement preparedStmt = MySQL.getConnection().prepareStatement(query);

            System.out.println(preparedStmt);
            //execute the preparedstatement
            // execute the query, and get a java resultset
            ResultSet resultSet = preparedStmt.executeQuery(query);

            // iterate through the java resultset
            while (resultSet.next())
            {
                Integer Id = resultSet.getInt(1);
                String Name = resultSet.getString(2);
                String Manufacturer = resultSet.getString(3);
                int Quantity = resultSet.getInt(4);
                Float Price = resultSet.getFloat(5);
                Float AcoholeByVolume = resultSet.getFloat(6);

                BeverageDTO beverageDTO = new BeverageDTO(Id, Name, Price, Quantity, Manufacturer, AcoholeByVolume);
                beverageDTOArrayList.add(beverageDTO);
            }

            MySQL.getConnection().close();
        }
        catch (Exception ex) {
            System.err.println("Got an exception!");
            System.err.println(ex.getMessage());
        }
        finally {
            return beverageDTOArrayList;
        }
    }

    @Override
    public Boolean update(BeverageDTO DTO) {
        BeverageDTO beverageDTO = new BeverageDTO();
        boolean status = false;
        try {
            //insert query
            String query = "update beverages set Name = ?, Manufacturer = ?, Quantity = ?, Price = ?, AcoholeByVolume = ? where Id = ?";

            //create the mysql insert preparedstatement
            PreparedStatement preparedStmt = MySQL.getConnection().prepareStatement(query);
            preparedStmt.setString (1, DTO.getName());
            preparedStmt.setString(2, DTO.getManufacturer());
            preparedStmt.setInt(3, DTO.getQuantity());
            preparedStmt.setFloat(4, DTO.getPrice());
            preparedStmt.setFloat(5, DTO.getAcoholeByVolume());
            preparedStmt.setInt(6, DTO.getId());

            System.out.println(preparedStmt);
            //execute the preparedstatement
            // execute the query, and get a java resultset
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
    public Boolean insert(BeverageDTO DTO) {
        boolean status = false;
        try {
            //insert query
            String query = "INSERT INTO beverages(Name, Manufacturer, Quantity, Price, AcoholeByVolume) " +
                    "values (?, ?, ?, ?, ?)";

            //create the mysql insert preparedstatement
            PreparedStatement preparedStmt = MySQL.getConnection().prepareStatement(query);
            preparedStmt.setString (1, DTO.getName());
            preparedStmt.setString (2, DTO.getManufacturer());
            preparedStmt.setInt  (3, DTO.getQuantity());
            preparedStmt.setFloat(4, DTO.getPrice());
            preparedStmt.setFloat(5, DTO.getAcoholeByVolume());

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
    public BeverageDTO findById(Integer Id) {
        BeverageDTO beverageDTO = new BeverageDTO();
        try {
            //insert query
            String query = "SELECT * FROM beverages Where Id = ?";

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
                String Name = resultSet.getString(2);
                String Manufacturer = resultSet.getString(3);
                int Quantity = resultSet.getInt(4);
                float Price = resultSet.getFloat(5);
                float abv = resultSet.getFloat(6);

                // print the results
                beverageDTO = new BeverageDTO(Id, Name, Price, Quantity, Manufacturer, abv);
            }

            MySQL.getConnection().close();
        }
        catch (Exception ex) {
            System.err.println("Got an exception!");
            System.err.println(ex.getMessage());
        }
        finally {
            return beverageDTO;
        }
    }

    @Override
    public boolean delete(Integer Id) {
        boolean status = false;
        try {
            //insert query
            String query = "update beverages set Status = 0 where Id = ?";

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
}
