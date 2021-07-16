package edu.pucmm.eict.services;

import java.sql.SQLException;
import org.h2.tools.Server;

public class DatabaseSetupServices {


    private static Server server;
    private static DatabaseSetupServices instance = null;

    private DatabaseSetupServices() {

    }

    public static DatabaseSetupServices getInstance() {
        if (instance == null) {
            instance = new DatabaseSetupServices();
        }
        return instance;
    }
    /**
     *
     //  * @throws SQLException
     //  */
    public static void startDb() throws SQLException {
        server = Server.createTcpServer("-tcpPort", "9092", "-tcpAllowOthers", "-ifNotExists").start();

        String status = Server.createWebServer("-trace", "-webPort", "0").start().getStatus();
        System.out.println("Web Status: "+status);
    }

    /**
     *
     * @throws SQLException
     */
    public static void stopDb() throws SQLException {
        server.shutdown();
    }

}
