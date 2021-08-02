package edu.pucmm.eict.controllers;

import edu.pucmm.eict.models.Reminders;
import edu.pucmm.eict.models.UserTracing;
import edu.pucmm.eict.models.UserWheelchair;
import edu.pucmm.eict.models.Username;
import edu.pucmm.eict.services.UserTracingServices;
import edu.pucmm.eict.services.UserWheelchairServices;
import edu.pucmm.eict.services.UsernameServices;
import edu.pucmm.eict.util.BaseController;
import io.javalin.Javalin;

import java.util.*;

import static io.javalin.apibuilder.ApiBuilder.*;

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

        app.get("/login", ctx -> {
            ctx.render("/public/templates/2-outside-login.html");
        });

        app.get("/forgot", ctx -> {
            ctx.render("/public/templates/2.3-outside-forgot-message.html");
        });

        app.get("/select", ctx -> {
            ctx.render("/public/templates/2.0-outside-select-user.html");
        });

        app.get("/wheelchair", ctx -> {
            ctx.render("/public/templates/2.1-outside-regist-wheel.html");
        });

        app.get("/tracing", ctx -> {
            ctx.render("/public/templates/2.2-outside-regist-tracing.html.html");
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
                        Username usernameObject = new Username(username,password,true);
                        UsernameServices.getInstance().create(usernameObject);

                        UserWheelchair userWheelchair = new UserWheelchair(usernameObject,name,lastname,email,phone);
                        UserWheelchairServices.getInstance().create(userWheelchair);

                        ctx.redirect("/in/admin-regist-wheel");
                    }
                    else {
                        UserWheelchair userWheelchair = UserWheelchairServices.getInstance().find(username);

                        userWheelchair.getUsername().setPassword(password);
                        userWheelchair.setName(name);
                        userWheelchair.setLastname(lastname);
                        userWheelchair.setEmail(email);
                        userWheelchair.setPhoneNumber(phone);

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
                        Username usernameObject = new Username(username,password,false);
                        UsernameServices.getInstance().create(usernameObject);

                        UserTracing userTracing = new UserTracing(usernameObject,name,lastname,email,phone,userWheelchairList);
                        UserTracingServices.getInstance().create(userTracing);

                        ctx.redirect("/in/admin-regist-tracing");
                    }
                    else{
                        UserTracing userTracing = UserTracingServices.getInstance().find(username);

                        userTracing.getUsername().setPassword(password);
                        userTracing.setName(name);
                        userTracing.setLastname(lastname);
                        userTracing.setEmail(email);
                        userTracing.setPhoneNumber(phone);

                        userTracing.setUsersWheelchair(userWheelchairList);

                        UserTracingServices.getInstance().update(userTracing);

                        ctx.redirect("/in/admin-list-tracing");

                    }

                });


                get("/wheel-reminder-create", ctx -> {
                    ctx.render("/public/templates/3-in-wheel-reminder-create.html");
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
                    String dateEnd = ctx.formParam("dateEnd");

                    String edit = ctx.pathParam("edit");

                    if(edit.equalsIgnoreCase("false")){
                        //Reminder


                    }


                });

                get("/wheel-reminder-list", ctx -> {
                    ctx.render("/public/templates/4-in-wheel-reminder-list.html");
                });

                get("/wheel-routes", ctx -> {
                    ctx.render("/public/templates/5-in-wheel-routes.html");
                });

                get("/tracing-tracing", ctx -> {
                    ctx.render("/public/templates/6-in-tracing-tracing.html");
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
