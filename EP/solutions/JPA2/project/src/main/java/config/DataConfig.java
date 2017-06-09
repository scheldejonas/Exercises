package config;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by scheldejonas on 22/02/2017.
 */
public class DataConfig {

    private static DataConfig singleton = null;
    private EntityManagerFactory entityManagerFactory = null;
    private EntityManager entityManager = null;
    private String host = "";
    private String databaseName = "";
    private int port = 0;
    private String username = "";
    private String password = "";
    private String profile = "one";

    public static DataConfig getSingleton() {
        if (singleton == null) {
            singleton = new DataConfig();
        }
        return singleton;
    }

    private DataConfig() {
        if (profile.equals("one")) {
            this.host = "localhost";
            this.port = 3306;
            this.databaseName = "jpatwo";
            this.username = "jpatwouser";
            this.password = "DAmh?vAEUUYz}ZggmggRcB(tTwKm4b";
            this.entityManagerFactory = createEntityManagerFactory();
        }
        if (profile.equals("two")) {
            this.host = "localhost";
            this.port = 3306;
            this.databaseName = "kris?";
            this.username = "kris?";
            this.password = "kris?";
            this.entityManagerFactory = createEntityManagerFactory();
        }
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    private EntityManagerFactory createEntityManagerFactory() {
        Map myProperties = new HashMap();
        myProperties.put("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        myProperties.put("hibernate.connection.url", String.format("jdbc:mysql://%s:%s/%s?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false", host, port, databaseName));
        myProperties.put("hibernate.connection.username", String.format("%s", this.username));
        myProperties.put("hibernate.connection.password", String.format("%s", this.password));
        myProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQL57InnoDBDialect");
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("hibernate", myProperties);
        return entityManagerFactory;
    }

    public EntityManagerFactory getEntityManagerFactory() {
        return this.entityManagerFactory;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
}