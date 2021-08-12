package edu.pucmm.eict.models;

import java.io.Serializable;
import javax.persistence.*;


@SuppressWarnings("serial")
@Entity
@Table(name = "USERWHEELCHAIR")
public class UserWheelchair implements Serializable {

    @Id
    @OneToOne
    private Username username;


    public UserWheelchair() {

    }

    public UserWheelchair(Username username) {
        this.username = username;
    }

    public Username getUsername() {
        return username;
    }

    public void setUsername(Username username) {
        this.username = username;
    }

}
