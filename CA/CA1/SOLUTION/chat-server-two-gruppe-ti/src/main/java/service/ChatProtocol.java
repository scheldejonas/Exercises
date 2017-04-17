package service;

import java.util.List;

/**
 * Created by scheldejonas on 18/02/2017.
 */
public interface ChatProtocol {

    /**
     * On server analyse received text from client.
     *
     * @param receivedText the received text
     */
    void onServerAnalyseReceivedTextFromClient(String receivedText);

    /**
     * On server respond to client login.
     *
     * @param username the username
     */
    void onServerRespondToClientLogin(String username);

    /**
     * On server respond update on new user to active clients.
     *
     * @param newUser the new user
     */
    void onServerRespondUpdateOnNewUserToActiveClients(String newUser);

    /**
     * On server respond message to all clients.
     *
     * @param fromUsername the from username
     * @param message      the message
     */
    void onServerRespondMessageToAllClients(String fromUsername, String message);

    /**
     * On server respond message to private client.
     *
     * @param fromUsername the from username
     * @param toUsername   the to username
     * @param message      the message
     */
    void onServerRespondMessageToPrivateClient(String fromUsername, String toUsername, String message);

    /**
     * On server send remove user.
     *
     * @param username the username
     */
    void onServerSendRemoveUser(String username);

    /**
     * On client prepare message to server string.
     *
     * @param toUsername  the to username
     * @param messageText the message text
     * @return the string
     */
    String onClientPrepareMessageToServer(String toUsername, String messageText);

    /**
     * On client prepare login to server string.
     *
     * @param username the username
     * @return the string
     */
    String onClientPrepareLoginToServer(String username);

    /**
     * On client analyse received text from server.
     *
     * @param receivedText the received text
     */
    void onClientAnalyseReceivedTextFromServer(String receivedText);

    /**
     * On client update ui with user list.
     *
     * @param usernameList the username list
     */
    void onClientUpdateUIWithUserList(List<String> usernameList);

    /**
     * On client update ui with login error.
     */
    void onClientUpdateUIWithLoginError();

    /**
     * On client update ui with username not available.
     *
     * @param errorText the error text
     */
    void onClientUpdateUIWithUsernameNotAvailable(String errorText);

    /**
     * On client update ui with new message.
     *
     * @param fromUsername the from username
     * @param message      the message
     */
    void onClientUpdateUIWithNewMessage(String fromUsername, String message);

    /**
     * On client update ui with new user.
     *
     * @param username the username
     */
    void onClientUpdateUIWithNewUser(String username);

    /**
     * On client remove user from ui.
     *
     * @param username the username
     */
    void onClientRemoveUserFromUI(String username);
}