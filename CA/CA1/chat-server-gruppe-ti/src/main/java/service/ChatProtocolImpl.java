package service;

import domain.User;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

/**
 * This protocol is only meant to control the convetion of the communication commands.
 *
 * Created by scheldejonas on 15/02/2017.
 */
public class ChatProtocolImpl implements ChatProtocol {

    private UserService userService = UserServiceImpl.getInstance();

    public ChatProtocolImpl() {
    }

    /**
     * Logs in the client by adding it to the list of participants in the chat room
     *
     * @return
     */
    @Override
    public String commandLogIn(String userName) {

        User user = userService.getUserByUserName(userName);

        user.setActive(true);

        userService.updateActive(user);

        List<User> userList = userService.getActiveUsers();

        String activeUserStringSeperatedByHash = "";

        for (User userIterator : userList
             ) {
            activeUserStringSeperatedByHash += "#" + userIterator.getUserName();
        }

        return String.format("OK#%s",activeUserStringSeperatedByHash);
    }

    /**
     * Logging out a client should be discovered by the server when the TCP connection breaks.
     *
     * That should trigger a DELETE message sent from the server to all the remaining clients.
     *
     * @return
     */
    @Override
    public String commandLogOut() {
        throw new NotImplementedException();
    }

    @Override
    public String commandMessage() {
        throw new NotImplementedException();
    }

    @Override
    public String handleRecievedLine(String recievedText) {

        if ( recievedText.contains("LOGIN") ) {

            String userName = recievedText.substring(recievedText.lastIndexOf('#') + 1);

            return commandLogIn(userName);
        }

        if ( recievedText.contains("MSG") ) {
            String message = recievedText.substring()
        }

        return "NOT VALID CONNECTION";
    }

}
