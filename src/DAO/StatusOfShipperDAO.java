package DAO;

import DAO.DAOImpl.GenericDAO;
import DTO.ShipperDTO;
import DTO.StatusOfShipperDTO;
import SQLConnection.MySQL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StatusOfShipperDAO implements GenericDAO<StatusOfShipperDTO> {
    @Override
    public List<StatusOfShipperDTO> getAll() {
        return null;
    }

    @Override
    public Boolean update(StatusOfShipperDTO DTO) {
        boolean status = false;
        try {
            //insert query
            String query = "update statusofshippers set status = ? where IdShipper = ?";

            //create the mysql insert preparedstatement
            PreparedStatement preparedStmt = MySQL.getConnection().prepareStatement(query);
            int st = DTO.isStatus() ? 1 : 0;
            preparedStmt.setInt(1, st);
            preparedStmt.setInt(2, DTO.getIdShipper());

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
    public Boolean insert(StatusOfShipperDTO DTO) {
        boolean status = false;
        try {
            //insert query
            String query = "INSERT INTO statusofshippers(IdShipper, status) " +
                    "values (?, ?)";

            //create the mysql insert preparedstatement
            PreparedStatement preparedStmt = MySQL.getConnection().prepareStatement(query);
            preparedStmt.setInt (1, DTO.getIdShipper());
            int temp = DTO.isStatus() ? 1 : 0;
            preparedStmt.setInt (2, temp);

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
    public StatusOfShipperDTO findById(Integer Id) {
        return null;
    }

    @Override
    public boolean delete(Integer Id) {
        return false;
    }

    public static List<StatusOfShipperDTO> getAllNotStatus() {
        ArrayList<StatusOfShipperDTO> statusOfShipperDTOArrayList = new ArrayList<StatusOfShipperDTO>();
        try {
            //insert query
            String query = "SELECT * FROM statusofshippers WHERE status = 0";

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
                int temp = resultSet.getInt(2);
                boolean status = temp == 0 ? false : true;

                StatusOfShipperDTO statusOfShipperDTO = new StatusOfShipperDTO(ID, status);
                statusOfShipperDTOArrayList.add(statusOfShipperDTO);
            }

            MySQL.getConnection().close();
        }
        catch (Exception ex) {
            System.err.println("Got an exception!");
            System.err.println(ex.getMessage());
        }
        finally {
            return statusOfShipperDTOArrayList;
        }
    }
}
