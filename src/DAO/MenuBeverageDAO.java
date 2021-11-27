package DAO;

import DAO.DAOImpl.GenericDAO;
import DTO.MenuBeverageDTO;
import DTO.MenuFoodDTO;
import SQLConnection.MySQL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MenuBeverageDAO implements GenericDAO<MenuBeverageDTO> {
    @Override
    public List<MenuBeverageDTO> getAll() {
        ArrayList<MenuBeverageDTO> menuBeverageDTOArrayList = new ArrayList<>();
        try {
            //insert query
            String query = "SELECT * FROM menu_beverages";

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

                MenuBeverageDTO menuBeverageDTO = new MenuBeverageDTO(daysinweek, productId, quantity);
                menuBeverageDTOArrayList.add(menuBeverageDTO);
            }

            MySQL.getConnection().close();
        }
        catch (Exception ex) {
            System.err.println("Got an exception!");
            System.err.println(ex.getMessage());
        }
        finally {
            return menuBeverageDTOArrayList;
        }
    }

    @Override
    public Boolean update(MenuBeverageDTO DTO) {
        boolean status = false;
        try {
            //insert query
            String query = "update menu_beverages set quantity = ? where daysinweek = " + DTO.getDaysinweek() + " and id_beverage = " + DTO.getId_product() ;

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
    public Boolean insert(MenuBeverageDTO DTO) {
        boolean status = false;
        try {
            //insert query
            String query = "INSERT INTO menu_beverages(daysinweek, id_beverage, quantity) " +
                    "values (?, ?, ?)";

            //create the mysql insert preparedstatement
            PreparedStatement preparedStmt = MySQL.getConnection().prepareStatement(query);
            preparedStmt.setInt  (1, DTO.getDaysinweek());
            preparedStmt.setInt  (2, DTO.getId_product());
            preparedStmt.setInt(3,DTO.getQuantity());

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
    public MenuBeverageDTO findById(Integer Id) {
        return null;
    }

    @Override
    public boolean delete(Integer Id) {
        return false;
    }

    //method get menu by days in week
    public static List<MenuBeverageDTO> getAllByDaysInWeek(int daysinweek) {
        ArrayList<MenuBeverageDTO> menuBeverageDTOArrayList = new ArrayList<>();
        try {
            //insert query
            String query = "SELECT * FROM `menu_beverages` where daysinweek = " + daysinweek;

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

                MenuBeverageDTO menuBeverageDTO = new MenuBeverageDTO(daysinweek, productId, quantity);
                menuBeverageDTOArrayList.add(menuBeverageDTO);
            }

            MySQL.getConnection().close();
        }
        catch (Exception ex) {
            System.err.println("Got an exception!");
            System.err.println(ex.getMessage());
        }
        finally {
            return menuBeverageDTOArrayList;
        }
    }

    //method check food existed in menu, daysinweek
    public static boolean checkFoodExistedInMenuAndDaysInWeek(int id_food, int daysinweek) {
        boolean flag = true;
        try {
            //insert query
            String query = "SELECT count(*) FROM menu_beverages WHERE daysinweek = " + daysinweek + " and id_beverage = " + id_food;

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

    public static MenuBeverageDTO getByDaysAndId(Integer id_food, int daysinweek) {
        MenuBeverageDTO menuBeverageDTO = new MenuBeverageDTO();
        try {
            //insert query
            String query = "SELECT * FROM menu_beverages Where daysinweek = " + daysinweek + " and id_beverage = " + id_food;

            //create the mysql insert preparedstatement
            PreparedStatement preparedStmt = MySQL.getConnection().prepareStatement(query);
            //preparedStmt.setInt (1, id_food);

            System.out.println(preparedStmt);
            //execute the preparedstatement
            // execute the query, and get a java resultset
            ResultSet resultSet = preparedStmt.executeQuery();

            // iterate through the java resultset
            while (resultSet.next()) {
                menuBeverageDTO.setDaysinweek(daysinweek);
                menuBeverageDTO.setId_product(id_food);
                menuBeverageDTO.setQuantity(resultSet.getInt(3));
            }

            MySQL.getConnection().close();
        }
        catch (Exception ex) {
            System.err.println("Got an exception!");
            System.err.println(ex.getMessage());
        }
        finally {
            return menuBeverageDTO;
        }
    }

    public static boolean deleteMenuBeverage(Integer id_food, int daysinweek) {
        boolean status = false;
        try {
            //insert query
            String query = "DELETE FROM menu_beverages where daysinweek = " + daysinweek + " and id_beverage = " + id_food;

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
