package edu.pucmm.eict.util;

import io.javalin.Javalin;

public abstract class BaseController {

    protected Javalin app;

    public BaseController(Javalin app) {
        this.app = app;
    }

    public abstract void applyRoutes();
}

