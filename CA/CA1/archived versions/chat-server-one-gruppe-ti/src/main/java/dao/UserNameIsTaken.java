package dao;

/**
 * This is a temporable way to control duplicate usernames
 *
 * Created by scheldejonas on 15/02/2017.
 */
public class UserNameIsTaken extends Throwable {
    public UserNameIsTaken() {
        super("There is a 2 or more usernames in the User table with the same name");
    }
}
