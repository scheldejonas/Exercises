package service;

import dao.UserDaoImpl;
import dao.UserNameIsTaken;
import domain.User;

import java.util.List;

/**
 * Created by scheldejonas on 15/02/2017.
 */
public class UserServiceImpl implements UserService {

    private static final UserServiceImpl singleton = new UserServiceImpl();

    private UserDaoImpl userDao = UserDaoImpl.getInstance();

    private UserServiceImpl() {
    }

    public static UserServiceImpl getInstance() {
        return singleton;
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public List<User> getActiveUsers() {
        return userDao.getActiveUsers();
    }

    @Override
    public void updateActive(User user) {
        userDao.updateActive(user);
    }

    @Override
    public User getUserByUserName(String userName) throws UserNameIsTaken {
        return userDao.getUserByUserName(userName);
    }
}
