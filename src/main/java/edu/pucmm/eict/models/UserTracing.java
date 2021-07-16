package edu.pucmm.eict.models;

import java.util.List;
import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
@Table(name = "USERTRACING")
public class UserTracing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String username;
    private String name;
    private String lastname;
    private String password;
    private String email;
    private String phoneNumber;

    @OneToMany
    private List<UserWheelchair> usersWheelchair;

    public UserTracing(String username, String name, String lastname, String password, String email, String phoneNumber, List<UserWheelchair> usersWheelchair) {
        this.username = username;
        this.name = name;
        this.lastname = lastname;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.usersWheelchair = usersWheelchair;
    }

    public UserTracing() {

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

    public List<UserWheelchair> getUsersWheelchair() {
        return usersWheelchair;
    }

    public void setUsersWheelchair(List<UserWheelchair> usersWheelchair) {
        this.usersWheelchair = usersWheelchair;
    }

}
