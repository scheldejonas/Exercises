package service;

import java.util.List;

/**
 * Created by scheldejonas on 18/02/2017.
 */
public interface ChatProtocol {

    void onServerAnalyseReceivedTextFromClient(String receivedText);

    void onServerRespondToClientLogin(String username);

    void onServerRespondUpdateOnNewUserToActiveClients(String newUser);

    void onServerRespondMessageToAllClients(String fromUsername, String message);

    void onServerRespondMessageToPrivateClient(String fromUsername, String toUsername, String message);

    void onServerSendRemoveUser(String username);

    String onClientPrepareMessageToServer(String toUsername, String messageText);

    String onClientPrepareLoginToServer(String username);

    void onClientAnalyseReceivedTextFromServer(String receivedText);

    void onClientUpdateUIWithUserList(List<String> usernameList);

    void onClientUpdateUIWithLoginError();

    void onClientUpdateUIWithUsernameNotAvailable(String errorText);

    void onClientUpdateUIWithNewMessage(String fromUsername, String message);

    void onClientUpdateUIWithNewUser(String username);

    void onClientRemoveUserFromUI(String username);
}