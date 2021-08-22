package edu.pucmm.eict.models;

import edu.pucmm.eict.services.FallEventServices;
import edu.pucmm.eict.services.UserTracingServices;
import edu.pucmm.eict.services.UsernameServices;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "FALLEVENT")
public class FallEvent implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne
    private Username username;
    @Column(length = 16777216)
    private String photo;
    @OneToOne
    private Position position;
    private LocalDate dateTime;
    private LocalTime hour;


    public FallEvent(Username username, String photo, Position position, LocalDate dateTime,LocalTime hour) {
        this.username = username;
        this.photo = photo;
        this.position = position;
        this.dateTime = dateTime;
        this.hour = hour;
    }

    public FallEvent() {
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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public LocalDate getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDate dateTime) {
        this.dateTime = dateTime;
    }

    public LocalTime getHour() {
        return hour;
    }

    public void setHour(LocalTime hour) {
        this.hour = hour;
    }

    public String getDateEndSpanish(){
        return this.dateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }


    // Obtain UserWheelchair of UserTracing and compare they with the existing FallEvent's Users, return a list of the matching ones.
    public static List<FallEvent> findFallEventsByUsername(String usernameTracing){
        List<FallEvent> allFallEvents = FallEventServices.getInstance().findAll();
        List<FallEvent> filteredFallEvents= new ArrayList<FallEvent>();

        //Username usernameTracingOject = UsernameServices.getInstance().find(usernameTracing);
        UserTracing userTracing = UserTracingServices.getInstance().find(usernameTracing);

        for (UserWheelchair userWheelchair:userTracing.getUsersWheelchair()) {
            for (FallEvent fallEvent : allFallEvents) {
                if (fallEvent.username.getUsername().equals(userWheelchair.getUsername().getUsername())) {
                    filteredFallEvents.add(fallEvent);
                }
            }
        }

        return filteredFallEvents;
    }

}
