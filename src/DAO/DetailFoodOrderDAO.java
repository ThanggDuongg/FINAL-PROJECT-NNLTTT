package DAO;

import DAO.DAOImpl.GenericDAO;
import DTO.DetailFoodOrderDTO;
import DTO.OrderDTO;
import SQLConnection.MySQL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class DetailFoodOrderDAO implements GenericDAO<DetailFoodOrderDTO> {
    @Override
    public List<DetailFoodOrderDTO> getAll() {
        return null;
    }

    @Override
    public Boolean update(DetailFoodOrderDTO DTO) {
        return null;
    }

    @Override
    public Boolean insert(DetailFoodOrderDTO DTO) {
        boolean status = false;
        try {
            //insert query
            String query = "INSERT INTO detailfoodorders(IdOrder, IdFood, quantity) " +
                    "values (?, ?, ?)";

            //create the mysql insert preparedstatement
            PreparedStatement preparedStmt = MySQL.getConnection().prepareStatement(query);
            preparedStmt.setInt(1, DTO.getIdOrder());
            preparedStmt.setInt  (2, DTO.getIdProduct());
            preparedStmt.setFloat (3, DTO.getQuantity());

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
    public DetailFoodOrderDTO findById(Integer Id) {
        return null;
    }

    @Override
    public boolean delete(Integer Id) {
        return false;
    }

    public static List<DetailFoodOrderDTO> getAllByOrderId(Integer orderId) {
        ArrayList<DetailFoodOrderDTO> detailFoodOrderDTOArrayList = new ArrayList<>();
        try {
            //insert query
            String query = "SELECT * FROM detailfoodorders where IdOrder = " + orderId;

            //create the mysql insert preparedstatement
            PreparedStatement preparedStmt = MySQL.getConnection().prepareStatement(query);
            //preparedStmt.setInt(1, IdCustomer);

            System.out.println(preparedStmt);
            //execute the preparedstatement
            // execute the query, and get a java resultset
            ResultSet resultSet = preparedStmt.executeQuery(query);

            // iterate through the java resultset
            while (resultSet.next())
            {
                Integer Id = resultSet.getInt(1);
                Integer IdOrder = orderId;
                Integer IdFood = resultSet.getInt(3);
                int quantity = resultSet.getInt(4);

                DetailFoodOrderDTO detailFoodOrderDTO = new DetailFoodOrderDTO(Id, IdOrder, IdFood, quantity);
                detailFoodOrderDTOArrayList.add(detailFoodOrderDTO);
            }

            MySQL.getConnection().close();
        }
        catch (Exception ex) {
            System.err.println("Got an exception!");
            System.err.println(ex.getMessage());
        }
        finally {
            return detailFoodOrderDTOArrayList;
        }
    }
}
