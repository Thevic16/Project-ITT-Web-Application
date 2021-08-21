package edu.pucmm.eict.controllers;

import edu.pucmm.eict.models.*;
import edu.pucmm.eict.services.ReminderServices;
import edu.pucmm.eict.services.UserTracingServices;
import edu.pucmm.eict.services.UserWheelchairServices;
import edu.pucmm.eict.services.UsernameServices;
import edu.pucmm.eict.util.BaseController;
import edu.pucmm.eict.util.EmailUtility;
import edu.pucmm.eict.util.ReminderSchedule;
import edu.pucmm.eict.util.ReminderScheduleUtil;
import io.javalin.Javalin;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

import static io.javalin.apibuilder.ApiBuilder.*;

public class MainController extends BaseController {

    private HashMap<Integer, ReminderScheduleUtil> ReminderScheduleUtilMap;

    public MainController(Javalin app) {
        super(app);

        this.ReminderScheduleUtilMap = new HashMap<Integer, ReminderScheduleUtil>();
    }

    @Override
    public void applyRoutes() {

        //pagina principal
        app.get("/", ctx -> {
            ctx.render("/public/templates/1-outside-index.html");
        });

        app.get("/login", ctx -> {
            ctx.render("/public/templates/2-outside-login.html");
        });

        app.get("/forgot", ctx -> {
            ctx.render("/public/templates/2.3-outside-forgot-message.html");
        });

        app.post("/forgot", ctx -> {
            String name = ctx.formParam("name");
            String lastname = ctx.formParam("lastname");
            String email = ctx.formParam("email");
            String phone = ctx.formParam("phone");

            EmailUtility emailUtility = new EmailUtility();
            emailUtility.sendMail( "proyectosilladeruedasitt@gmail.com", "Formulario restablecimiento de usuario/contraseña.", "Nombre:"+name+"\r\n"+"Apellido:"+lastname+"\r\n"+"Correo electrónico: "+email+"\r\n"+"Teléfono:"+phone);


            ctx.redirect("/");

        });

        app.get("/select", ctx -> {
            ctx.render("/public/templates/2.0-outside-select-user.html");
        });

        app.get("/wheelchair", ctx -> {
            ctx.render("/public/templates/2.1-outside-regist-wheel.html");
        });

        app.post("/wheelchair", ctx -> {
            String name = ctx.formParam("name");
            String lastname = ctx.formParam("lastname");
            String username = ctx.formParam("username");
            String password = ctx.formParam("password");
            String email = ctx.formParam("email");
            String phone = ctx.formParam("phone");

            EmailUtility emailUtility = new EmailUtility();
            emailUtility.sendMail( "proyectosilladeruedasitt@gmail.com", "Solicitud Usuario silla de ruedas.", "Nombre:"+name+"\r\n"+"Apellido:"+lastname+"\r\n"+"Usuario:"+username+"\r\n"+"Contraseña:"+password+"\r\n"+"Correo electrónico: "+email+"\r\n"+"Teléfono:"+phone);


            ctx.redirect("/wheelchair");

        });

        app.get("/tracing", ctx -> {
            List<UserWheelchair> userWheelchairList = UserWheelchairServices.getInstance().findAll();
            Map<String, Object> model = new HashMap<>();
            model.put("userWheelchairList",userWheelchairList);

            ctx.render("/public/templates/2.2-outside-regist-tracing.html.html",model);
        });

        app.post("/tracing", ctx -> {
            String name = ctx.formParam("name");
            String lastname = ctx.formParam("lastname");
            String username = ctx.formParam("username");
            String password = ctx.formParam("password");
            String email = ctx.formParam("email");
            String phone = ctx.formParam("phone");

            List<String> usersNameWheelchair = ctx.formParams("usersNameWheelchair");

            String usersTracking = "";

            usersTracking = usersNameWheelchair.get(0);

            for (int i=1;i<usersNameWheelchair.size();i++){
                usersTracking += "," + usersNameWheelchair.get(i);
            }

            EmailUtility emailUtility = new EmailUtility();
            emailUtility.sendMail( "proyectosilladeruedasitt@gmail.com", "Solicitud Usuario seguimiento.", "Nombre:"+name+"\r\n"+"Apellido:"+lastname+"\r\n"+"Usuario:"+username+"\r\n"+"Contraseña:"+password+"\r\n"+"Correo electrónico: "+email+"\r\n"+"Teléfono:"+phone+"\r\n"+"Usuario(s) seguir:"+ usersTracking);

            ctx.redirect("/tracing");

        });


        app.post("/validate-login", ctx -> {
            String username = ctx.formParam("username");
            String password = ctx.formParam("password");
            String rememberMe = ctx.formParam("rememberMe");

            if(username.equalsIgnoreCase("admin") && password.equals("admin")){
                ctx.redirect("/in/admin-list-wheel");
                ctx.sessionAttribute("logged", username);

                if(rememberMe != null){
                    ctx.cookie("rememberMe", username);
                }

                return;
            }

            Username usernameObject = UsernameServices.getInstance().find(username);

            if(usernameObject != null){
                if(usernameObject.getUsername().equalsIgnoreCase(username) && usernameObject.getPassword().equalsIgnoreCase(password)){

                    if(usernameObject.getIswheelchair()){
                        ctx.redirect("/in/wheel-reminder-create");
                        ctx.sessionAttribute("logged", username);

                        if(rememberMe != null){
                            ctx.cookie("rememberMe", username);
                        }
                        return;
                    }
                    else{
                        ctx.redirect("/in/tracing-tracing");
                        ctx.sessionAttribute("logged", username);

                        if(rememberMe != null){
                            ctx.cookie("rememberMe", username);
                        }

                        return;
                    }

                }
                else {
                    ctx.redirect("/login");
                }
            }

        });

        app.routes(() -> {
            path("/in", () -> {

                before( ctx -> {
                    String logged = ctx.sessionAttribute("logged");
                    String rememberMe = ctx.cookie("rememberMe");
                    System.out.println("rememberMe "+rememberMe);
                    if (logged == null) {

                        if (rememberMe == null) {
                            ctx.redirect("/login");
                        }
                        else  {
                            ctx.sessionAttribute("logged", rememberMe);
                        }
                    }
                });

                get("/admin-list-wheel", ctx -> {
                    Map<String, Object> model = new HashMap<>();
                    List<UserWheelchair> userWheelchairList = UserWheelchairServices.getInstance().findAll();
                    model.put("userWheelchairList",userWheelchairList);

                    ctx.render("/public/templates/7.1-in-admin-list-wheel.html",model);
                });

                get("/admin-list-wheel/delete/:username", ctx -> {
                    String username = ctx.pathParam("username");
                    UserWheelchairServices.getInstance().delete(username);
                    UsernameServices.getInstance().delete(username);

                    ctx.redirect("/in/admin-list-wheel");
                });

                get("/admin-list-wheel/edit/:username", ctx -> {
                    String username = ctx.pathParam("username");
                    UserWheelchair userWheelchair = UserWheelchairServices.getInstance().find(username);

                    Map<String, Object> model = new HashMap<>();
                    model.put("userWheelchair",userWheelchair);
                    model.put("edit",true);

                    ctx.render("/public/templates/8-in-admin-regist-wheel.html",model);
                });

                get("/admin-list-tracing", ctx -> {
                    Map<String, Object> model = new HashMap<>();
                    List<UserTracing> userTracingList = UserTracingServices.getInstance().findAll();

                    model.put("userTracingList",userTracingList);

                    ctx.render("/public/templates/7.2-in-admin-list-tracing.html",model);
                });

                get("/admin-list-tracing/delete/:username", ctx -> {
                    String username = ctx.pathParam("username");
                    UserTracingServices.getInstance().delete(username);
                    UsernameServices.getInstance().delete(username);

                    ctx.redirect("/in/admin-list-tracing");
                });

                get("/admin-list-tracing/edit/:username", ctx -> {
                    String username = ctx.pathParam("username");
                    UserTracing userTracing = UserTracingServices.getInstance().find(username);

                    List<UserWheelchair> userWheelchairList = UserWheelchairServices.getInstance().findAll(); //Looking for the existing wheelchair users.



                    Map<String, Object> model = new HashMap<>();
                    model.put("userWheelchair",userTracing);
                    model.put("edit",true);
                    model.put("userWheelchairList",userWheelchairList);

                    ctx.render("/public/templates/9-in-admin-regist-tracing.html",model);
                });

                get("/admin-regist-wheel", ctx -> {
                    Map<String, Object> model = new HashMap<>();
                    model.put("edit",false);

                    ctx.render("/public/templates/8-in-admin-regist-wheel.html",model);
                });

                post("/admin-regist-wheel/:edit", ctx -> {
                    String name = ctx.formParam("name");
                    String lastname = ctx.formParam("lastname");
                    String username = ctx.formParam("username");
                    String password = ctx.formParam("password");
                    String email = ctx.formParam("email");
                    String phone = ctx.formParam("phone");


                    String edit = ctx.pathParam("edit");

                    if(edit.equalsIgnoreCase("false")){
                        Username usernameObject = new Username(username,password,true,name,lastname,email,phone);
                        UsernameServices.getInstance().create(usernameObject);

                        UserWheelchair userWheelchair = new UserWheelchair(usernameObject);
                        UserWheelchairServices.getInstance().create(userWheelchair);

                        ctx.redirect("/in/admin-regist-wheel");
                    }
                    else {
                        UserWheelchair userWheelchair = UserWheelchairServices.getInstance().find(username);

                        userWheelchair.getUsername().setPassword(password);
                        userWheelchair.getUsername().setName(name);
                        userWheelchair.getUsername().setLastname(lastname);
                        userWheelchair.getUsername().setEmail(email);
                        userWheelchair.getUsername().setPhoneNumber(phone);

                        UserWheelchairServices.getInstance().update(userWheelchair);

                        ctx.redirect("/in/admin-list-wheel");
                    }

                });

                get("/admin-regist-tracing", ctx -> {

                    List<UserWheelchair> userWheelchairList = UserWheelchairServices.getInstance().findAll();
                    Map<String, Object> model = new HashMap<>();
                    model.put("userWheelchairList",userWheelchairList);
                    model.put("edit",false);

                    ctx.render("/public/templates/9-in-admin-regist-tracing.html",model);
                });

                post("/admin-regist-tracing/:edit", ctx -> {
                    String name = ctx.formParam("name");
                    String lastname = ctx.formParam("lastname");
                    String username = ctx.formParam("username");
                    String password = ctx.formParam("password");
                    String email = ctx.formParam("email");
                    String phone = ctx.formParam("phone");

                    List<String> usersNameWheelchair = ctx.formParams("usersNameWheelchair");
                    List<UserWheelchair> userWheelchairList = new ArrayList<UserWheelchair>();

                    for (String usernameWheelchair:usersNameWheelchair) {
                        UserWheelchair userWheelchair = UserWheelchairServices.getInstance().find(usernameWheelchair);
                        userWheelchairList.add(userWheelchair);
                    }

                    String edit = ctx.pathParam("edit");
                    if(edit.equalsIgnoreCase("false")){
                        Username usernameObject = new Username(username,password,false,name,lastname,email,phone);
                        UsernameServices.getInstance().create(usernameObject);

                        UserTracing userTracing = new UserTracing(usernameObject,userWheelchairList);
                        UserTracingServices.getInstance().create(userTracing);

                        ctx.redirect("/in/admin-regist-tracing");
                    }
                    else{
                        UserTracing userTracing = UserTracingServices.getInstance().find(username);

                        userTracing.getUsername().setPassword(password);
                        userTracing.getUsername().setName(name);
                        userTracing.getUsername().setLastname(lastname);
                        userTracing.getUsername().setEmail(email);
                        userTracing.getUsername().setPhoneNumber(phone);

                        userTracing.setUsersWheelchair(userWheelchairList);

                        UserTracingServices.getInstance().update(userTracing);

                        ctx.redirect("/in/admin-list-tracing");

                    }

                });


                get("/wheel-reminder-create", ctx -> {
                    Map<String, Object> model = new HashMap<>();
                    model.put("edit",false);

                    ctx.render("/public/templates/3-in-wheel-reminder-create.html",model);
                });

                post("/wheel-reminder-create/:edit", ctx -> {
                    String desciption = ctx.formParam("desciption");
                    String monday = ctx.formParam("monday");
                    String tuesday = ctx.formParam("tuesday");
                    String wednesday = ctx.formParam("wednesday");
                    String thursday = ctx.formParam("thursday");
                    String friday = ctx.formParam("friday");
                    String saturday = ctx.formParam("saturday");
                    String sunday = ctx.formParam("sunday");
                    String hour = ctx.formParam("hour");
                    String always = ctx.formParam("always");
                    String dateEnd = ctx.formParam("dateEnd");

                    Boolean mondayBoolean;
                    Boolean tuesdayBoolean;
                    Boolean wednesdayBoolean;
                    Boolean thursdayBoolean;
                    Boolean fridayBoolean;
                    Boolean saturdayBoolean;
                    Boolean sundayBoolean;
                    Boolean alwaysBoolean;

                    if(monday != null){
                        mondayBoolean = true;
                    }
                    else {
                        mondayBoolean = false;
                    }

                    if(tuesday != null){
                        tuesdayBoolean = true;
                    }
                    else {
                        tuesdayBoolean = false;
                    }

                    if(wednesday != null){
                        wednesdayBoolean = true;
                    }
                    else {
                        wednesdayBoolean = false;
                    }

                    if(thursday != null){
                        thursdayBoolean = true;
                    }
                    else {
                        thursdayBoolean = false;
                    }

                    if(friday != null){
                        fridayBoolean = true;
                    }
                    else {
                        fridayBoolean = false;
                    }

                    if(saturday != null){
                        saturdayBoolean = true;
                    }
                    else {
                        saturdayBoolean = false;
                    }

                    if(sunday != null){
                        sundayBoolean = true;
                    }
                    else {
                        sundayBoolean = false;
                    }

                    if(always != null){
                        alwaysBoolean = true;
                    }
                    else {
                        alwaysBoolean = false;
                    }


                    LocalTime hourLocalTime=  LocalTime.of(Integer.parseInt(hour.substring(0,2)),Integer.parseInt(hour.substring(3,5)));

                    LocalDate dateEndLocalDate = null;

                    if(!alwaysBoolean){
                        dateEndLocalDate = LocalDate.of(Integer.parseInt(dateEnd.substring(0,4)),Integer.parseInt(dateEnd.substring(5,7)),Integer.parseInt(dateEnd.substring(8,10)));
                    }

                    String edit = ctx.pathParam("edit");

                    if(edit.equalsIgnoreCase("false")){

                        String username = ctx.sessionAttribute("logged");

                        Reminder reminder = new Reminder(desciption,mondayBoolean,tuesdayBoolean,wednesdayBoolean,thursdayBoolean,fridayBoolean,saturdayBoolean,sundayBoolean,hourLocalTime,dateEndLocalDate,UsernameServices.getInstance().find(username),alwaysBoolean);
                        ReminderServices.getInstance().create(reminder);

                        ReminderScheduleUtil reminderScheduleUtil = new ReminderScheduleUtil();

                        reminderScheduleUtil.setReminderSchedule(desciption,mondayBoolean,tuesdayBoolean,wednesdayBoolean,thursdayBoolean,fridayBoolean,saturdayBoolean,sundayBoolean,hourLocalTime,dateEndLocalDate,UsernameServices.getInstance().find(username),alwaysBoolean);

                        this.ReminderScheduleUtilMap.put(reminder.getId(),reminderScheduleUtil);

                        ctx.redirect("/in/wheel-reminder-create");
                    }
                    else {
                        int id = ctx.formParam("id",int.class).get();

                        Reminder reminder = ReminderServices.getInstance().find(id);

                        reminder.setDesciption(desciption);
                        reminder.setMonday(mondayBoolean);
                        reminder.setTuesday(tuesdayBoolean);
                        reminder.setWednesday(wednesdayBoolean);
                        reminder.setThursday(thursdayBoolean);
                        reminder.setFriday(fridayBoolean);
                        reminder.setSaturday(saturdayBoolean);
                        reminder.setSunday(sundayBoolean);
                        reminder.setHour(hourLocalTime);
                        reminder.setDateEnd(dateEndLocalDate);
                        reminder.setAlways(alwaysBoolean);

                        ReminderServices.getInstance().update(reminder);

                        //Reminder email
                        ReminderScheduleUtil reminderScheduleUtil = this.ReminderScheduleUtilMap.get(id);
                        reminderScheduleUtil.cancelReminderSchedule();

                        ReminderScheduleUtil reminderScheduleUtilnew = new ReminderScheduleUtil();
                        this.ReminderScheduleUtilMap.put(id,reminderScheduleUtilnew);
                        reminderScheduleUtilnew.setReminderSchedule(desciption,mondayBoolean,tuesdayBoolean,wednesdayBoolean,thursdayBoolean,fridayBoolean,saturdayBoolean,sundayBoolean,hourLocalTime,dateEndLocalDate,reminder.getUsername(),alwaysBoolean);


                        ctx.redirect("/in/wheel-reminder-list");

                    }

                });

                get("/wheel-reminder-list", ctx -> {
                    Map<String, Object> model = new HashMap<>();

                    String username = ctx.sessionAttribute("logged");
                    List<Reminder> reminders = Reminder.findRemandersByUsername(username);

                    model.put("reminders",reminders);

                    ctx.render("/public/templates/4-in-wheel-reminder-list.html",model);
                });

                get("/wheel-reminder-list/delete/:id", ctx -> {
                    int id = ctx.pathParam("id",int.class).get();

                    ReminderScheduleUtil reminderScheduleUtil = this.ReminderScheduleUtilMap.get(id);
                    reminderScheduleUtil.cancelReminderSchedule();

                    ReminderServices.getInstance().delete(id);

                    ctx.redirect("/in/wheel-reminder-list");
                });


                get("/wheel-reminder-list/edit/:id", ctx -> {
                    int id = ctx.pathParam("id",int.class).get();

                    Reminder reminder = ReminderServices.getInstance().find(id);

                    Map<String, Object> model = new HashMap<>();

                    model.put("reminder",reminder);
                    model.put("edit",true);

                    ctx.render("/public/templates/3-in-wheel-reminder-create.html",model);
                });


                get("/wheel-routes", ctx -> {
                    ctx.render("/public/templates/5-in-wheel-routes.html");
                });


                get("/tracing-tracing", ctx -> {
                    Map<String, Object> model = new HashMap<>();

                    String usernameTracing = ctx.sessionAttribute("logged");
                    List<FallEvent> fallEvents = FallEvent.findFallEventsByUsername(usernameTracing);

                    model.put("fallEvents",fallEvents);


                    ctx.render("/public/templates/6-in-tracing-tracing.html",model);
                });

                get("/logout", ctx -> {
                    ctx.removeCookie("rememberMe", "/");
                    ctx.sessionAttribute("logged", null);
                    ctx.redirect("/");
                });

            });
        });


        app.exception(Exception.class, (e, ctx) -> {
            // handle general exceptions here
            // will not trigger if more specific exception-mapper found
            ctx.render("/public/templates/11-error.html");
        });

        app.error(404, ctx -> {
            ctx.render("/public/templates/10-no-found.html");
        });

    }

}
