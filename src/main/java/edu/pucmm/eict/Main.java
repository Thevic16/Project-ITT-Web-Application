package edu.pucmm.eict;


import edu.pucmm.eict.services.DatabaseSetupServices;
import io.javalin.Javalin;
import io.javalin.plugin.rendering.JavalinRenderer;
import io.javalin.plugin.rendering.template.JavalinThymeleaf;

import java.sql.SQLException;


public class Main {
    public static void main(String[] args) throws SQLException {
        //Creando la instancia del servidor.
        Javalin app = Javalin.create(config ->{
            config.addStaticFiles("/public"); //desde la carpeta de resources
            config.enableCorsForAllOrigins();
        });


        DatabaseSetupServices.startDb();

        //iniciando servidor
        app.start(7000);


        //Registrando manejador de plantillas THymeleaf.
        JavalinRenderer.register(JavalinThymeleaf.INSTANCE, ".html");


        app.get("/", ctx -> {
            ctx.render("/public/templates/1-outside-index.html");
        });
    }
}
