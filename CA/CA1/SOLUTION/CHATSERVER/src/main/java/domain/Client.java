package domain;

/**
 * Created by scheldejonas on 17/04/2017.
 */
public abstract class Client {
    private User user = new User();
    private boolean active = false;

    public Client() {
    }

    public User getUser() {
        return user;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
