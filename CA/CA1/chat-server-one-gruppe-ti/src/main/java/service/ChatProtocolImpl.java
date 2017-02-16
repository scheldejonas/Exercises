package service;

import dao.UserNameIsTaken;
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

        User user = null;

        String activeUserStringSeperatedByHash = "";

        try {

            user = userService.getUserByUserName(userName);

            user.setActive(true);

            userService.updateActive(user);

            List<User> userList = userService.getActiveUsers();
            System.out.printf("TEST - active user list size in command log in %s \n",userList.size());

            for (User userIterator : userList
                    ) {
                activeUserStringSeperatedByHash += "#" + userIterator.getUserName();
            }

            return String.format("OK#%s",activeUserStringSeperatedByHash);

        } catch (UserNameIsTaken userNameIsTaken) {
            System.out.println(userNameIsTaken.getMessage());
            userNameIsTaken.printStackTrace();
        }

        return String.format("FAIL\n");

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
    public String commandMessage(String message, String userName) {

        String responseMessageToClient = "";

        try {
            User user = userService.getUserByUserName(userName);
        } catch (UserNameIsTaken userNameIsTaken) {
            userNameIsTaken.printStackTrace();
        }


        return responseMessageToClient;
    }

    @Override
    public String handleRecievedLine(String recievedText) {

        String commandTextLine = recievedText.substring(0,recievedText.indexOf('#',0));
        System.out.printf("TEST - commandTextLine: %s \n", commandTextLine);

        String parameterAndMessageTextLine = recievedText.substring(recievedText.indexOf('#',0) + 1);
        System.out.printf("TEST - parameterAndMessageTextLine: %s \n", parameterAndMessageTextLine);

        if (  commandTextLine.contains("LOGIN") ) {

            String userName = recievedText.substring(recievedText.lastIndexOf('#') + 1);
            System.out.printf("TEST - userName on LOGIN command: %s \n", userName);

            return commandLogIn(userName);
        }

        if ( commandTextLine.contains("MSG") ) {

            String message = recievedText.substring(recievedText.lastIndexOf('#') + 1);
            System.out.printf("TEST - message: %s \n", message);

            String userName = parameterAndMessageTextLine.substring(0,parameterAndMessageTextLine.indexOf('#',0));
            System.out.printf("TEST - userName on MSG command: %s \n",userName);

            return commandMessage(message, userName);
        }

        return "NOT VALID CONNECTION";
    }

}
