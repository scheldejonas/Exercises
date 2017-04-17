package domain;

/**
 * Created by scheldejonas on 17/04/2017.
 */
public abstract class Client {
    private User user = new User();

    public Client() {
    }

    public User getUser() {
        return user;
    }
}
