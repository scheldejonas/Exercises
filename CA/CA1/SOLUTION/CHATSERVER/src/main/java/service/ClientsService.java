package service;

import com.sun.security.ntlm.Server;
import dao.Clients;
import domain.ClientConnection;

import java.net.Socket;

import static service.ChatProtocolService.ServerCommand.*;

/**
 * Created by scheldejonas on 17/04/2017.
 */
public class ClientsService {
    private static final ClientsService singleton = new ClientsService();

    private Clients clients = Clients.getSingleton();
    private ChatProtocolServiceImpl chatProtocolService = ChatProtocolServiceImpl.getSingleton();

    private ClientsService() {
    }

    public static ClientsService getSingleton() {
        return singleton;
    }

    public void addNewClient(Socket socket) {
        ClientConnection newClient = new ClientConnection();
        newClient.mountConnection(socket);
        System.out.println("...ClientsService is starting the connection in a seperate thread for sending and recieving messages.");
        newClient.setLock(clients.getReentrantLock());
        System.out.println("...ClientsService has successfully mounted the lock to the new client.");
        clients.getActiveClients().add(newClient);
        System.out.println("...ClientsService has added a new client to the database, with reference: " + newClient.hashCode());
        clients.getConnectionPool().execute(newClient);
        System.out.println("...ClientsService succesfully started the Client Connection for reference: " + newClient.hashCode());
    }

    public void shutdownClientConnection(ClientConnection clientConnection) {
        System.out.println("...Active clients has the size of: " + clients.getActiveClients().size());
        clients.getConnectionPool().remove(clientConnection);
        clients.getActiveClients().remove(clientConnection);
        System.out.println("...Active clients has the size of: " + clients.getActiveClients().size());
        System.out.println("...Removed client with reference: " + clientConnection.hashCode() + " succesfully.");

    }

    public void newTextFromClient(String newReceivedText, ClientConnection clientConnection) {
        ChatProtocolService.ServerCommand serverCommand = chatProtocolService.analyseServerLine(newReceivedText);
        String[] newTextAsArray = newReceivedText.split("#");
        if (serverCommand.getId() == SEND_MESSAGE_TO_FORUM.getId()) {
            System.out.println("...Client have sent a message to all users.");
            for (ClientConnection clientConnectionLooper : clients.getActiveClients()
                 ) {
                clientConnectionLooper.sendText("MSG#" + clientConnection.getUser().getName() + "#" + newTextAsArray[2]);
            }
        }
        if (serverCommand.getId() == SEND_MESSAGE_TO_ONE_USER.getId()) {
            System.out.println("...Client wants to send a specific user a message.");
            for (ClientConnection clientConnectionLooper : clients.getActiveClients()
                    ) {
                
            }
        }
        if (serverCommand.getId() == LOGIN_USER_TO_FORUM.getId()) {
            System.out.println("...New Client wnats to login in to forum");

        }
    }
}
