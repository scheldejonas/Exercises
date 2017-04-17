package service;

import controller.ChatClientThread;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import view.ScannerChatUI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by scheldejonas on 18/02/2017.
 */
public class ChatProtocolImpl implements ChatProtocol {

    private ChatClientThread chatClientThread = null;
    private ScannerChatUI scannerChatUI = null;

    public ChatProtocolImpl() {
    }

    @Override
    public void onServerAnalyseReceivedTextFromClient(String receivedText) {
        throw new NotImplementedException();
    }

    @Override
    public void onServerRespondToClientLogin(String username) {
        throw new NotImplementedException();
    }

    @Override
    public void onServerRespondUpdateOnNewUserToActiveClients(String newUser) {
        throw new NotImplementedException();
    }

    @Override
    public void onServerRespondMessageToAllClients(String fromUsername, String message) {
        throw new NotImplementedException();
    }

    @Override
    public void onServerRespondMessageToPrivateClient(String fromUsername, String toUsername, String message) {
        throw new NotImplementedException();
    }

    @Override
    public String onClientPrepareMessageToServer(String toUsername, String messageText) {
        return String.format("MSG#%s#%s", toUsername, messageText);
    }

    @Override
    public String onClientPrepareLoginToServer(String username) {
        return String.format("LOGIN#%s",username);
    }

    @Override
    public void onClientAnalyseReceivedTextFromServer(String receivedText) {
        String commandTextLine = "";
        String parameterAndRestOfTextLine = "";
        if (receivedText.equals("FAIL")) {
            onClientUpdateUIWithLoginError();
        } else if (receivedText.equals("T")) {
            commandTextLine = "TEST";
        } else {
            commandTextLine = receivedText.substring(0, countToFirstHashValue(receivedText));
            parameterAndRestOfTextLine = receivedText.substring(countToFirstHashValue(receivedText)+1);
        }
        if (commandTextLine.equals("OK")) {
            String[] usernamesArray = receivedText.split("#");
            List<String> usernameList = Arrays.asList(usernamesArray);
            this.chatClientThread.setActiveUsersListExceptFirstString(usernameList);
            onClientUpdateUIWithUserList(this.chatClientThread.getActiveUsersList());
            this.scannerChatUI.setReadyToChat(true);
        }
        if (commandTextLine.equals("FAIL")) {
            onClientUpdateUIWithUsernameNotAvailable("Username: " + this.chatClientThread.getUsername() + " was not available.");
            onClientUpdateUIWithLoginError();
        }
        if (commandTextLine.equals("UPDATE")) {
            onClientUpdateUIWithNewUser(parameterAndRestOfTextLine);
        }
        if (commandTextLine.equals("DELETE")) {
            onClientRemoveUserFromUI(parameterAndRestOfTextLine);
        }
        if (commandTextLine.equals("MSG")) {
            String fromUsername = receivedText.substring(countToFirstHashValue(receivedText)+1, countToSecondHashValue(receivedText));
            String message = parameterAndRestOfTextLine.substring(countToFirstHashValue(parameterAndRestOfTextLine)+1);
            onClientUpdateUIWithNewMessage(fromUsername, message);
        }
    }

    @Override
    public void onClientUpdateUIWithUserList(List<String> usernameList) {
        this.scannerChatUI.printActiveUsernameList(usernameList);
    }

    @Override
    public void onClientUpdateUIWithLoginError() {
        this.scannerChatUI.printErrorOnLogin();
    }

    private int countToFirstHashValue(String receivedText) {
        return receivedText.indexOf('#',0);
    }

    private int countToSecondHashValue(String receivedText) {
        return receivedText.indexOf('#', countToFirstHashValue(receivedText)+1);
    }

    @Override
    public void onClientUpdateUIWithUsernameNotAvailable(String errorText) {
        this.scannerChatUI.printErrorOnText(errorText);
    }

    @Override
    public void onClientUpdateUIWithNewMessage(String fromUsername, String message) {
        this.scannerChatUI.printNewMessage(fromUsername,message);
    }

    @Override
    public void onClientUpdateUIWithNewUser(String username) {
        this.chatClientThread.getActiveUsersList().add(username);
        scannerChatUI.printActiveUsernameList(this.chatClientThread.getActiveUsersList());
    }

    @Override
    public void onClientRemoveUserFromUI(String username) {
        System.out.println("\n " + username + " left the chat room.");
        List<String> newActiveUserList = new ArrayList<>();
        for (int i = 0; i < this.getChatClientThread().getActiveUsersList().size(); i++) {
            if (!this.getChatClientThread().getActiveUsersList().get(i).toLowerCase().equals(username.toLowerCase())) {
                newActiveUserList.add(this.getChatClientThread().getActiveUsersList().get(i));
            }
        }
        this.getChatClientThread().setActiveUsersList(newActiveUserList);
        onClientUpdateUIWithUserList(this.getChatClientThread().getActiveUsersList());
    }

    public ScannerChatUI getScannerChatUI() {
        return scannerChatUI;
    }

    public void setScannerChatUI(ScannerChatUI scannerChatUI) {
        this.scannerChatUI = scannerChatUI;
    }

    public ChatClientThread getChatClientThread() {
        return chatClientThread;
    }

    public void setChatClientThread(ChatClientThread chatClientThread) {
        this.chatClientThread = chatClientThread;
    }
}
