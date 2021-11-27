package DTO.AbstractDTO;

import DTO.InterfaceDTO.IAccountDTO;

public abstract class AbstractPerson implements IAccountDTO {
    //feilds
    private Integer ID;
    private String Firstname;
    private String Lastname;
    private String Phone;
    private String Gender;
    private int Age;
    private String Email;
    private String Password;

    //constructor


    public AbstractPerson(String firstname, String lastname, String phone, String gender, int age, String email, String password) {
        Firstname = firstname;
        Lastname = lastname;
        Phone = phone;
        Gender = gender;
        Age = age;
        Email = email;
        Password = password;
    }

    public AbstractPerson(Integer ID, String firstname, String lastname, String phone,
                          String gender, int age, String email, String password) {
        this.ID = ID;
        Firstname = firstname;
        Lastname = lastname;
        Phone = phone;
        Gender = gender;
        Age = age;
        Email = email;
        Password = password;
    }

    public AbstractPerson() {
    }

    //getter & setter
    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getFirstname() {
        return Firstname;
    }

    public void setFirstname(String firstname) {
        Firstname = firstname;
    }

    public String getLastname() {
        return Lastname;
    }

    public void setLastname(String lastname) {
        Lastname = lastname;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    @Override
    public String getEmail() {
        return this.Email;
    }

    @Override
    public void setEmail(String Email) {
        this.Email = Email;
    }

    @Override
    public String getPassword() {
        return this.Password;
    }

    @Override
    public void setPassword(String Password) {
        this.Password = Password;
    }

    //methods
    public abstract double tinhLuong();

    @Override
    public String toString() {
        return "AbstractPerson{" +
                "ID=" + ID +
                ", Firstname='" + Firstname + '\'' +
                ", Lastname='" + Lastname + '\'' +
                ", Phone='" + Phone + '\'' +
                ", Gender='" + Gender + '\'' +
                ", Age=" + Age +
                ", Email='" + Email + '\'' +
                ", Password='" + Password + '\'' +
                '}';
    }
}
