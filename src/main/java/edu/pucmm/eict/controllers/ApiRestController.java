package edu.pucmm.eict.controllers;

import edu.pucmm.eict.models.*;
import edu.pucmm.eict.services.FallEventServices;
import edu.pucmm.eict.services.PositionServices;
import edu.pucmm.eict.services.UsernameServices;
import edu.pucmm.eict.util.BaseController;
import edu.pucmm.eict.util.EmailFallEvent;
import io.javalin.Javalin;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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

                    try {
                        String body = ctx.body();
                        FallEventOutside tmp = ctx.bodyAsClass(FallEventOutside.class);

                        System.out.println(body);
                        Position position = new Position(tmp.getLatitude(),tmp.getLongitude());
                        PositionServices.getInstance().create(position);
                        Username username = UsernameServices.getInstance().find(tmp.getUsername());


                        LocalDate dateTime = LocalDate.of(Integer.parseInt(tmp.getDateTime().substring(0,4)),Integer.parseInt(tmp.getDateTime().substring(5,7)),Integer.parseInt(tmp.getDateTime().substring(8,10)));

                        LocalTime hourLocalTime=  LocalTime.of(Integer.parseInt(tmp.getHour().substring(0,2)),Integer.parseInt(tmp.getHour().substring(3,5)));


                        FallEvent fallEvent = new FallEvent(username,tmp.getPhoto(),position,dateTime,hourLocalTime);
                        FallEventServices.getInstance().create(fallEvent);

                        //Send email.


                        for (UserTracing userTracing:UserTracing.getListUsersTracingByUserWheelchair(username)) {
                            String to = userTracing.getUsername().getEmail();
                            String subject = "¡Se ha detectado una posible caída!";
                            String content = "Usuario:"+username.getUsername()+" <br> Nombre:"+username.getName()+" <br> Apellido:"+username.getLastname();
                            String photo = fallEvent.getPhoto().substring(23);

                            EmailFallEvent emailFallEvent = new EmailFallEvent();
                            emailFallEvent.sendMail(to,subject,content,photo,fallEvent.getPosition().getLatitude(),fallEvent.getPosition().getLongitude());
                        }

                        ctx.json("true");
                    }
                    catch (Exception e){
                        System.out.println("Something went wrong with the API-REST. \n");
                        System.out.println(e);

                        ctx.json("false");
                    }

                });

            });
        });

    }
}
