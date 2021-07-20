package edu.pucmm.eict.models;

import java.io.Serializable;
import javax.persistence.*;


@SuppressWarnings("serial")
@Entity
@Table(name = "USERWHEELCHAIR")
public class UserWheelchair implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    private Username username;
    private String name;
    private String lastname;
    private String email;
    private String phoneNumber;



    public UserWheelchair() {

    }

    public UserWheelchair(Username username, String name, String lastname, String email, String phoneNumber) {
        this.username = username;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Username getUsername() {
        return username;
    }

    public void setUsername(Username username) {
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
