package service;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import view.ScannerChatUI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by scheldejonas on 18/02/2017.
 */
public class ChatProtocolImpl implements ChatProtocol {

    private String myUsername;

    private ScannerChatUI scannerChatUI = null;

    private List<String> activeUsernameList = new ArrayList<>();

    public ChatProtocolImpl() {
    }

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
        throw new NotImplementedException();
    }

    @Override
    public void onServerRespondWith(String loginText) {
        throw new NotImplementedException();
    }

    @Override
    public void onServerRespondMessageToAllClients(String message) {
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
        String commandTextLine = receivedText.substring(0, countToFirstHashValue(receivedText));
        System.out.printf("Incoming command text line: %s\n", commandTextLine);
        String parameterTextLine = receivedText.substring(countToFirstHashValue(receivedText)+1, countToSecondHashValue(receivedText));
        System.out.printf("Incoming paramter text line: %s\n", parameterTextLine);
        String parameterAndRestOfTextLine = receivedText.substring(countToFirstHashValue(receivedText)+1);
        System.out.printf("Incoming parameter and rest of text line: %s\n",parameterAndRestOfTextLine);
        if (commandTextLine.equals("OK")) {
            String[] usernamesArray = parameterAndRestOfTextLine.split("#");
            this.activeUsernameList = Arrays.asList(usernamesArray);
            onClientUpdateUIWithUserList(this.activeUsernameList);
            this.scannerChatUI.setReadyToChat(true);
        }
        if (commandTextLine.equals("FAIL")) {
            onClientUpdateUIWithUsernameNotAvailable("Username: " + this.myUsername + " was not available.");
            onClientUpdateUIWithLoginError();
        }
        if (commandTextLine.equals("UPDATE")) {
            onClientUpdateUIWithNewUser(parameterTextLine);
        }
        if (commandTextLine.equals("DELETE")) {
            onClientRemoveUserFromUI(parameterTextLine);
        }
        if (commandTextLine.equals("MSG")) {
            String message = parameterAndRestOfTextLine.substring(countToFirstHashValue(parameterAndRestOfTextLine));
            onClientUpdateUIWithNewMessage(parameterTextLine, message);
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
    public void onClientUpdateUIWithNewMessage(String usernameAndMessageText, String message) {
        String fromUsername = usernameAndMessageText.substring(0,countToFirstHashValue(usernameAndMessageText));
        this.scannerChatUI.printNewMessage(fromUsername,message);
    }

    @Override
    public void onClientUpdateUIWithNewUser(String username) {
        this.activeUsernameList.add(username);

    }

    @Override
    public void onClientRemoveUserFromUI(String username) {
        int indexToRemove = -1;
        for (String string : activeUsernameList) {
            if (string.equals(username)) {
                indexToRemove = activeUsernameList.indexOf(string);
            }
        }
        if (indexToRemove != -1) {
            activeUsernameList.remove(indexToRemove);
        } else {
            this.scannerChatUI.printErrorOnText(String.format("\nServer wanted to remove user: %s, but he wasn't existing in your active user list",username));
        }
    }

    @Override
    public void setScannerChatIU(ScannerChatUI scannerChatIU) {
        this.scannerChatUI = scannerChatIU;
    }


}
