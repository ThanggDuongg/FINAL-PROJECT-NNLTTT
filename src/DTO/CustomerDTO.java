package DTO;

import DTO.AbstractDTO.AbstractPerson;

public class CustomerDTO extends AbstractPerson {
    //fields
    private String Address;
    private Double Distance;

    //contrusctor
    public CustomerDTO() {
    }

    public CustomerDTO(String address, Double distance) {
        Address = address;
        Distance = distance;
    }

    public CustomerDTO(Integer ID, String firstname, String lastname, String phone, String gender,
                       int age, String email, String password, String address, Double distance) {
        super(ID, firstname, lastname, phone, gender, age, email, password);
        Address = address;
        Distance = distance;
    }

    public CustomerDTO(String firstname, String lastname, String phone, String gender, int age, String email, String password, String address, Double distance) {
        super(firstname, lastname, phone, gender, age, email, password);
        Address = address;
        Distance = distance;
    }

    //getter & setter
    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public Double getDistance() {
        return Distance;
    }

    public void setDistance(Double distance) {
        Distance = distance;
    }

    //methods
    @Override
    public String toString() {
        return "CustomerDTO{" +
                "Address='" + Address + '\'' +
                ", Distance=" + Distance +
                '}';
    }

    @Override
    public double tinhLuong() {
        return 0;
    }
}
