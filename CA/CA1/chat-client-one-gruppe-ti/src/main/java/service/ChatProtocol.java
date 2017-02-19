package service;

import view.ScannerChatUI;

import java.util.List;

/**
 * Created by scheldejonas on 18/02/2017.
 */
public interface ChatProtocol {

    String getMyUsername();

    void setMyUsername(String myUsername);

    void onServerAnalyseReceivedTextFromClient(String receivedText);

    void onServerRespondWith(String loginText);

    void onServerRespondMessageToAllClients(String message);

    void onServerRespondMessageToPrivateClient(String fromUsername, String toUsername, String message);

    String onClientPrepareMessageToServer(String toUsername, String messageText);

    String onClientPrepareLoginToServer(String username);

    void onClientAnalyseReceivedTextFromServer(String receivedText);

    void onClientUpdateUIWithUserList(List<String> usernameList);

    void onClientUpdateUIWithLoginError();

    void onClientUpdateUIWithUsernameNotAvailable(String errorText);

    void onClientUpdateUIWithNewMessage(String fromUsername, String message);

    void onClientUpdateUIWithNewUser(String username);

    void onClientRemoveUserFromUI(String username);

    void setScannerChatIU(ScannerChatUI scannerChatIU);
}
