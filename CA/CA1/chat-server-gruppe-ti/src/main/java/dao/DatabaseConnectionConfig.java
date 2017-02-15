package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This is to basic deliver a DB connection object according to sql accent and Drivers
 *
 * History:
 *  Version 1 - Using MySQL
 *
 * @version 1.0
 *
 * Created by scheldejonas on 15/02/2017.
 */
public class DatabaseConnectionConfig {

    public DatabaseConnectionConfig() {
    }

    public static Connection getConnection(String profile) throws SQLException, ClassNotFoundException, ConnectionProfileNotFoundException {

        System.out.printf("TEST: this is the string representation of the profiles: %s \n", Profiles.values().toString());

        if ( !profile.contains(Profiles.values().toString()) ) {
            throw new ConnectionProfileNotFoundException(profile);
        }

        String url = String.format("jdbc:mysql://%s:3306/%s?useSSL=false", Profiles.valueOf(profile).getHost(), Profiles.valueOf(profile).getSchemaName());

        Class.forName("com.mysql.jdbc.Driver");

        return DriverManager.getConnection(url, Profiles.valueOf(profile).getUserName(), Profiles.valueOf(profile).getPassWord());

    }
}
