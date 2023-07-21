package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Util implements AutoCloseable {
    private static Connection connection;
    private static EntityManagerFactory entityManagerFactory;
    private static final String DB_NAME = "testdb";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "testtest";
    private static final Logger LOGGER = Logger.getLogger(Util.class.getName());

    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + DB_NAME +
                        "?user=" + DB_USER + "&password=" + DB_PASS);
                LOGGER.log(Level.INFO, "Successful connection");
            } catch (SQLException e) {
                LOGGER.log(Level.INFO, "Unsuccessful connection");
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static Properties getProperties() {
        Properties settings = new Properties();
        settings.put(AvailableSettings.DRIVER, "com.mysql.cj.jdbc.Driver");
        settings.put(AvailableSettings.URL, "jdbc:mysql://localhost:3306/testdb?serverTimezone=UTC");
        settings.put(AvailableSettings.USER, DB_USER);
        settings.put(AvailableSettings.PASS, DB_PASS);
        settings.put(AvailableSettings.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
        settings.put(AvailableSettings.SHOW_SQL, "true");
        settings.put(AvailableSettings.CURRENT_SESSION_CONTEXT_CLASS, "thread");
        settings.put(AvailableSettings.HBM2DDL_AUTO, "");
        return settings;
    }

    public static EntityManagerFactory getEntityManagerFactory(Properties prop) {
        Configuration configuration = new Configuration();
        configuration.setProperties(prop);
        configuration.addAnnotatedClass(User.class);
        if (entityManagerFactory == null) {
            try {
                entityManagerFactory =
                        Persistence.createEntityManagerFactory("users", configuration.getProperties());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return entityManagerFactory;
    }

    @Override
    public void close() {
        try {
            entityManagerFactory.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
