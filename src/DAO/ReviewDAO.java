package DAO;

import DAO.DAOImpl.GenericDAO;
import DTO.OrderDTO;
import DTO.ReviewDTO;
import SQLConnection.MySQL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ReviewDAO implements GenericDAO<ReviewDTO> {
    @Override
    public List<ReviewDTO> getAll() {
        ArrayList<ReviewDTO> reviewDTOArrayList = new ArrayList<>();
        try {
            //insert query
            String query = "SELECT * FROM reviews ORDER BY rating desc";

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
                int rating = resultSet.getInt(2);
                String comment = resultSet.getString(3);
                Integer IdCustomer = resultSet.getInt(4);
                Integer IdOrder = resultSet.getInt(5);

                ReviewDTO reviewDTO = new ReviewDTO(Id, rating, comment, IdCustomer, IdOrder);
                reviewDTOArrayList.add(reviewDTO);
            }

            MySQL.getConnection().close();
        }
        catch (Exception ex) {
            System.err.println("Got an exception!");
            System.err.println(ex.getMessage());
        }
        finally {
            return reviewDTOArrayList;
        }
    }

    @Override
    public Boolean update(ReviewDTO DTO) {
        return null;
    }

    @Override
    public Boolean insert(ReviewDTO DTO) {
        boolean status = false;
        try {
            //insert query
            String query = "INSERT INTO reviews(rating, comment, IdCustomer, IdOrder) " +
                    "values (?, ?, ?, ?)";

            //create the mysql insert preparedstatement
            PreparedStatement preparedStmt = MySQL.getConnection().prepareStatement(query);
            preparedStmt.setInt(1, DTO.getRating());
            preparedStmt.setString  (2, DTO.getComment());
            preparedStmt.setInt (3, DTO.getIdCustomer());
            preparedStmt.setInt(4, DTO.getIdOrder());

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
    public ReviewDTO findById(Integer Id) {
        ReviewDTO reviewDTO = new ReviewDTO();
        try {
            //insert query
            String query = "SELECT * FROM reviews Where Id = ?";

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
                int rating = resultSet.getInt(2);
                String comment = resultSet.getString(3);
                Integer IdCustomer = resultSet.getInt(4);
                Integer IdOrder = resultSet.getInt(5);

                reviewDTO = new ReviewDTO(Id, rating, comment, IdCustomer, IdOrder);
            }

            MySQL.getConnection().close();
        }
        catch (Exception ex) {
            System.err.println("Got an exception!");
            System.err.println(ex.getMessage());
        }
        finally {
            return reviewDTO;
        }
    }

    @Override
    public boolean delete(Integer Id) {
        return false;
    }

    public static ReviewDTO getByCustomerAndOrder(Integer IdOrder, Integer IdCustomer) {
        ReviewDTO reviewDTO = new ReviewDTO();
        try {
            //insert query
            String query = "SELECT * FROM reviews Where IdCustomer = ? and IdOrder = ?";

            //create the mysql insert preparedstatement
            PreparedStatement preparedStmt = MySQL.getConnection().prepareStatement(query);
            preparedStmt.setInt (1, IdCustomer);
            preparedStmt.setInt(2, IdOrder);

            System.out.println(preparedStmt);
            //execute the preparedstatement
            // execute the query, and get a java resultset
            ResultSet resultSet = preparedStmt.executeQuery();

            // iterate through the java resultset
            while (resultSet.next())
            {
                Integer Id = resultSet.getInt(1);
                int rating = resultSet.getInt(2);
                String comment = resultSet.getString(3);

                reviewDTO = new ReviewDTO(Id, rating, comment, IdCustomer, IdOrder);
            }

            MySQL.getConnection().close();
        }
        catch (Exception ex) {
            System.err.println("Got an exception!");
            System.err.println(ex.getMessage());
        }
        finally {
            return reviewDTO;
        }
    }
}
