package edu.pucmm.eict.models;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;


@Entity
@Table(name = "FALLEVENT")
public class FallEvent implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne
    private Username username;
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
}
