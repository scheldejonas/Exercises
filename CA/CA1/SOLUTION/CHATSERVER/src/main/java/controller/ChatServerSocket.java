package controller;

import service.ClientsService;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by scheldejonas on 17/04/2017.
 */
public class ChatServerSocket implements Runnable {
    private ServerSocket serverSocket = null;
    private String host = "localhost";
    private int portNumber = 8084;
    private ClientsService clientsService = ClientsService.getSingleton();

    /**
     * Instantiates a new Chat Server on standard port.
     */
    public ChatServerSocket() {
        System.out.println("This server will start on host: " + this.host + " and port: " + this.portNumber);
    }

    /**
     * Instantiates a new Chat Server, with specific host and portNumber.
     * @param host
     * @param portNumber
     */
    public ChatServerSocket(String host, int portNumber) {
        this.host = host;
        this.portNumber = portNumber;
    }

    public void startRunningTheServer() {
        try {
            serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress(this.host, this.portNumber));
        } catch (IOException e) {
            System.out.println("...There was a problem binding the server to your port or host.");
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        ReentrantLock lock = new ReentrantLock();
        while (true) {
            try {
                System.out.println("...Server is bound to host: " + this.host + ":" + this.portNumber);
                System.out.println("...Chat server is now starting to accept clients.");
                Socket socket = serverSocket.accept();
                System.out.println("...Chat server started running on port " + portNumber + " succesfully.");
                lock.lock();
                clientsService.addNewClient( (Socket) socket );
                lock.unlock();
            } catch (IOException exception) {
                lock.unlock();
                System.out.println("...Accept failed on: " + portNumber);
                exception.printStackTrace();
            }
        }
    }
}
