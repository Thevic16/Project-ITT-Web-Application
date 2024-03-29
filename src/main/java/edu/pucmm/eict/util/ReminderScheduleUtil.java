package edu.pucmm.eict.util;

import edu.pucmm.eict.models.Username;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

public class ReminderScheduleUtil {

    private Timer timer;

    public ReminderScheduleUtil() {
        this.timer = new Timer();
    }

    public void setReminderSchedule(String description, Boolean monday, Boolean tuesday, Boolean wednesday, Boolean thursday, Boolean friday, Boolean saturday, Boolean sunday, LocalTime hour, LocalDate dateEnd, Username username, Boolean always , Boolean sendEmail){
        //Date hourReminder = new Date(System.currentTimeMillis());

        Date today = new Date();
        System.out.println("Output today:"+today);

        Calendar c = Calendar.getInstance();

        c.setTime(today);
        c.set(Calendar.HOUR_OF_DAY, hour.getHour());
        c.set(Calendar.MINUTE, hour.getMinute());
        c.set(Calendar.SECOND, hour.getSecond());

        Date hourReminder = c.getTime();

        System.out.println("Output hour Reminder:"+hourReminder);

        //System.out.println(c.get(Calendar.DAY_OF_WEEK));

        //System.out.println(hourReminder);:>
        //System.out.println(c.get(Calendar.DAY_OF_WEEK));

        // The code is executed every 24 hours (once a day)
        int timeRepetition = 86400000;

        //Date DateProof = new Date(System.currentTimeMillis());
        //System.out.println(DateProof);

        // Schedule the timer.
        this.timer.schedule(new ReminderSchedule(description,monday,tuesday,wednesday,thursday,friday,saturday,sunday,hour,dateEnd,username,always,sendEmail),hourReminder,timeRepetition);
    }

    public void cancelReminderSchedule(){
        this.timer.cancel();
    }

}
