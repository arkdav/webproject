package com.marpen.shop.dto;

public class UserDataDto {

//    private Integer userId;
    private String login;
    private String password;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private String birthdate;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String  getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
