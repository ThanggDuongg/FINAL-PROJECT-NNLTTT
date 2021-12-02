package DAO;

import DAO.DAOImpl.GenericDAO;
import DTO.BeverageDTO;
import DTO.FoodDTO;
import SQLConnection.MySQL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FoodDAO implements GenericDAO<FoodDTO> {
    @Override
    public List<FoodDTO> getAll() {
        ArrayList<FoodDTO> foodDTOArrayList = new ArrayList<FoodDTO>();
        try {
            //insert query
            String query = "SELECT * FROM foods where Status = 1";

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
                Float Price = resultSet.getFloat(3);
                int Quantity = resultSet.getInt(4);

                FoodDTO foodDTO = new FoodDTO(Id, Name, Price, Quantity);
                foodDTOArrayList.add(foodDTO);
            }

            MySQL.getConnection().close();
        }
        catch (Exception ex) {
            System.err.println("Got an exception!");
            System.err.println(ex.getMessage());
        }
        finally {
            return foodDTOArrayList;
        }
    }

    @Override
    public Boolean update(FoodDTO DTO) {
        BeverageDTO beverageDTO = new BeverageDTO();
        boolean status = false;
        try {
            //insert query
            String query = "update foods set Name = ?, Price = ?, Quantity = ? where Id = ?";

            //create the mysql insert preparedstatement
            PreparedStatement preparedStmt = MySQL.getConnection().prepareStatement(query);
            preparedStmt.setString (1, DTO.getName());
            preparedStmt.setInt(3, DTO.getQuantity());
            preparedStmt.setFloat(2, DTO.getPrice());
            preparedStmt.setInt(4, DTO.getId());

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
    public Boolean insert(FoodDTO DTO) {
        boolean status = false;
        try {
            //insert query
            String query = "INSERT INTO foods(Name, Price, Quantity) " +
                    "values (?, ?, ?)";

            //create the mysql insert preparedstatement
            PreparedStatement preparedStmt = MySQL.getConnection().prepareStatement(query);
            preparedStmt.setString (1, DTO.getName());
            preparedStmt.setFloat (2, DTO.getPrice());
            preparedStmt.setInt  (3, DTO.getQuantity());

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
    public FoodDTO findById(Integer Id) {
        FoodDTO foodDTO = new FoodDTO();
        try {
            //insert query
            String query = "SELECT * FROM foods Where Id = ?";

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
                int Quantity = resultSet.getInt(4);
                float Price = resultSet.getFloat(3);

                // print the results
                foodDTO = new FoodDTO(Id, Name, Price, Quantity);
            }

            MySQL.getConnection().close();
        }
        catch (Exception ex) {
            System.err.println("Got an exception!");
            System.err.println(ex.getMessage());
        }
        finally {
            return foodDTO;
        }
    }

    @Override
    public boolean delete(Integer Id) {
        boolean status = false;
        try {
            //insert query
            String query = "update foods set Status = 0 where Id = ?";

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
