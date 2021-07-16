package edu.pucmm.eict.models;

import java.io.Serializable;
import javax.persistence.*;


@SuppressWarnings("serial")
@Entity
@Table(name = "USERWHEELCHAIR")
public class UserWheelchair implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String username;
    private String name;
    private String lastname;
    private String password;
    private String email;
    private String phoneNumber;

    public UserWheelchair (String username, String name, String lastname, String password, String email, String phoneNumber) {
        this.username = username;
        this.name = name;
        this.lastname = lastname;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public UserWheelchair() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
