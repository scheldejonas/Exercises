package dao;

/**
 * Created by scheldejonas on 15/02/2017.
 */
public class ConnectionProfileNotFoundException extends Exception {
    public ConnectionProfileNotFoundException(String profile) {
        super( String.format("You need to re-validate your connection details or re-write profile name for database connection, you typed %s \n", profile) );
    }
}
