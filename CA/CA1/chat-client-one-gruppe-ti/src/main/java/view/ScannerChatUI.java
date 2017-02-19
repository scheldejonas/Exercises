package view;

import controller.ChatClientThread;

import java.util.List;
import java.util.Scanner;

/**
 * Created by scheldejonas on 18/02/2017.
 */
public class ScannerChatUI {

    private ChatClientThread chatClientService = null;

    private boolean readyToChat = false;

    public ScannerChatUI() {
        boolean connectionIsConnected = false;
        while (!connectionIsConnected) {
            if (this.readyToChat) {
                connectionIsConnected = true;
            } else {
                startScannerUI();
            }
        }
        startChatRoom();
    }

    public void startScannerUI() {
        System.out.println("\nHello chatter\n" +
                "\n" +
                "Here you will be able to chat with people from all over the world!");
        System.out.println("\nThe next thing you wanna do is typing your username right here below, and push enter.");
        String username = startScannerAndWaitForOneLineBeforeContinue();
        System.out.println("\nThank you for your username, the next is to give the domain or ip of the host you would like to " +
                "connect to.");
        String host = startScannerAndWaitForOneLineBeforeContinue();
        if (host.equals("")) {
            host = "localhost";
        }
        System.out.println("\nThank you for the host, the next this is to provide the port on the host, which " +
                "the server is started on.");
        String portNumber = startScannerAndWaitForOneLineBeforeContinue();
        if (portNumber.equals("")) {
            portNumber = "8081";
        }
        System.out.println("\nThank you for the port, we will now connect you.");
        connectToChatServer(username,host,portNumber);
        waitFiveSeonds();
    }

    private void connectToChatServer(String username, String host, String portNumber) {
        this.chatClientService = new ChatClientThread(host,portNumber);
        this.chatClientService.setScannerChatUI(this);
        this.chatClientService.setUsername(username);
        this.chatClientService.tryToStartThread();
        waitOneSecond();
    }

    private void waitFiveSeonds() {
        try {
            Thread.sleep(5*1000);
        } catch (InterruptedException e) {
            System.err.println("Waiting 5 seconds in Scanner Chat UI was interrupted!");
            e.printStackTrace();
        }
    }

    private void startChatRoom() {
        while (true) {
            System.out.println("\nWho do you wanna write to, all or a specific user? type below 'all' or the 'username' and push enter.");
            String newReceiveUsername = startScannerAndWaitForOneLineBeforeContinue();
            System.out.println("\nThank you, what do you wanna tell?. type below and push enter.");
            String newMessage = startScannerAndWaitForOneLineBeforeContinue();
            String newTextToServer = this.chatClientService.getServerConnectionThread().getChatProtocol().onClientPrepareMessageToServer(newReceiveUsername,newMessage);
            this.chatClientService.getServerConnectionThread().printToServer(newTextToServer);
            waitTwoSecond();
        }
    }

    private void waitTwoSecond() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.err.println("Waiting 2 seconds in Scanner Chat UI was interrupted!");
            e.printStackTrace();
        }
    }

    private void waitOneSecond() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.err.println("Waiting 1 second in Scanner Chat UI was interrupted!");
            e.printStackTrace();
        }
    }

    private String startScannerAndWaitForOneLineBeforeContinue() {
        Scanner scanner = new Scanner(System.in);
        scanner.reset();
        String newTextLine = null;
        while (newTextLine == null) {
            if (scanner.hasNextLine()) {
                newTextLine = scanner.nextLine();
            }
        }
        return newTextLine;
    }

    public boolean isReadyToChat() {
        return readyToChat;
    }

    public void setReadyToChat(boolean readyToChat) {
        this.readyToChat = readyToChat;
    }

    public void printActiveUsernameList(List<String> usernameList) {
        System.out.println("\n This is the list of active users");
        for (String string : usernameList) {
            System.out.printf(" ActiveUser: %s\n",string);
        }
        System.out.println("\n That is the current list of active users");
    }

    public void printErrorOnLogin() {
        System.out.println("\nwe are sorry but there was an error on login, you will be prompted to try again.");
    }

    public void printErrorOnText(String errorText) {
        System.err.println(String.format("\n%s",errorText));
    }

    public void printNewMessage(String fromUsername, String message) {
        System.out.printf("\n%s > %s\n",fromUsername,message);
    }
}
