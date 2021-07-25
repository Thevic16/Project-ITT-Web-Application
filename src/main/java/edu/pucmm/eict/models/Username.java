package edu.pucmm.eict.models;

import javax.persistence.*;


@SuppressWarnings("serial")
@Entity
@Table(name = "USERNAME")
public class Username {

    @Id
    private String username;
    private String password;
    private Boolean iswheelchair;


    public Username() {

    }

    public Username(String username, String password, Boolean iswheelchair) {
        this.username = username;
        this.password = password;
        this.iswheelchair = iswheelchair;
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
}
