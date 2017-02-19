package service;

import controller.ChatServer;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by scheldejonas on 18/02/2017.
 */
public class ChatProtocolImpl implements ChatProtocol {

    private ClientThread clientThread = null;

    public ChatProtocolImpl() {
    }

    @Override
    public void onServerAnalyseReceivedTextFromClient(String receivedText) {
        String commandTextLine = receivedText.substring(0, countToFirstHashValue(receivedText));
        System.out.printf("Incoming command text line: %s\n", commandTextLine);
        String parameterAndRestOfTextLine = receivedText.substring(countToFirstHashValue(receivedText)+1);
        System.out.printf("Incoming parameter and rest of text line: %s\n",parameterAndRestOfTextLine);
        if (  commandTextLine.equals("LOGIN") ) {
            if (!usernameIsTakenInActiveUsers(parameterAndRestOfTextLine)) {
                this.clientThread.setUsername(parameterAndRestOfTextLine);
                onServerRespondUpdateOnNewUserToActiveClients(this.clientThread.getUsername());
                onServerRespondToClientLogin(this.clientThread.getUsername());
            } else {
                this.clientThread.getToClientPrintOutWriter().println("FAIL");
            }
        }
        if ( commandTextLine.equals("MSG") ) {
            String toUsername = receivedText.substring(countToFirstHashValue(receivedText)+1, countToSecondHashValue(receivedText));
            String fromUsername = this.clientThread.getUsername();
            String messageTextLine = parameterAndRestOfTextLine.substring(parameterAndRestOfTextLine.lastIndexOf('#') + 1);
            if (toUsername.toUpperCase().equals("ALL")) {
                onServerRespondMessageToAllClients(fromUsername,messageTextLine);
            }
            if (!toUsername.toUpperCase().equals("ALL")) {
                onServerRespondMessageToPrivateClient(fromUsername,toUsername,messageTextLine);
            }
        }
    }

    private boolean usernameIsTakenInActiveUsers(String newUsername) {
        for (ClientThread clientThread : clientThread.getChatServer().getClientThreadList()) {
            System.out.println("Checking this: " + newUsername + " with this " + clientThread.getUsername());
            if (newUsername.equals(clientThread.getUsername())){
                return true;
            }
        }
        return false;
    }

    @Override
    public void onServerRespondToClientLogin(String username) {
        String activeUsersExceptNewUser = "";
        System.out.println("Before reponding active user list, size of client list is: " + clientThread.getChatServer().getClientThreadList().size());
        for (ClientThread clientThread : clientThread.getChatServer().getClientThreadList()) {
            activeUsersExceptNewUser += "#" + clientThread.getUsername();
        }
        this.clientThread.getToClientPrintOutWriter().println("OK" + activeUsersExceptNewUser);
        System.out.println("OK with all active users sent back to new logged in user.");
    }

    @Override
    public void onServerRespondUpdateOnNewUserToActiveClients(String newUser) {
        for (ClientThread clientThread : clientThread.getChatServer().getClientThreadList()) {
            if (!clientThread.getUsername().toLowerCase().equals(newUser.toLowerCase())){
                clientThread.getToClientPrintOutWriter().println("UPDATE#" + newUser);
                System.out.println("Update with new user: " + newUser + " sent to " + clientThread.getUsername());
            }
        }
    }

    private int countToFirstHashValue(String receivedText) {
        return receivedText.indexOf('#',0);
    }

    private int countToSecondHashValue(String receivedText) {
        return receivedText.indexOf('#',countToFirstHashValue(receivedText)+1);
    }

    @Override
    public void onServerRespondMessageToAllClients(String fromUsername, String message) {
        System.out.println("Sending message to all clients");
        for (ClientThread clientThread : clientThread.getChatServer().getClientThreadList()) {
            clientThread.getToClientPrintOutWriter().println("MSG#" + fromUsername + "#" + message);
        }
    }

    @Override
    public void onServerRespondMessageToPrivateClient(String fromUsername, String toUsername, String message) {
        System.out.println("Sending message privatly from " + fromUsername + " to " + toUsername);
        this.clientThread.getToClientPrintOutWriter().println("MSG#" + fromUsername + "#" + message);
        for (ClientThread clientThread : clientThread.getChatServer().getClientThreadList()) {
            if (toUsername.equals(clientThread.getUsername())) {
                clientThread.getToClientPrintOutWriter().println("MSG#" + fromUsername + "#" + message);
            }
        }
    }

    @Override
    public void onServerSendRemoveUser(String username) {
        List<ClientThread> newCleanedActiveUserList = new ArrayList<>();
        for (ClientThread clientThread : this.clientThread.getChatServer().getClientThreadList()) {
            if (!clientThread.getUsername().toLowerCase().equals(username.toLowerCase())) {
                newCleanedActiveUserList.add(clientThread);
            }
        }
        this.clientThread.getChatServer().setClientThreadList(newCleanedActiveUserList);
        for (ClientThread clientThread : this.clientThread.getChatServer().getClientThreadList()) {
            clientThread.getToClientPrintOutWriter().println("DELETE#" + username);
            System.out.println("Remove " + username + " sent to " + clientThread.getUsername());
        }
        System.out.println("Done removing user and updating list at active clients.");
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

    public ClientThread getClientThread() {
        return clientThread;
    }

    public void setClientThread(ClientThread clientThread) {
        this.clientThread = clientThread;
    }
}
