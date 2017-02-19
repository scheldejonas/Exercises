package controller;

import service.ServerConnectionThread;
import view.ScannerChatUI;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Created by scheldejonas on 16/02/2017.
 */
public class ChatClientThread implements Runnable {

    private int portNumber = 8081;
    private String host = "localhost";
    private String username = null;
    private Socket clientSocket = null;
    private Thread clientServerThread = null;
    private ServerConnectionThread serverConnectionThread = null;

    private ScannerChatUI scannerChatUI = null;

    @Override
    public void run() {
        waitHalfASecond();
        connectClientSocketToServerConnection();
        waitHalfASecond();
        runServerConnectionInThread();
    }

    public ChatClientThread() {
        System.out.println("Chat Client will be connecting to Localhost on port 8081, when started");
    }

    public ChatClientThread(String host, String portNumber) {
        this.portNumber = Integer.parseInt(portNumber);
        this.host = host;
    }

    public void tryToStartThread() {
        if (username == null) {
            System.err.println("Username has not been set yet, you are not able to connect to server without a username set on Chat Client instance");
            System.err.println("Thread is not started");
        } else {
            Thread thread = new Thread(this);
            thread.start();
        }
    }

    private void connectClientSocketToServerConnection() {
        if (host.equals("localhost") && this.portNumber == 8081) {
            System.out.println("Chat Client connection settings has now been changed since instance creation and will connect to localhost on port 8081");
        }
        this.clientSocket = new Socket();
        try {
            this.clientSocket.connect(new InetSocketAddress(this.host, this.portNumber));
            System.out.printf("ChatClientThread is connected to %s:%s\n", this.host, this.portNumber);
            System.out.printf("ClientSocket Connected state %s\n",clientSocket.isConnected());
        } catch (IOException e) {
            System.err.println("There was an error while trying to connect to server through TCP");
            e.printStackTrace();
        }
    }

    private void runServerConnectionInThread() {
        this.serverConnectionThread = new ServerConnectionThread(clientSocket,username);
        this.serverConnectionThread.setScannerChatUI(this.scannerChatUI);
        this.clientServerThread = new Thread(this.serverConnectionThread);
        this.clientServerThread.start();
    }

    private void waitHalfASecond() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            System.err.println("Thread was interrupted while starting client service");
            e.printStackTrace();
        }
    }

    public int getPortNumber() {
        return portNumber;
    }

    public String getHost() {
        return host;
    }

    public String getUsername() {
        return username;
    }

    public void setPortNumber(int portNumber) {
        this.portNumber = portNumber;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ScannerChatUI getScannerChatUI() {
        return scannerChatUI;
    }

    public void setScannerChatUI(ScannerChatUI scannerChatUI) {
        this.scannerChatUI = scannerChatUI;
    }

    public ServerConnectionThread getServerConnectionThread() {
        return serverConnectionThread;
    }

    public void setServerConnectionThread(ServerConnectionThread serverConnectionThread) {
        this.serverConnectionThread = serverConnectionThread;
    }
}
