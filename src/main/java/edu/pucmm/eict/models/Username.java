package edu.pucmm.eict.models;

import javax.persistence.*;


@SuppressWarnings("serial")
@Entity
@Table(name = "USERNAME")
public class Username {

    @Id
    private String username;
    private String password;
    private String name;
    private String lastname;
    private String email;
    private String phoneNumber;

    private Boolean iswheelchair;


    public Username() {

    }

    public Username(String username, String password, Boolean iswheelchair,String name,String lastname,String email, String phoneNumber) {
        this.username = username;
        this.password = password;
        this.iswheelchair = iswheelchair;
        this.name =name;
        this.lastname=lastname;
        this.email=email;
        this.phoneNumber=phoneNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getIswheelchair() {
        return iswheelchair;
    }

    public void setIswheelchair(Boolean iswheelchair) {
        this.iswheelchair = iswheelchair;
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
