package DAO;

import DAO.DAOImpl.GenericDAO;
import DTO.DetailBeverageOrderDTO;
import DTO.DetailFoodOrderDTO;
import SQLConnection.MySQL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DetailBeverageOrderDAO implements GenericDAO<DetailBeverageOrderDTO> {
    @Override
    public List<DetailBeverageOrderDTO> getAll() {
        return null;
    }

    @Override
    public Boolean update(DetailBeverageOrderDTO DTO) {
        return null;
    }

    @Override
    public Boolean insert(DetailBeverageOrderDTO DTO) {
        boolean status = false;
        try {
            //insert query
            String query = "INSERT INTO detailbeverageorders(IdOrder, IdBeverage, quantity) " +
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
    public DetailBeverageOrderDTO findById(Integer Id) {
        return null;
    }

    @Override
    public boolean delete(Integer Id) {
        return false;
    }

    public static List<DetailBeverageOrderDTO> getAllByOrderId(Integer orderId) {
        ArrayList<DetailBeverageOrderDTO> detailBeverageOrderDTOArrayList = new ArrayList<>();
        try {
            //insert query
            String query = "SELECT * FROM detailbeverageorders where IdOrder = " + orderId;

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

                DetailBeverageOrderDTO detailBeverageOrderDTO = new DetailBeverageOrderDTO(Id, IdOrder, IdFood, quantity);
                detailBeverageOrderDTOArrayList.add(detailBeverageOrderDTO);
            }

            MySQL.getConnection().close();
        }
        catch (Exception ex) {
            System.err.println("Got an exception!");
            System.err.println(ex.getMessage());
        }
        finally {
            return detailBeverageOrderDTOArrayList;
        }
    }
}
