package DAO;

import DAO.DAOImpl.GenericDAO;
import DTO.OrderDTO;
import SQLConnection.MySQL;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class OrderDAO implements GenericDAO<OrderDTO> {
    @Override
    public List<OrderDTO> getAll() {
        return null;
    }

    @Override
    public Boolean update(OrderDTO DTO) {
        boolean status = false;
        try {
            //insert query
            String query = "update orders set dateOrder = ?, quantity = ?, total = ?, IdCustomer = ?, status = ?, IdShipper = ? where Id = ?";

            //create the mysql insert preparedstatement
            PreparedStatement preparedStmt = MySQL.getConnection().prepareStatement(query);
            preparedStmt.setTimestamp(1, DTO.getDateOrder());
            preparedStmt.setInt(2, DTO.getQuantity());
            preparedStmt.setFloat(3, DTO.getTotal());
            preparedStmt.setInt(4, DTO.getIdCustomer());
            int temp = DTO.isStatus() ? 1 : 0;
            preparedStmt.setInt(5, temp);
            //preparedStmt.setInt(6, DTO.getIdShipper());
            if(DTO.getIdShipper() == null ) {
                preparedStmt.setNull(6, Types.INTEGER);
            }else {
                preparedStmt.setInt(6, DTO.getIdShipper());
            }
            preparedStmt.setInt(7, DTO.getId());

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
    public Boolean insert(OrderDTO DTO) {
        boolean status = false;
        try {
            //insert query
            String query = "INSERT INTO orders(dateOrder, quantity, total, IdCustomer, status) " +
                    "values (?, ?, ?, ?, ?)";

            //create the mysql insert preparedstatement
            PreparedStatement preparedStmt = MySQL.getConnection().prepareStatement(query);
            preparedStmt.setTimestamp(1, DTO.getDateOrder());
            preparedStmt.setInt  (2, DTO.getQuantity());
            preparedStmt.setFloat (3, DTO.getTotal());
            preparedStmt.setInt(4, DTO.getIdCustomer());
            int temp = DTO.isStatus() ? 1 : 0;
            preparedStmt.setInt(5, temp);

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
    public OrderDTO findById(Integer Id) {
        OrderDTO orderDTO = new OrderDTO();
        try {
            //insert query
            String query = "SELECT * FROM orders Where Id = ?";

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
                Timestamp dateOrder = resultSet.getTimestamp(2);
                int quantity = resultSet.getInt(3);
                float total = resultSet.getFloat(4);
                int IdCustomer = resultSet.getInt(5);
                int st = resultSet.getInt(6);
                Integer shipperId =resultSet.getInt(7);
                boolean status = st == 1 ? true : false;

                // print the results
                orderDTO = new OrderDTO(Id, dateOrder, quantity, total, IdCustomer, shipperId, status);
            }

            MySQL.getConnection().close();
        }
        catch (Exception ex) {
            System.err.println("Got an exception!");
            System.err.println(ex.getMessage());
        }
        finally {
            return orderDTO;
        }
    }

    @Override
    public boolean delete(Integer Id) {
        return false;
    }

    public static List<OrderDTO> getAllByIdCustomerHistory(Integer IdCustomer) {
        ArrayList<OrderDTO> orderDTOArrayList = new ArrayList<>();
        try {
            //insert query
            String query = "SELECT * FROM orders where IdCustomer = " + IdCustomer;

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
                Timestamp dateOrder = resultSet.getTimestamp(2);
                int quantity = resultSet.getInt(3);
                float total = resultSet.getFloat(4);
                int status = resultSet.getInt(6);
                //Integer IdShipper = resultSet.getInt(7);
                boolean st = status == 1 ? true : false;

                OrderDTO orderDTO = new OrderDTO(Id, dateOrder, quantity, total, IdCustomer, st);
                orderDTOArrayList.add(orderDTO);
            }

            MySQL.getConnection().close();
        }
        catch (Exception ex) {
            System.err.println("Got an exception!");
            System.err.println(ex.getMessage());
        }
        finally {
            return orderDTOArrayList;
        }
    }

    public int insertReturnId(OrderDTO DTO) {
        int id = -1;
        try {
            //insert query
            String query = "INSERT INTO orders(dateOrder, quantity, total, IdCustomer, status) " +
                    "values (?, ?, ?, ?, ?)";

            //create the mysql insert preparedstatement
            PreparedStatement preparedStmt = MySQL.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStmt.setTimestamp(1, DTO.getDateOrder());
            preparedStmt.setInt  (2, DTO.getQuantity());
            preparedStmt.setFloat (3, DTO.getTotal());
            preparedStmt.setInt(4, DTO.getIdCustomer());
            int temp = DTO.isStatus() ? 1 : 0;
            preparedStmt.setInt(5, temp);

            System.out.println(preparedStmt);
            //execute the preparedstatement
            int result = preparedStmt.executeUpdate();

            ResultSet tableKeys = preparedStmt.getGeneratedKeys();
            tableKeys.next();
            id = tableKeys.getInt(1);

            MySQL.getConnection().close();
        }
        catch (Exception ex) {
            System.err.println("Got an exception!");
            System.err.println(ex.getMessage());
        }
        finally {
            return id;
        }
    }

    public static List<OrderDTO> getAllOrder_noShipper() {
        ArrayList<OrderDTO> orderDTOArrayList = new ArrayList<>();
        try {
            //insert query
            String query = "SELECT * FROM orders where IdShipper is null";

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
                Timestamp dateOrder = resultSet.getTimestamp(2);
                int quantity = resultSet.getInt(3);
                float total = resultSet.getFloat(4);
                Integer IdCustomer = resultSet.getInt(5);
                int status = resultSet.getInt(6);
                boolean st = status == 1 ? true : false;

                OrderDTO orderDTO = new OrderDTO(Id, dateOrder, quantity, total, IdCustomer, st);
                orderDTOArrayList.add(orderDTO);
            }

            MySQL.getConnection().close();
        }
        catch (Exception ex) {
            System.err.println("Got an exception!");
            System.err.println(ex.getMessage());
        }
        finally {
            return orderDTOArrayList;
        }
    }

    public static int numOfOrdersByShipper_Today(Integer IdShipper) {
        int count = 0;
        try {
            //insert query
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat dateOnly = new SimpleDateFormat("yyyy-MM-dd");
            //System.out.println(dateOnly.format(cal.getTime()));
            String firstTime = dateOnly.format(cal.getTime()) + " 00:00:01";
            String query = "SELECT count(*) FROM orders Where IdShipper = ? and dateOrder between '" + firstTime + "' and CURRENT_TIMESTAMP()";

            //create the mysql insert preparedstatement
            PreparedStatement preparedStmt = MySQL.getConnection().prepareStatement(query);
            preparedStmt.setInt (1, IdShipper);

            System.out.println(preparedStmt);
            //execute the preparedstatement
            // execute the query, and get a java resultset
            ResultSet resultSet = preparedStmt.executeQuery();

            // iterate through the java resultset

            while (resultSet.next()) {
                count = resultSet.getInt(1);
            }

            MySQL.getConnection().close();
        }
        catch (Exception ex) {
            System.err.println("Got an exception!");
            System.err.println(ex.getMessage());
        }
        finally {
            return count;
        }
    }

    public static List<OrderDTO> getAllOrderOfShipper_StatusFalse(Integer IdShipper) {
        ArrayList<OrderDTO> orderDTOArrayList = new ArrayList<>();
        try {
            //insert query
            String query = "SELECT * FROM orders where IdShipper = " + IdShipper + " and status = 0";

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
                Timestamp dateOrder = resultSet.getTimestamp(2);
                int quantity = resultSet.getInt(3);
                float total = resultSet.getFloat(4);
                Integer IdCustomer = resultSet.getInt(5);
                int status = resultSet.getInt(6);
                //Integer IdShipper = resultSet.getInt(7);
                boolean st = status == 1 ? true : false;

                OrderDTO orderDTO = new OrderDTO(Id, dateOrder, quantity, total, IdCustomer, IdShipper, st);
                orderDTOArrayList.add(orderDTO);
            }

            MySQL.getConnection().close();
        }
        catch (Exception ex) {
            System.err.println("Got an exception!");
            System.err.println(ex.getMessage());
        }
        finally {
            return orderDTOArrayList;
        }
    }

    public static List<OrderDTO> getAllOrdersWithStatusTrue(int index) {
        String fairyTail = " order by ";
        if (index == 0) {
            fairyTail += "dateOrder asc";
        }
        else if (index == 1) {
            fairyTail += "dateOrder desc";
        }
        else if (index == 2) {
            fairyTail += "total asc";
        }
        else {
            fairyTail += "total desc";
        }

        ArrayList<OrderDTO> orderDTOArrayList = new ArrayList<>();
        try {
            //insert query
            String query = "SELECT * FROM orders where status = 1";
            query += fairyTail;
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
                Timestamp dateOrder = resultSet.getTimestamp(2);
                int quantity = resultSet.getInt(3);
                float total = resultSet.getFloat(4);
                Integer IdCustomer = resultSet.getInt(5);
                int status = resultSet.getInt(6);
                Integer IdShipper = resultSet.getInt(7);
                boolean st = status == 1 ? true : false;

                OrderDTO orderDTO = new OrderDTO(Id, dateOrder, quantity, total, IdCustomer, IdShipper, st);
                orderDTOArrayList.add(orderDTO);
            }

            MySQL.getConnection().close();
        }
        catch (Exception ex) {
            System.err.println("Got an exception!");
            System.err.println(ex.getMessage());
        }
        finally {
            return orderDTOArrayList;
        }
    }

    public static List<OrderDTO> getAllByIdShipperHistory(Integer IdShipper) {
        ArrayList<OrderDTO> orderDTOArrayList = new ArrayList<>();
        try {
            //insert query
            String query = "SELECT * FROM orders where IdShipper = " + IdShipper;

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
                Timestamp dateOrder = resultSet.getTimestamp(2);
                int quantity = resultSet.getInt(3);
                float total = resultSet.getFloat(4);
                int status = resultSet.getInt(6);
                Integer IdCustomer = resultSet.getInt(5);
                boolean st = status == 1 ? true : false;

                OrderDTO orderDTO = new OrderDTO(Id, dateOrder, quantity, total, IdCustomer, st);
                orderDTOArrayList.add(orderDTO);
            }

            MySQL.getConnection().close();
        }
        catch (Exception ex) {
            System.err.println("Got an exception!");
            System.err.println(ex.getMessage());
        }
        finally {
            return orderDTOArrayList;
        }
    }
}
