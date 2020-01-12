package com.marpen.shop.model;

import org.hibernate.annotations.Proxy;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "users")
@Proxy(lazy = false)
public class User {

    @Id
    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "role_id")
    private int roleId;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "birthdate")
    private Date birthDate;

    @Column(name = "address")
    private String address;

    @Column(name = "status", columnDefinition = "bit")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean status;

    public User() {
    }

    public User(String login, String password, int roleId, String name, String surname,
                String email, String phone, Date birthDate, String address, Boolean status) {
        this.login = login;
        this.password = password;
        this.roleId = roleId;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
        this.birthDate = birthDate;
        this.address=address;
        this.status=status;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
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

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return roleId == user.roleId &&
                Objects.equals(login, user.login) &&
                Objects.equals(password, user.password) &&
                Objects.equals(name, user.name) &&
                Objects.equals(surname, user.surname) &&
                Objects.equals(email, user.email) &&
                Objects.equals(phone, user.phone) &&
                Objects.equals(formatter.format(birthDate),formatter.format(user.birthDate)) &&
                Objects.equals(address, user.address) &&
                Objects.equals(status, user.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password, roleId, name, surname, email, phone, birthDate, address, status);
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", roleId=" + roleId +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", birthDate=" + birthDate +
                ", address='" + address + '\'' +
                ", status=" + status +
                '}';
    }
}
