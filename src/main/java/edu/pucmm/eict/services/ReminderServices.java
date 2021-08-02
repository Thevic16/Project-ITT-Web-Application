package edu.pucmm.eict.services;

import edu.pucmm.eict.models.Reminder;

public class ReminderServices extends DatabaseOrmHandler<Reminder>{

    private static ReminderServices instance = null;

    private ReminderServices() {
        super(Reminder.class);
    }

    public static ReminderServices getInstance() {

        if (instance == null) {
            instance = new ReminderServices();
        }
        return instance;
    }

}
