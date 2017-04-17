package controller;

import service.ClientThread;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * Created by scheldejonas on 16/02/2017.
 */
public class ChatServer {

    private ServerSocket serverSocket = null;
    private String host = "localhost";
    private int portNumber = 8081;
    private List<ClientThread> clientThreadList = new ArrayList<>();

    /**
     * Instantiates a new Chat server.
     */
    public ChatServer() {
        System.out.println("This server will start on host: " + this.host + " and port: " + this.portNumber);
    }

    /**
     * Instantiates a new Chat server.
     *
     * @param host       the host
     * @param portNumber the port number
     */
    public ChatServer(String host, int portNumber) {
        this.host = host;
        this.portNumber = portNumber;
    }

    private void startAcceptingClients() {
        while (true) {
            try {
                System.out.printf("Starting to wait for now clients connecting...\n");
                Socket socket = serverSocket.accept();
                System.out.printf("Client connected on return port: %s \n", socket.getLocalPort());
                ClientThread clientThread = new ClientThread(socket);
                clientThreadList.add(clientThread);
                waitOneSecond();
                clientThread.setChatServer(this);
                waitOneSecond();
                Thread thread = new Thread(clientThread);
                thread.start();
                System.out.printf("Client added to the list with a new size: %s\n",clientThreadList.size());
            } catch (IOException e) {
                System.err.println("Accept failen on: " + portNumber);
                e.printStackTrace();
            }
        }
    }

    private void waitOneSecond() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.err.println("There was a error or interruption, while waiting for sleep of one second");
            e.printStackTrace();
        }
    }

    private void waitHalfASecond() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            System.err.println("There was a error or interruption, while waiting for sleep of half a second");
            e.printStackTrace();
        }
    }

    /**
     * Gets client thread list.
     *
     * @return the client thread list
     */
    public List<ClientThread> getClientThreadList() {
        return clientThreadList;
    }

    /**
     * Sets client thread list.
     *
     * @param clientThreadList the client thread list
     */
    public void setClientThreadList(List<ClientThread> clientThreadList) {
        this.clientThreadList = clientThreadList;
    }

    /**
     * Gets server socket.
     *
     * @return the server socket
     */
    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    /**
     * Sets server socket.
     *
     * @param serverSocket the server socket
     */
    public void setServerSocket(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    /**
     * Gets host.
     *
     * @return the host
     */
    public String getHost() {
        return host;
    }

    /**
     * Gets port number.
     *
     * @return the port number
     */
    public int getPortNumber() {
        return portNumber;
    }

    /**
     * Start server.
     */
    public void startServer() {
        try {
            serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress(this.host, this.portNumber));
            startAcceptingClients();
        } catch (IOException e) {
            System.err.println("Could not listen on port: " + portNumber);
            e.printStackTrace();
            System.exit(1);
        }
    }
}
