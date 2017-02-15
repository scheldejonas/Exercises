package dao;

/**
 * This is a temporable way to control duplicate usernames
 *
 * Created by scheldejonas on 15/02/2017.
 */
public class UserNameIsDuplicate extends Throwable {
    public UserNameIsDuplicate() {
        super("There is a 2 or more usernames in the User table with the same name");
    }
}
