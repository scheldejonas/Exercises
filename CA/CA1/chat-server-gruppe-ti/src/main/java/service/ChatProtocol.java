package service;

import controller.ChatServer;
import dao.UserDaoImpl;
import domain.User;

import java.util.List;

/**
 * Created by scheldejonas on 15/02/2017.
 */
public class ChatProtocol {

    private UserDaoImpl userDao = UserDaoImpl.getInstance();

    public ChatProtocol() {
    }

    public String commandLogIn() {

        List<User> userList = userDao.getActiveUsers();

        String activeUserStringSeperatedByHash = "";

        for (User user : userList
             ) {
            activeUserStringSeperatedByHash += "#" + user.getUserName();
        }

        return String.format("OK#%s",activeUserStringSeperatedByHash);
    }

    public String commandLogOut() {

    }
}
