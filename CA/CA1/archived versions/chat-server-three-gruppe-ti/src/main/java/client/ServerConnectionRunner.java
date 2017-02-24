package client;

import java.io.*;
import java.net.Socket;

/**
 * Created by scheldejonas on 16/02/2017.
 */
public class ServerConnectionRunner extends Client implements Runnable {

    protected PrintWriter printToServerStream = null;
    protected BufferedReader recieveFromServerStream = null;
    protected BufferedReader recieveFromClientSelf = null;
    private Socket clientSocket = null;
    private String username;

    public ServerConnectionRunner(Socket clientSocket, String username) {
        this.clientSocket = clientSocket;
        this.username = username;
    }

    public void run() {
        readyWritersAndReaders();
        System.err.printf("Client socket connection: %s\n", clientSocket.isConnected());
        while (!clientSocket.isClosed()) {
            waitFiveMiliSeconds();
            checkServerAndPrintToClient();
            checkClientAndPrintToServer();
        }
    }

    private void waitFiveMiliSeconds() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            System.err.println("Thread sleeping was interrupted in ServerConnectionRunner");
            e.printStackTrace();
        }
    }

    private void checkClientAndPrintToServer() {
        try {
            if (this.recieveFromClientSelf.ready()) {
                System.err.println("Service recieved a message from client");
                String recievedMessage = this.recieveFromClientSelf.readLine();
                String printToServerMessage = filterMessageWithProtocolAndReturnServerSend(recievedMessage);
                this.printToServerStream.println(recievedMessage);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String filterMessageWithProtocolAndReturnServerSend(String recievedText) {

        String commandTextLine = recievedText.substring(0,recievedText.indexOf('#',0));
        System.err.printf("Incoming commandTextLine: %s \n", commandTextLine);

        String parameterAndMessageTextLine = recievedText.substring(recievedText.indexOf('#',0) + 1);
        System.err.printf("Incoming parameterAndMessageTextLine: %s \n", parameterAndMessageTextLine);

        if (  commandTextLine.equals("LOGIN") ) {

            String username = recievedText.substring(recievedText.lastIndexOf('#') + 1);
            this.username = username;
            System.out.printf("LOGIN command for: %s \n", username);
            String activeUsers = "";

            for (ClientThread clientThread : clientThreadList) {

                clientThread.getOut().println("UPDATE#" + username);

                activeUsers += clientThread.getUsername() + "#";

            }

            this.getOut().printf("OK#%s\n",activeUsers);
        }

        if ( commandTextLine.equals("MSG") ) {

            String parameterTextLine = parameterAndMessageTextLine.substring(0, parameterAndMessageTextLine.indexOf('#', 0));
            System.out.printf("Incoming parameterTextLine: %s \n", parameterTextLine);

            String messageTextLine = parameterAndMessageTextLine.substring(parameterAndMessageTextLine.lastIndexOf('#') + 1);
            System.out.printf("Incoming messageTextLine: %s \n", messageTextLine);

            if (parameterTextLine.equals("ALL")) {
                System.out.println("Sending message to all clients");

                for (ClientThread clientThread : clientThreadList) {

                    clientThread.getOut().println("MSG#" + this.username + "#" + messageTextLine);

                }
            }

            if (!parameterTextLine.equals("ALL")) {
                System.out.println("Sending message privatly to specific user");

                this.getOut().println("MSG#" + parameterTextLine + "#" + messageTextLine);

                for (ClientThread clientThread : clientThreadList) {

                    System.out.println("Username testet: " + clientThread.getUsername());
                    if (parameterTextLine.equals(clientThread.getUsername())) {

                        clientThread.getOut().println("MSG#" + clientThread.getUsername() + "#" + messageTextLine);
                        System.out.println("Message " + messageTextLine + " sending to " + clientThread.getUsername());

                    }

                }

            }

        }

        return null;
    }


    private void checkServerAndPrintToClient() {
        try {
            if (this.recieveFromServerStream.ready()) {
                System.err.println("Service recieved a messsage from server");
                String messageToChat = checkRecievedWithProtocol(this.recieveFromServerStream.readLine());
                System.out.printf("%s\n", messageToChat);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String checkRecievedWithProtocol(String recievedText) {
        String commandTextLine = recievedText.substring(0,recievedText.indexOf('#',0));
        System.err.printf("Incoming commandTextLine: %s \n", commandTextLine);

        String parameterAndMessageTextLine = recievedText.substring(recievedText.indexOf('#',0) + 1);
        System.err.printf("Incoming parameterAndMessageTextLine: %s \n", parameterAndMessageTextLine);

        if (  commandTextLine.contains("LOGIN") ) {

            String username = recievedText.substring(recievedText.lastIndexOf('#') + 1);
            System.err.printf("LOGIN command for: %s \n", username);

            return String.format("Chat > Welcome %s. You are now logged into the chat.",username);
        }

        if ( commandTextLine.contains("MSG") ) {

            String message = recievedText.substring(recievedText.lastIndexOf('#') + 1);
            System.err.printf("MSG command with: %s \n", message);

            String username = parameterAndMessageTextLine.substring(0,parameterAndMessageTextLine.indexOf('#',0));
            System.err.printf("MSG command from username: %s \n",username);

            return String.format("%s > %s",username,message);
        }

        return null;
    }

    private void readyWritersAndReaders() {
        try {
            this.printToServerStream = new PrintWriter(clientSocket.getOutputStream(), true);
            this.recieveFromServerStream = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            this.recieveFromClientSelf = new BufferedReader(new InputStreamReader(System.in));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

