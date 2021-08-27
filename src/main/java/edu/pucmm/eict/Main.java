package edu.pucmm.eict;


import edu.pucmm.eict.controllers.ApiRestController;
import edu.pucmm.eict.controllers.MainController;
import edu.pucmm.eict.services.DatabaseSetupServices;
import edu.pucmm.eict.util.PushNotification;
import io.javalin.Javalin;
import io.javalin.plugin.rendering.JavalinRenderer;
import io.javalin.plugin.rendering.template.JavalinThymeleaf;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;


public class Main {
    public static void main(String[] args) throws SQLException, IOException, URISyntaxException, InterruptedException {
        //Creando la instancia del servidor.
        Javalin app = Javalin.create(config ->{
            config.addStaticFiles("/public"); //desde la carpeta de resources
            config.enableCorsForAllOrigins();
            config.requestCacheSize = Long.valueOf(2000000);
        });


        DatabaseSetupServices.startDb();

        //iniciando servidor
        app.start(7000);


        //Registrando manejador de plantillas THymeleaf.
        JavalinRenderer.register(JavalinThymeleaf.INSTANCE, ".html");

        new MainController(app).applyRoutes();
        new ApiRestController(app).applyRoutes();

    }
}
