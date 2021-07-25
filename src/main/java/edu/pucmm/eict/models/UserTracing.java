package edu.pucmm.eict.models;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
@Table(name = "USERTRACING")
public class UserTracing implements Serializable {

    @Id
    @OneToOne
    private Username username;
    private String name;
    private String lastname;
    private String email;
    private String phoneNumber;

    @ManyToMany
    private List<UserWheelchair> usersWheelchair;


    public UserTracing() {

    }

    public UserTracing(Username username, String name, String lastname, String email, String phoneNumber, List<UserWheelchair> usersWheelchair) {
        this.username = username;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.usersWheelchair = usersWheelchair;
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

    public List<UserWheelchair> getUsersWheelchair() {
        return usersWheelchair;
    }

    public void setUsersWheelchair(List<UserWheelchair> usersWheelchair) {
        this.usersWheelchair = usersWheelchair;
    }
}
