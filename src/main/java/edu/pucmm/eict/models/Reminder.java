package edu.pucmm.eict.models;


import edu.pucmm.eict.services.ReminderServices;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;


@SuppressWarnings("serial")
@Entity
@Table(name = "REMINDER")
public class Reminder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String desciption;

    private Boolean monday;
    private Boolean tuesday;
    private Boolean wednesday;
    private Boolean thursday;
    private Boolean friday;
    private Boolean saturday;
    private Boolean sunday;

    private LocalTime hour;
    private LocalDate dateEnd;

    private String username;


    public Reminder(String desciption, Boolean monday, Boolean tuesday, Boolean wednesday, Boolean thursday, Boolean friday, Boolean saturday, Boolean sunday, LocalTime hour, LocalDate dateEnd, String username) {
        this.desciption = desciption;
        this.monday = monday;
        this.tuesday = tuesday;
        this.wednesday = wednesday;
        this.thursday = thursday;
        this.friday = friday;
        this.saturday = saturday;
        this.sunday = sunday;
        this.hour = hour;
        this.dateEnd = dateEnd;
        this.username = username;
    }

    public Reminder() {

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDesciption() {
        return desciption;
    }

    public void setDesciption(String desciption) {
        this.desciption = desciption;
    }

    public Boolean getMonday() {
        return monday;
    }

    public void setMonday(Boolean monday) {
        this.monday = monday;
    }

    public Boolean getTuesday() {
        return tuesday;
    }

    public void setTuesday(Boolean tuesday) {
        this.tuesday = tuesday;
    }

    public Boolean getWednesday() {
        return wednesday;
    }

    public void setWednesday(Boolean wednesday) {
        this.wednesday = wednesday;
    }

    public Boolean getThursday() {
        return thursday;
    }

    public void setThursday(Boolean thursday) {
        this.thursday = thursday;
    }

    public Boolean getFriday() {
        return friday;
    }

    public void setFriday(Boolean friday) {
        this.friday = friday;
    }

    public Boolean getSaturday() {
        return saturday;
    }

    public void setSaturday(Boolean saturday) {
        this.saturday = saturday;
    }

    public Boolean getSunday() {
        return sunday;
    }

    public void setSunday(Boolean sunday) {
        this.sunday = sunday;
    }

    public LocalTime getHour() {
        return hour;
    }

    public void setHour(LocalTime hour) {
        this.hour = hour;
    }

    public LocalDate getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(LocalDate dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public static List<Reminder> findRemandersByUsername(String username){
        List<Reminder> allRemanders = ReminderServices.getInstance().findAll();
        List<Reminder> filteredRemanders = new ArrayList<Reminder>();

        for (Reminder reminder:allRemanders) {

            if(reminder.username.equals(username)){
                filteredRemanders.add(reminder);
            }

        }

        return filteredRemanders;
    }

    public String repeat(){
        String repeat ="";

        if(monday==true){
            repeat += "Lunes";
        }
        if(tuesday==true){
            repeat += " Martes";
        }
        if(wednesday==true){
            repeat += " Miércoles";
        }
        if(thursday==true){
            repeat += " Jueves";
        }
        if(friday==true){
            repeat += " Viernes";
        }
        if(saturday==true){
            repeat += " Sábado";
        }
        if(sunday==true){
            repeat += " Domingo";
        }

        return repeat;
    }

    public String getDateEndSpanish(){

        return this.dateEnd.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }


}
