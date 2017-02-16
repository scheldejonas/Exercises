package server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by scheldejonas on 16/02/2017.
 */
public class ChatServer {

    private static ServerSocket serverSocket = null;
    private static int portNumber = 8081;
    protected static List<ClientThread> clientThreadList = null;

    /**
     * Starting Chat server, in a one thread to control all clients.
     *
     * @param args
     */
    public static void main(String[] args) {

        try {

            // Creating new instance of the server
            serverSocket = new ServerSocket();

            // Binding server to ip and port
            serverSocket.bind(new InetSocketAddress("localhost", portNumber));

            acceptClients();

        } catch (IOException e) {
            System.err.println("Could not listen on port: " + portNumber);
            e.printStackTrace();
            System.exit(1);
        }

    }

    private static void acceptClients() {

        clientThreadList = new ArrayList<>();

        while (true) {

            try {

                System.out.printf("Starting to wait for now clients connecting...\n");
                Socket socket = serverSocket.accept();
                System.out.printf("Client connected on return port: %s \n", socket.getLocalPort());

                ClientThread clientThread = new ClientThread(socket);

                Thread thread = new Thread(clientThread);

                thread.start();

                clientThreadList.add(clientThread);
                System.out.printf("Client added to the list with a new size: %s\n",clientThreadList.size());

            } catch (IOException e) {
                System.err.println("Accept failen on: " + portNumber);
                e.printStackTrace();
            }

        }

    }

}
