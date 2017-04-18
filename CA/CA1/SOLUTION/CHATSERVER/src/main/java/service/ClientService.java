package service;

import dao.Clients;
import domain.ClientConnection;
import domain.Message;

import java.net.Socket;

import static service.ChatProtocolService.ServerCommand.*;

/**
 * Created by scheldejonas on 17/04/2017.
 */
public class ClientService {
    private static final ClientService singleton = new ClientService();

    private Clients clients = Clients.getSingleton();
    private ChatProtocolServiceImpl chatProtocolService = ChatProtocolServiceImpl.getSingleton();

    private ClientService() {
    }

    public static ClientService getSingleton() {
        return singleton;
    }

    public void addNewClient(Socket socket) {
        try {
            clients.getReentrantLock().lock();
            ClientConnection newClient = new ClientConnection();
            newClient.mountConnection(socket);
            System.out.println("...ClientService is starting the connection in a seperate thread for sending and recieving messages.");
            newClient.setLock(clients.getReentrantLock());
            System.out.println("...ClientService has successfully mounted the lock to the new client.");
            clients.getNewClients().add(newClient);
            System.out.println("...ClientService has added a new client to the database, with reference: " + newClient.hashCode());
            clients.getConnectionPool().execute(newClient);
            System.out.println("...ClientService succesfully started the Client Connection for reference: " + newClient.hashCode());
            clients.getReentrantLock().unlock();
        } catch (Throwable error) {
            clients.getReentrantLock().unlock();
            System.err.println("...There was a problem while trying to add new client. From socket: " + socket.toString());
            error.printStackTrace();
        }
    }

    public void shutdownClientConnection(ClientConnection clientConnection) {
        try {
            clients.getReentrantLock().lock();
            System.out.println("...Active clients has the size of: " + clients.getActiveClients().size());
            clients.getConnectionPool().remove(clientConnection);
            boolean isInActiceClientList = clients.getActiveClients().remove(clientConnection);
            boolean isInNewClientList = clients.getNewClients().remove(clientConnection);
            if (isInActiceClientList) {
                for (ClientConnection clientConnectionLooper : clients.getActiveClients()
                     ) {
                    clientConnectionLooper.sendText("REMOVE#" + clientConnection.getUser().getName());
                }
            } else if (!isInActiceClientList) {
                System.err.println("...While trying to remove a client the the active client list, it wasn't able to be found, with reference: " + clientConnection.hashCode() + ", and username: " + clientConnection.getUser().getName());
            } else if (isInNewClientList) {
                System.err.println("...While trying to remove a disconnected client the new client list, wasn't not to found in, client: " + clientConnection.toString());
            } else if (!isInNewClientList) {
                System.err.println("...While trying to remove a disconnected client, it was not existing in new client list");
            } else {
                System.out.println("...Removed client with reference: " + clientConnection.hashCode() + " succesfully.");
            }
            System.out.println("...Active clients has the size of: " + clients.getActiveClients().size());
            clients.getDisconnectedClients().add(clientConnection);
            System.out.println("...Disconnected clients has the size of: " + clients.getDisconnectedClients().size());
            clients.getReentrantLock().unlock();
        } catch (Throwable error) {
            clients.getReentrantLock().unlock();
            System.err.println("...There was a problem while trying to handle shutdown from disconnected client. From client: " + clientConnection.toString());
            error.printStackTrace();
        }
    }

    public void newTextFromClient(String newReceivedText, ClientConnection clientConnection) {
        try {
            clients.getReentrantLock().lock();
            ChatProtocolService.ServerCommand serverCommand = chatProtocolService.analyseServerLine(newReceivedText);
            String[] newTextAsArray = newReceivedText.split("#");
            if (serverCommand.id == SEND_MESSAGE_TO_FORUM.id && clientConnection.isActive()) {
                System.out.println("...Client have sent a message to all users.");
                Message newMessage = new Message(clientConnection.getUser().getName(), "ALL", newTextAsArray[2]);
                clientConnection.getUser().getSentMessages().add(newMessage);
                for (ClientConnection clientConnectionLooper : clients.getActiveClients()
                        ) {
                    System.out.println("...Sending message to all: " + newMessage.toString());
                    clientConnectionLooper.sendText("MSG#" + newMessage.getFromUser() + "#" + newMessage.getMessage());
                    clientConnectionLooper.getUser().getSeenMessages().add(newMessage);
                }
                System.out.println("...Done sending message to all from reference: " + clientConnection.hashCode());
            }
            if (serverCommand.id == SEND_MESSAGE_TO_ONE_USER.id && clientConnection.isActive()) {
                System.out.println("...Client wants to send a specific user a message.");
                Message newMessage = new Message(clientConnection.getUser().getName(), newTextAsArray[1], newTextAsArray[2]);
                clientConnection.sendText("MSG#" + newMessage.getFromUser() + "#" + newMessage.getMessage() + " ");
                clientConnection.getUser().getSentMessages().add(newMessage);
                for (ClientConnection clientConnectionLooper : clients.getActiveClients()
                        ) {
                    if (clientConnectionLooper.getUser().getName().equals(newTextAsArray[1])) {
                        System.out.println("...Sending private message: " + newMessage.toString());
                        clientConnectionLooper.sendText("MSG#" + newMessage.getFromUser() + "#" + newMessage.getMessage() + " ");
                        clientConnectionLooper.getUser().getSeenMessages().add(newMessage);
                    }
                }

                System.out.println("...Done sending private message from reference: " + clientConnection.hashCode());
            }
            if (serverCommand.id == LOGIN_USER_TO_FORUM.id && clientConnection.isActive()) {
                System.err.println("...Active client wants to login in again. This is an mistake from the client side.");
                clientConnection.sendText("ERROR#It is not possible to login again after you already went active");
            }
            if (serverCommand.id == LOGIN_USER_TO_FORUM.id && !clientConnection.isActive()) {
                System.out.println("...New Client wants to login in to forum");
                boolean isAlreadyExistingActiveUsername = false;
                for (ClientConnection clientConnectionLooper : clients.getActiveClients()
                        ) {
                    if (clientConnectionLooper.getUser().getName().equals(newTextAsArray[1])) {
                        isAlreadyExistingActiveUsername = true;
                    }
                }
                if (isAlreadyExistingActiveUsername) {
                    clientConnection.sendText("FAIL");
                    boolean isFindable = clients.getActiveClients().remove(clientConnection);
                    if (!isFindable) {
                        System.err.println("...When trying to find Client Connection, because username is not available, during new login. It was not possible.");
                    }
                } else {
                    clientConnection.getUser().setName(newTextAsArray[1]);
                    clientConnection.setActive(true);
                    boolean isFoundableInNewClients = clients.getNewClients().remove(clientConnection);
                    if (!isFoundableInNewClients) {
                        System.err.println("...While trying to move a new client, after approved username, it was not able to be found. Client: " + clientConnection.toString());
                    }
                    String allUsers = "";
                    clients.getActiveClients().add(clientConnection);
                    for (ClientConnection clientConnectionLooper : clients.getActiveClients()
                            ) {
                        if (!clientConnectionLooper.equals(clientConnection)) {
                            clientConnectionLooper.sendText("UPDATE#" + newTextAsArray[1]);
                        }
                        allUsers += "#" + clientConnectionLooper.getUser().getName();
                    }
                    clientConnection.sendText("OK" + allUsers);
                    System.out.println("...Server has successfully moved a new Client to active clients. Client: " + clientConnection.toString());
                }
            }
            if (serverCommand.id == ERROR.id) {
                System.err.println("...New or Active client tried to sent a text, not fitting to the protocol.");
                clientConnection.sendText("ERROR#This is to tell you, you have fucked up in your client code, and haven't implemented from the chat protocol correctly.");
            }
            clients.getReentrantLock().unlock();
        } catch (Throwable error) {
            clients.getReentrantLock().unlock();
            System.err.println("...There was a problem while trying to handle new text from a client. From client: " + clientConnection.toString());
            error.printStackTrace();
        }
    }
}
