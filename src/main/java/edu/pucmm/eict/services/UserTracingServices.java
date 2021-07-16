package edu.pucmm.eict.services;


import edu.pucmm.eict.models.UserTracing;

public class UserTracingServices extends DatabaseOrmHandler<UserTracing>{
    private static UserTracingServices instance = null;

    private UserTracingServices() {
        super(UserTracing.class);
    }

    public static UserTracingServices getInstance() {

        if (instance == null) {
            instance = new UserTracingServices();
        }
        return instance;
    }
}
