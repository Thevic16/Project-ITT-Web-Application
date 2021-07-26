package edu.pucmm.eict.controllers;

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

                get("/admin-list-tracing", ctx -> {
                    Map<String, Object> model = new HashMap<>();
                    List<UserTracing> userTracingList = UserTracingServices.getInstance().findAll();

                    //List<String> list_listWheelchair = new ArrayList<String>();

                    /*
                    for (UserTracing userTracing: userTracingList) {
                        list_listWheelchair.add(userTracing.getListWheelchair());
                    }
                     */

                    model.put("userTracingList",userTracingList);
                    //model.put("list_listWheelchair",list_listWheelchair);

                    ctx.render("/public/templates/7.2-in-admin-list-tracing.html",model);
                });

                get("/admin-regist-wheel", ctx -> {
                    ctx.render("/public/templates/8-in-admin-regist-wheel.html");
                });

                post("/admin-regist-wheel", ctx -> {
                    String name = ctx.formParam("name");
                    String lastname = ctx.formParam("lastname");
                    String username = ctx.formParam("username");
                    String password = ctx.formParam("password");
                    String email = ctx.formParam("email");
                    String phone = ctx.formParam("phone");

                    Username usernameObject = new Username(username,password,true);
                    UsernameServices.getInstance().create(usernameObject);

                    UserWheelchair userWheelchair = new UserWheelchair(usernameObject,name,lastname,email,phone);
                    UserWheelchairServices.getInstance().create(userWheelchair);

                    ctx.redirect("/in/admin-regist-wheel");
                });

                get("/admin-regist-tracing", ctx -> {

                    List<UserWheelchair> userWheelchairList = UserWheelchairServices.getInstance().findAll();
                    Map<String, Object> model = new HashMap<>();
                    model.put("userWheelchairList",userWheelchairList);

                    ctx.render("/public/templates/9-in-admin-regist-tracing.html",model);
                });

                post("/admin-regist-tracing", ctx -> {
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

                    Username usernameObject = new Username(username,password,false);
                    UsernameServices.getInstance().create(usernameObject);

                    UserTracing userTracing = new UserTracing(usernameObject,name,lastname,email,phone,userWheelchairList);

                    UserTracingServices.getInstance().create(userTracing);


                    ctx.redirect("/in/admin-regist-tracing");
                });


                get("/wheel-reminder-create", ctx -> {
                    ctx.render("/public/templates/3-in-wheel-reminder-create.html");
                });

                get("/wheel-reminder-list", ctx -> {
                    ctx.render("/public/templates/4-in-wheel-reminder-list.html");
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


    }


}
