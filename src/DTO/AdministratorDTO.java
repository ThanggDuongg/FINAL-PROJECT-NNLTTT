package DTO;

import DTO.AbstractDTO.AbstractPerson;

public class AdministratorDTO extends AbstractPerson {
    public AdministratorDTO() {
    }

    public AdministratorDTO(String firstname, String lastname, String phone, String gender, int age, String email, String password) {
        super(firstname, lastname, phone, gender, age, email, password);
    }

    public AdministratorDTO(Integer ID, String firstname, String lastname, String phone, String gender,
                            int age, String email, String password) {
        super(ID, firstname, lastname, phone, gender, age, email, password);
    }

    @Override
    public double tinhLuong() {
        return 0;
    }
}
