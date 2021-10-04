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

    public void setReminderSchedule(String desciption, Boolean monday, Boolean tuesday, Boolean wednesday, Boolean thursday, Boolean friday, Boolean saturday, Boolean sunday, LocalTime hour, LocalDate dateEnd, Username username, Boolean always){
        Date hourReminder = new Date(System.currentTimeMillis());

        Calendar c = Calendar.getInstance();
        c.setTime(hourReminder);

        //System.out.println(c.get(Calendar.DAY_OF_WEEK));

        //If the current hour is after reminder hour we put the reminder to start the next day.
        /*
        if (c.get(Calendar.HOUR_OF_DAY) >= reminder.getHour().getHour()) {
            c.set(Calendar.DAY_OF_YEAR, c.get(Calendar.DAY_OF_YEAR) + 1);
        }
         */

        c.set(Calendar.HOUR_OF_DAY, hour.getHour());
        c.set(Calendar.MINUTE, hour.getMinute());
        c.set(Calendar.SECOND, hour.getSecond());

        hourReminder = c.getTime();
        //System.out.println(hourReminder);
        //System.out.println(c.get(Calendar.DAY_OF_WEEK));

        // The code is executed every 24 hours (once a day)
        int timeRepetition = 86400000;

        // Schedule the timer.
        this.timer.schedule(new ReminderSchedule(desciption,monday,tuesday,wednesday,thursday,friday,saturday,sunday,hour,dateEnd,username,always),hourReminder,timeRepetition);
    }

    public void cancelReminderSchedule(){
        this.timer.cancel();
    }


}
