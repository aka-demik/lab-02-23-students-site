package models.dao;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {
    private static Logger logger = Logger.getLogger(Connector.class);
    private static Connector ourInstance = new Connector();
    private Connection con;

    private Connector() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            logger.fatal("Postgresql driver not found", e);
        }

        try {
            logger.info("Connecting to DB");
            con = DriverManager.getConnection(
                    "jdbc:postgresql://localhost/newdb",
                    "postgres",
                    "sergtsop");
            logger.info("DB connected");
        } catch (SQLException e) {
            logger.fatal(e.getMessage(), e);
        }
    }

    public static Connection get() {
        return ourInstance.con;
    }
}
