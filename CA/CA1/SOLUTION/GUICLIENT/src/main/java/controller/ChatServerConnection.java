package controller;

import service.ClientService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by schelde on 18/04/17.
 */
public class ChatServerConnection implements Runnable {
    private static final ChatServerConnection singleton = new ChatServerConnection();
    private int portNumber = 8084;
    private String host = "localhost";
    private Socket clientSocket = null;
    private ReentrantLock reentrantLock = new ReentrantLock(true);
    private Thread thread = null;
    private PrintWriter printLinesToServer = null;
    private BufferedReader receiveLinesFromServer = null;

    private ClientService clientService = ClientService.getSingleton();

    private ChatServerConnection() {
        System.out.println("...GUI Client being connected on host: " + this.host + ":" + this.portNumber);
        clientService.setLock(reentrantLock);
    }

    public static ChatServerConnection getSingleton() {
        return singleton;
    }

    public void setConnectionLocation(String host, int portNumber) {
        this.host = host;
        this.portNumber = portNumber;
    }

    public void connectClientToServer() {
        System.out.println("...Starting to connect client to server on: " + this.host + ":" + this.portNumber);
        this.clientSocket = new Socket();
        try {
            this.clientSocket.connect(new InetSocketAddress(this.host, this.portNumber));
        } catch (IOException e) {
            System.err.println("...While trying to connect client to server, binding, was not possible");
            e.printStackTrace();
        }
        System.out.println("...Client is successfully connected to server");
        System.out.println("...Preparing the sending and receiving text streams.");
        try {
            this.printLinesToServer = new PrintWriter(this.clientSocket.getOutputStream(),true);
        } catch (IOException e) {
            System.err.println("...While trying to open print stream to server, there was an error.");
            try {
                this.clientSocket.close();
            } catch (IOException e1) {
                System.out.println("...During error on trying to mount print stream, on client, it was not possible to close client socket stream.");
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
        try {
            this.receiveLinesFromServer = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
        } catch (IOException e) {
            System.out.println("...While trying to open read stream from server, there was an error.");
            try {
                this.clientSocket.close();
            } catch (IOException e1) {
                System.out.println("...During error on trying to mount input stream, on client, it was not possible to close client socket.");
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
        this.thread = new Thread(this);
        this.thread.start();
        System.out.println("...Server is connected and reader to send and receive text to and from client to server.");
    }

    @Override
    public void run() {
        while (!this.clientSocket.isClosed()) {
            String newReceivedText = "";
            try {
                newReceivedText = receiveLinesFromServer.readLine();
            } catch (IOException e) {
                reentrantLock.unlock();
                System.err.println("...There was a problem trying to read a line from the server");
                e.printStackTrace();
            }
            try {
                if (newReceivedText != null) {
                    reentrantLock.lock();
                    System.out.println("...Server send a text line with this content: " + newReceivedText);
                    clientService.newTextFromServer(newReceivedText);
                    reentrantLock.unlock();
                }
            } catch (Throwable throwable) {
                System.err.println("...While recieving text from server, there was a problem.");
                throwable.printStackTrace();
            }
        }
    }

    public boolean isConnected() {
        return this.clientSocket.isConnected();
    }

    public void sentTextToServer(String text) {
        System.out.println("...Sending new text to the server: " + text);
        this.printLinesToServer.println(text);
    }
}
