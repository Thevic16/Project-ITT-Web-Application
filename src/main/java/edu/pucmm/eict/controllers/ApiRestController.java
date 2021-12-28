package edu.pucmm.eict.controllers;

import com.google.gson.Gson;
import edu.pucmm.eict.models.*;
import edu.pucmm.eict.services.FallEventServices;
import edu.pucmm.eict.services.PositionServices;
import edu.pucmm.eict.services.UsernameServices;
import edu.pucmm.eict.util.BaseController;
import edu.pucmm.eict.util.EmailFallEvent;
import edu.pucmm.eict.util.PushNotification;
import io.javalin.Javalin;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
                        System.out.println(body);
                        FallEventOutside tmp = ctx.bodyAsClass(FallEventOutside.class);

                        //verify username and password
                        Username usernameObject = UsernameServices.getInstance().find(tmp.getUsername());

                        if(usernameObject == null){
                            ctx.json("false");
                        }


                        // Condition to verify a correct username, password and rol in the application.

                        if (usernameObject.getUsername().equalsIgnoreCase(tmp.getUsername()) && usernameObject.getPassword().equalsIgnoreCase(tmp.getPassword()) && usernameObject.getIswheelchair()) {

                            Position position = new Position(tmp.getLatitude(), tmp.getLongitude());
                            PositionServices.getInstance().create(position);
                            Username username = UsernameServices.getInstance().find(tmp.getUsername());


                            LocalDate dateTime = LocalDate.of(Integer.parseInt(tmp.getDateTime().substring(0, 4)), Integer.parseInt(tmp.getDateTime().substring(5, 7)), Integer.parseInt(tmp.getDateTime().substring(8, 10)));

                            LocalTime hourLocalTime = LocalTime.of(Integer.parseInt(tmp.getHour().substring(0, 2)), Integer.parseInt(tmp.getHour().substring(3, 5)));


                            //Verifing if a photo have been sent
                            String photoFallEvent = null;
                            if(tmp.getPhoto().length() >= 100){
                                photoFallEvent = tmp.getPhoto();

                            }
                            else {
                                photoFallEvent = "data:image/jpeg;base64,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";
                             }

                            FallEvent fallEvent = new FallEvent(username, photoFallEvent, position, dateTime, hourLocalTime);
                            FallEventServices.getInstance().create(fallEvent);

                            //Send email.

                            //Numbers for GPRS/SMS module.
                            List<String> numbersSMS = new ArrayList<>();

                            for (UserTracing userTracing : UserTracing.getListUsersTracingByUserWheelchair(username)) {
                                String to = userTracing.getUsername().getEmail();
                                String subject = "¡Se ha detectado una posible caída!";
                                String content = "Usuario:" + username.getUsername() + " <br> Nombre:" + username.getName() + " <br> Apellido:" + username.getLastname();
                                String photo = fallEvent.getPhoto().substring(23);


                                EmailFallEvent emailFallEvent = new EmailFallEvent();
                                emailFallEvent.sendMail(to, subject, content, photo, fallEvent.getPosition().getLatitude(), fallEvent.getPosition().getLongitude());

                                try {
                                    PushNotification.sendPushNotification("¡Se ha detectado una posible caída!", "Información: \n Usuario:" + username.getUsername() + " \n Nombre:" + username.getName() + " \n  Apellido:" + username.getLastname());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                } catch (URISyntaxException e) {
                                    e.printStackTrace();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                numbersSMS.add("+1"+userTracing.getUsername().getPhoneNumber().replace("-",""));
                            }

                            Map<String, Object> JsonForSMS =  new HashMap<String, Object>();;
                            JsonForSMS.put("username",usernameObject.getUsername());
                            JsonForSMS.put("name",usernameObject.getName());
                            JsonForSMS.put("lastname",usernameObject.getLastname());
                            JsonForSMS.put("numbers",numbersSMS);

                            ctx.json(new Gson().toJson(JsonForSMS));
                        }
                        else {
                            ctx.json("false");
                        }
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
