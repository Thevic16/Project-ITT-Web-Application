package edu.pucmm.eict.controllers;

import edu.pucmm.eict.models.FallEvent;
import edu.pucmm.eict.models.FallEventOutside;
import edu.pucmm.eict.models.Position;
import edu.pucmm.eict.models.Username;
import edu.pucmm.eict.services.FallEventServices;
import edu.pucmm.eict.services.PositionServices;
import edu.pucmm.eict.services.UsernameServices;
import edu.pucmm.eict.util.BaseController;
import io.javalin.Javalin;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static io.javalin.apibuilder.ApiBuilder.*;

public class ApiRestController extends BaseController {


    public ApiRestController(Javalin app) {
        super(app);
    }

    @Override
    public void applyRoutes() {

        app.routes(() -> {
            path("/api", () -> {

                post("/FallEvent", ctx -> {
                    String body = ctx.body();
                    FallEventOutside tmp = ctx.bodyAsClass(FallEventOutside.class);

                    System.out.println(body);

                    Position position = new Position(tmp.getLatitude(),tmp.getLongitude());
                    PositionServices.getInstance().create(position);

                    Username username = UsernameServices.getInstance().find(tmp.getUsername());


                    LocalDate dateTime = LocalDate.of(Integer.parseInt(tmp.getDateTime().substring(0,4)),Integer.parseInt(tmp.getDateTime().substring(5,7)),Integer.parseInt(tmp.getDateTime().substring(8,10)));

                    FallEvent fallEvent = new FallEvent(username,tmp.getPhoto(),position,dateTime);
                    FallEventServices.getInstance().create(fallEvent);

                });

            });
        });

    }
}
