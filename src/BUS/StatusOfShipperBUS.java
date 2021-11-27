package BUS;

import DAO.StatusOfShipperDAO;
import DTO.ShipperDTO;
import DTO.StatusOfShipperDTO;

import java.util.List;

public class StatusOfShipperBUS {
    private StatusOfShipperDAO statusOfShipperDAO = new StatusOfShipperDAO();

    public boolean changeStatusShipper(StatusOfShipperDTO statusOfShipperDTO) {
        return this.statusOfShipperDAO.update(statusOfShipperDTO);
    }

    public static List<StatusOfShipperDTO> getAllNotStatus() {
        return StatusOfShipperDAO.getAllNotStatus();
    }
}
