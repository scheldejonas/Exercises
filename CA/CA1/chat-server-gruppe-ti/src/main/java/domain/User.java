package domain;

/**
 * Created by scheldejonas on 15/02/2017.
 */
public class User {
    private Long id;
    private String userName;
    private String password;
    private boolean isActive;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        this.isActive = active;
    }

    @Override
    public String toString() {
        return String.format("User: %s - %s identified by %s and active is %s",this.id, this.userName, this.password, this.isActive);
    }
}
