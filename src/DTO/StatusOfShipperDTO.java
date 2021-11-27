package DTO;

public class StatusOfShipperDTO {
    private Integer idShipper;
    private boolean status;

    public StatusOfShipperDTO() {
    }

    public StatusOfShipperDTO(Integer idShipper, boolean status) {
        this.idShipper = idShipper;
        this.status = status;
    }

    public Integer getIdShipper() {
        return idShipper;
    }

    public void setIdShipper(Integer idShipper) {
        this.idShipper = idShipper;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
