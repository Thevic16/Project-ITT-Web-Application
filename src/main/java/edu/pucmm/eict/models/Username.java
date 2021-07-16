package edu.pucmm.eict.models;

import javax.persistence.*;


@SuppressWarnings("serial")
@Entity
@Table(name = "USERNAME")
public class Username {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    private String username;

    public Username(String username) {
        this.username = username;
    }

    public Username() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
