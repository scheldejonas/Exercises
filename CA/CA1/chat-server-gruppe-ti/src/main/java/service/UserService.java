package service;

import dao.UserNameIsTaken;
import domain.User;

import java.util.List;

/**
 * Created by scheldejonas on 15/02/2017.
 */
public interface UserService {
    List<User> getAllUsers();

    List<User> getActiveUsers();

    void updateActive(User user);

    User getUserByUserName(String userName) throws UserNameIsTaken;
}
