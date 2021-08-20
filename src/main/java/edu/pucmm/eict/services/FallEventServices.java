package edu.pucmm.eict.services;

import edu.pucmm.eict.models.FallEvent;


public class FallEventServices extends DatabaseOrmHandler<FallEvent> {
    private static FallEventServices instance = null;

    private FallEventServices() {
        super(FallEvent.class);
    }

    public static FallEventServices getInstance() {

        if (instance == null) {
            instance = new FallEventServices();
        }
        return instance;
    }

}
