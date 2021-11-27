package DAO;

import DAO.DAOImpl.GenericDAO;
import DTO.FoodDTO;
import DTO.MenuFoodDTO;
import SQLConnection.MySQL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MenuFoodDAO implements GenericDAO<MenuFoodDTO> {

    @Override
    public List<MenuFoodDTO> getAll() {
        ArrayList<MenuFoodDTO> menuFoodDTOArrayList = new ArrayList<>();
        try {
            //insert query
            String query = "SELECT * FROM menu_foods";

            //create the mysql insert preparedstatement
            PreparedStatement preparedStmt = MySQL.getConnection().prepareStatement(query);

            System.out.println(preparedStmt);
            //execute the preparedstatement
            // execute the query, and get a java resultset
            ResultSet resultSet = preparedStmt.executeQuery(query);

            // iterate through the java resultset
            while (resultSet.next())
            {
                int daysinweek = resultSet.getInt(1);
                Integer productId = resultSet.getInt(2);
                int quantity = resultSet.getInt(3);

                MenuFoodDTO menuFoodDTO = new MenuFoodDTO(daysinweek, productId, quantity);
                menuFoodDTOArrayList.add(menuFoodDTO);
            }

            MySQL.getConnection().close();
        }
        catch (Exception ex) {
            System.err.println("Got an exception!");
            System.err.println(ex.getMessage());
        }
        finally {
            return menuFoodDTOArrayList;
        }
    }

    @Override
    public Boolean update(MenuFoodDTO DTO) {
        boolean status = false;
        try {
            //insert query
            String query = "update menu_foods set quantity = ? where daysinweek = " + DTO.getDaysinweek() + " and id_food = " + DTO.getId_product() ;

            //create the mysql insert preparedstatement
            PreparedStatement preparedStmt = MySQL.getConnection().prepareStatement(query);
            preparedStmt.setInt (1, DTO.getQuantity());

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
    public Boolean insert(MenuFoodDTO DTO) {
        boolean status = false;
        try {
            //insert query
            String query = "INSERT INTO menu_foods(daysinweek, id_food, quantity) " +
                    "values (?, ?, ?)";

            //create the mysql insert preparedstatement
            PreparedStatement preparedStmt = MySQL.getConnection().prepareStatement(query);
            preparedStmt.setInt  (1, DTO.getDaysinweek());
            preparedStmt.setInt  (2, DTO.getId_product());
            preparedStmt.setInt(3, DTO.getQuantity());

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
    public MenuFoodDTO findById(Integer Id) {
        return null;
    }

    @Override
    public boolean delete(Integer Id) {
        return false;
    }

    //method get menu by days in week
    public static List<MenuFoodDTO> getAllByDaysInWeek(int daysinweek) {
        ArrayList<MenuFoodDTO> menuFoodDTOArrayList = new ArrayList<>();
        try {
            //insert query
            String query = "SELECT * FROM `menu_foods` where daysinweek = " + daysinweek;

            //create the mysql insert preparedstatement
            PreparedStatement preparedStmt = MySQL.getConnection().prepareStatement(query);
           //preparedStmt.setInt(1, daysinweek);

            System.out.println(preparedStmt);
            //execute the preparedstatement
            // execute the query, and get a java resultset
            ResultSet resultSet = preparedStmt.executeQuery(query);

            // iterate through the java resultset
            while (resultSet.next())
            {
                Integer productId = resultSet.getInt(2);
                int quantity = resultSet.getInt(3);

                MenuFoodDTO menuFoodDTO = new MenuFoodDTO(daysinweek, productId, quantity);
                menuFoodDTOArrayList.add(menuFoodDTO);
            }

            MySQL.getConnection().close();
        }
        catch (Exception ex) {
            System.err.println("Got an exception!");
            System.err.println(ex.getMessage());
        }
        finally {
            return menuFoodDTOArrayList;
        }
    }

    //method check food existed in menu, daysinweek
    public static boolean checkFoodExistedInMenuAndDaysInWeek(int id_food, int daysinweek) {
        boolean flag = true;
        try {
            //insert query
            String query = "SELECT count(*) FROM menu_foods WHERE daysinweek = " + daysinweek + " and id_food = " + id_food;

            //create the mysql insert preparedstatement
            PreparedStatement preparedStmt = MySQL.getConnection().prepareStatement(query);
            //preparedStmt.setInt (1, id_food);

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

    public static MenuFoodDTO getByDaysAndId(Integer id_food, int daysinweek) {
        MenuFoodDTO menuFoodDTO = new MenuFoodDTO();
        try {
            //insert query
            String query = "SELECT * FROM menu_foods Where daysinweek = " + daysinweek + " and id_food = " + id_food;

            //create the mysql insert preparedstatement
            PreparedStatement preparedStmt = MySQL.getConnection().prepareStatement(query);
            //preparedStmt.setInt (1, id_food);

            System.out.println(preparedStmt);
            //execute the preparedstatement
            // execute the query, and get a java resultset
            ResultSet resultSet = preparedStmt.executeQuery();

            // iterate through the java resultset
            while (resultSet.next()) {
                menuFoodDTO.setDaysinweek(daysinweek);
                menuFoodDTO.setId_product(id_food);
                menuFoodDTO.setQuantity(resultSet.getInt(3));
            }

            MySQL.getConnection().close();
        }
        catch (Exception ex) {
            System.err.println("Got an exception!");
            System.err.println(ex.getMessage());
        }
        finally {
            return menuFoodDTO;
        }
    }

    public static boolean deleteMenuFood(Integer id_food, int daysinweek) {
        boolean status = false;
        try {
            //insert query
            String query = "DELETE FROM menu_foods where daysinweek = " + daysinweek + " and id_food = " + id_food;

            //create the mysql insert preparedstatement
            PreparedStatement preparedStmt = MySQL.getConnection().prepareStatement(query);
            //preparedStmt.setString (1, email);

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
