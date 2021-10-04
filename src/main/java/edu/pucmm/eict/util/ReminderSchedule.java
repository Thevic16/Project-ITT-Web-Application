package edu.pucmm.eict.util;

import edu.pucmm.eict.models.Username;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.TimerTask;

public class ReminderSchedule extends TimerTask{

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

    private Username username;

    private Boolean always;


    public ReminderSchedule(String desciption,Boolean monday, Boolean tuesday, Boolean wednesday, Boolean thursday, Boolean friday, Boolean saturday, Boolean sunday, LocalTime hour, LocalDate dateEnd,Username username, Boolean always) {
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
        this.always = always;
    }

    @Override
    public void run() {
        Calendar currentDay = Calendar.getInstance();

        if(dateEnd != null){
            if(LocalDate.now().isAfter(dateEnd)){
                this.cancel();
                return;
            }
        }

        switch (currentDay.get(Calendar.DAY_OF_WEEK)) {
            case 1:
                if(this.sunday){
                    EmailUtility emailUtility = new EmailUtility();
                    emailUtility.sendMail( this.username.getEmail(), "Recordatorio "+this.desciption+".", this.desciption);

                    try {
                        PushNotification.sendPushNotification("Recordatario","Recordatorio "+this.desciption+".");
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }

                break;

            case 2:
                if(this.monday){
                    EmailUtility emailUtility = new EmailUtility();
                    emailUtility.sendMail( this.username.getEmail(), "Recordatorio "+this.desciption+".", this.desciption);

                    try {
                        PushNotification.sendPushNotification("Recordatario","Recordatorio "+this.desciption+".");
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                break;

            case 3:
                if(this.tuesday){
                    EmailUtility emailUtility = new EmailUtility();
                    emailUtility.sendMail( this.username.getEmail(), "Recordatorio "+this.desciption+".", this.desciption);

                    try {
                        PushNotification.sendPushNotification("Recordatario","Recordatorio "+this.desciption+".");
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }

                break;

            case 4:
                if(this.wednesday){
                    EmailUtility emailUtility = new EmailUtility();
                    emailUtility.sendMail( this.username.getEmail(), "Recordatorio "+this.desciption+".", this.desciption);

                    try {
                        PushNotification.sendPushNotification("Recordatario","Recordatorio "+this.desciption+".");
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }

                break;

            case 5:
                if(this.thursday){
                    EmailUtility emailUtility = new EmailUtility();
                    emailUtility.sendMail( this.username.getEmail(), "Recordatorio "+this.desciption+".", this.desciption);

                    try {
                        PushNotification.sendPushNotification("Recordatario","Recordatorio "+this.desciption+".");
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }

                break;

            case 6:
                if(this.friday){
                    EmailUtility emailUtility = new EmailUtility();
                    emailUtility.sendMail( this.username.getEmail(), "Recordatorio "+this.desciption+".", this.desciption);

                    try {
                        PushNotification.sendPushNotification("Recordatario","Recordatorio "+this.desciption+".");
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }

                break;

            case 7:
                if(this.saturday){
                    EmailUtility emailUtility = new EmailUtility();
                    emailUtility.sendMail( this.username.getEmail(), "Recordatorio "+this.desciption+".", this.desciption);

                    try {
                        PushNotification.sendPushNotification("Recordatario","Recordatorio "+this.desciption+".");
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }

                break;
        }
    }

}
