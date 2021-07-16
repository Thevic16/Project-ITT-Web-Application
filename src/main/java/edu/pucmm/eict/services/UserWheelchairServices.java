package edu.pucmm.eict.services;

import edu.pucmm.eict.models.UserWheelchair;

public class UserWheelchairServices extends DatabaseOrmHandler<UserWheelchair>{

    private static UserWheelchairServices instance = null;

    private UserWheelchairServices() {
        super(UserWheelchair.class);
    }

    public static UserWheelchairServices getInstance() {

        if (instance == null) {
            instance = new UserWheelchairServices();
        }
        return instance;
    }

}
