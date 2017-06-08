package model;

/**
 * Created by scheldejonas on 11/04/2017.
 */
public class DatabaseTest {
    public static void main(String[] args) {
        Database database = Database.getSingleton();
        database.addOnePerson(new Long(1));
        database.addOnePerson(new Long(1));
        database.addOnePerson(new Long(2));
        database.addOnePerson(new Long(2));
    }
}
