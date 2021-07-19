package edu.pucmm.eict.services;

import edu.pucmm.eict.models.Username;

public class UsernameServices extends DatabaseOrmHandler<Username>{
    private static UsernameServices instance = null;

    private UsernameServices() {
        super(Username.class);
    }

    public static UsernameServices getInstance() {

        if (instance == null) {
            instance = new UsernameServices();
        }
        return instance;
    }
}