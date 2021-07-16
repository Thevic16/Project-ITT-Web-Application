package edu.pucmm.eict.controllers;

import edu.pucmm.eict.util.BaseController;
import io.javalin.Javalin;

public class MainController extends BaseController {

    public MainController(Javalin app) {
        super(app);
    }

    @Override
    public void applyRoutes() {

        //pagina principal
        app.get("/", ctx -> {
            ctx.render("/public/templates/1-outside-index.html");
        });

    }


}
