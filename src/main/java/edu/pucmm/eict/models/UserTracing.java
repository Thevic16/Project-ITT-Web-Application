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


    @ManyToMany(fetch = FetchType.EAGER)
    private List<UserWheelchair> usersWheelchair;


    public UserTracing() {

    }

    public UserTracing(Username username, List<UserWheelchair> usersWheelchair) {
        this.username = username;
        this.usersWheelchair = usersWheelchair;
    }

    public Username getUsername() {
        return username;
    }

    public void setUsername(Username username) {
        this.username = username;
    }

    public List<UserWheelchair> getUsersWheelchair() {
        return usersWheelchair;
    }

    public void setUsersWheelchair(List<UserWheelchair> usersWheelchair) {
        this.usersWheelchair = usersWheelchair;
    }

    public String getListWheelchair(){
       String listWheelchair = "";

        for (UserWheelchair userWheelchair:usersWheelchair) {
            listWheelchair += " "+userWheelchair.getUsername().getUsername();
        }

        return listWheelchair;
    }

}
