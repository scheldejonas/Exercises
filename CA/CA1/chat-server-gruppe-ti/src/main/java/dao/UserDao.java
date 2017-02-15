package dao;

import domain.User;

import java.util.List;

/**
 * Created by scheldejonas on 15/02/2017.
 */
public interface UserDao {

    List<User> getAllUsers();

    List<User> getActiveUsers();

    void updateActive(User user);

}
