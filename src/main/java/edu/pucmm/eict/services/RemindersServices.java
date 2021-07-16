package edu.pucmm.eict.services;

import edu.pucmm.eict.models.Reminders;

public class RemindersServices extends DatabaseOrmHandler<Reminders>{

    private static RemindersServices instance = null;

    private RemindersServices() {
        super(Reminders.class);
    }

    public static RemindersServices getInstance() {

        if (instance == null) {
            instance = new RemindersServices();
        }
        return instance;
    }

}
