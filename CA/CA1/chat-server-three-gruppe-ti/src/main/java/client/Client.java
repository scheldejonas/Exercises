package client;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by scheldejonas on 16/02/2017.
 */
public class Client implements Runnable {

    protected int portNumber = 8081;
    protected String host = "localhost";
    protected String username = null;
    private Socket clientSocket = null;
    protected Thread clientServerThread = null;
    protected ServerConnectionRunner serverConnectionRunner = null;
    private BufferedReader receiveFromClientSelf;

    @Override
    public void run() {
        waitOneSeconds();
        waitOneSeconds();
        startClientService();
    }

    public Client() {
    }

    public Client(String host, int portNumber) {
        this.portNumber = portNumber;
        this.host = host;
    }

    private void startClientService() {
        startWaitingForUsername();
        connectClientSocketToServerConnection();
        runMessagesFromConnectionInThread();
    }

    private void startWaitingForUsername() {
        System.err.println("Waiting for username being typed...");
        this.receiveFromClientSelf = new BufferedReader(new InputStreamReader(System.in));
        while (this.username == null) {
            try {
                if (receiveFromClientSelf.ready()) {
                    System.err.println("Username typed into System.in");
                    this.username = this.receiveFromClientSelf.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.err.println("Username is ready");
    }

    private void connectClientSocketToServerConnection() {
        this.clientSocket = new Socket();
        try {
            this.clientSocket.connect(new InetSocketAddress(this.host, this.portNumber));
            System.err.printf("Client is connected to %s:%s\n", this.host, this.portNumber);
            System.err.printf("ClientSocket Connected state %s\n",clientSocket.isConnected());
        } catch (IOException e) {
            e.printStackTrace();
        }
        waitOneSeconds();
    }

    private void runMessagesFromConnectionInThread() {
        this.serverConnectionRunner = new ServerConnectionRunner(clientSocket,username);
        this.clientServerThread = new Thread(this.serverConnectionRunner);
        this.clientServerThread.start();
    }

    private void waitOneSeconds() {
        try {
            Thread.sleep(1000);
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

}
