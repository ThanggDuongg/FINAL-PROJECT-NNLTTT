package DTO;

import DTO.AbstractDTO.AbstractPerson;

public class AdministratorDTO extends AbstractPerson {
    public AdministratorDTO() {
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
