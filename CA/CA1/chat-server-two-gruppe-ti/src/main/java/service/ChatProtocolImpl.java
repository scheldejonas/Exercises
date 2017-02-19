package service;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

/**
 * Created by scheldejonas on 18/02/2017.
 */
public class ChatProtocolImpl implements ChatProtocol {

    private String myUsername;

    @Override
    public String getMyUsername() {
        return this.myUsername;
    }

    @Override
    public void setMyUsername(String myUsername) {
        this.myUsername = myUsername;
    }

    @Override
    public void onServerAnalyseReceivedTextFromClient(String receivedText) {
        String commandTextLine = receivedText.substring(0,counToFirstHashValue(receivedText));
        System.out.printf("Incoming commandTextLine: %s \n", commandTextLine);
        String parameterTextLine = receivedText.substring(counToFirstHashValue(receivedText)+1, countToSecondHashValue(receivedText));
        System.out.printf("Incoming parameterTextLine: %s \n", parameterTextLine);
    }

    private int counToFirstHashValue(String receivedText) {
        return receivedText.indexOf('#',0);
    }

    private int countToSecondHashValue(String receivedText) {
        return receivedText.indexOf('#',counToFirstHashValue(receivedText)+1);
    }

    @Override
    public void onServerRespondWith(String loginText) {

    }

    @Override
    public void onServerRespondMessageToAllClients(String message) {

    }

    @Override
    public void onServerRespondMessageToPrivateClient(String fromUsername, String toUsername, String message) {

    }

    @Override
    public String onClientPrepareMessageToServer(String toUsername, String messageText) {
        System.err.println("onClientPrepareLoginToServer is not implemented on server");
        return null;
    }

    @Override
    public String onClientPrepareLoginToServer(String username) {
        System.err.println("onClientPrepareLoginToServer is not implemented on server");
        return null;
    }

    @Override
    public void onClientAnalyseReceivedTextFromServer(String receivedText) {
        throw new NotImplementedException();
    }

    @Override
    public void onClientUpdateUIWithUserList(List<String> usernameList) {
        throw new NotImplementedException();
    }

    @Override
    public void onClientUpdateUIWithLoginError() {
        throw new NotImplementedException();
    }

    @Override
    public void onClientUpdateUIWithUsernameNotAvailable(String errorText) {
        throw new NotImplementedException();
    }

    @Override
    public void onClientUpdateUIWithNewMessage(String fromUsername, String message) {
        throw new NotImplementedException();
    }

    @Override
    public void onClientUpdateUIWithNewUser(String username) {
        throw new NotImplementedException();
    }

    @Override
    public void onClientRemoveUserFromUI(String username) {
        throw new NotImplementedException();
    }
}
