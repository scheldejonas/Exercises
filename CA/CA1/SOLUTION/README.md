# CA1 | Solution | Jonas Schelde

[Here](https://github.com/CphBusCosSem3/Exercises/tree/master/CA/CA1) is the teachers assignment github repo

## Chat protocol

[Here](https://github.com/scheldejonas/Exercises/blob/master/CA/CA1/protocol.md) is the layed out school class decided protocol.

#### Design choices

- The protocol, is built as an abstract class for having the main intepretations of commands completly the same on both clients as server.

- The abstract protocol, looks like this then:

- ```java
  package service;

  /**
   * Created by scheldejonas on 17/04/2017.
   */
  public abstract class ChatProtocolService {

      public abstract ServerCommand analyseServerLine(String newLine);

      public abstract ClientCommand analyseClientLine(String newLine);

      enum ServerCommand {
          ERROR(0,"[ERROR#[ERRORMESSAGE]")
          ,SEND_MESSAGE_TO_FORUM(1,"MSG#[FROMUSERNAME]#[MESSAGE]")
          ,SEND_MESSAGE_TO_ONE_USER(2,"MSG#[FROMUSERNAME]#[MESSAGE] ")
          ,LOGIN_USER_TO_FORUM(3,"LOGIN#[NEWUSERNAME]")
          ;

          public final int id;
          public final String code;

          ServerCommand(int id, String code) {
              this.id = id;
              this.code = code;
          }
      }

      enum ClientCommand {
          ERROR(0,"[ERROR#[ERRORMESSAGE]")
          ,SEND_MESSAGE_TO_ALL(1,"MSG#[ALL]#[MESSAGE]")
          ,SEND_MESSAGE_TO_SPECIFIC_USER(2,"MSG#[TOUSERNAME]#[MESSAGE]")
          ,ADD_MESSAGE(3,"MSG#[FROMUSERNAME]#[MESSAGE]")
          ,ADD_PRIVATE_MESSAGE(4,"MSG#[FROMUSERNAME]#[MESSAGE] ")
          ,OK_ON_LOGIN_AND_OPEN_LIST_OF_ACTIVE_USERS(5,"OK#[ACTIVEUSERNAME1]#[ACTIVEUSERNAME2]")
          ,UPDATE_LIST_OF_NEW_ACTIVE_USER(6,"UPDATE#[NEWUSERNAME]")
          ,INFORM_ATTEMPTED_USERNAME_ON_LOGIN_WAS_ALREADY_USED(7,"FAIL")
          ,REMOVE_USER_FROM_ACTIVE_USERS(8,"REMOVE#[USERNAME]")
          ;

          public final int id;
          public final String code;

          ClientCommand(int id, String code) {
              this.id = id;
              this.code = code;
          }
      }
  }
  ```

- Mainly it is used as a intepreter or translater of the text being sent between clients and servers.

- From what happens after, is up to the controllers and service objects, of the data being passed around, to understand and distribute.

  #### Server interpretation of the protocol

- ```java
  package service;

  /**
   * Created by scheldejonas on 17/04/2017.
   */
  public class ChatProtocolServiceImpl extends ChatProtocolService {

      private static final ChatProtocolServiceImpl singleton = new ChatProtocolServiceImpl();

      private ChatProtocolServiceImpl() {
      }

      public static ChatProtocolServiceImpl getSingleton() {
          return singleton;
      }

      @Override
      public ServerCommand analyseServerLine(String newLine) {
          String[] newTextLineInArray = newLine.split("#");
          System.out.println("...Starting to analyse new received textline from client on server: " + newLine);
          if (newTextLineInArray[0].equals("LOGIN")) {
              return ServerCommand.LOGIN_USER_TO_FORUM;
          }
          if (newTextLineInArray[0].equals("MSG")) {
              if (newTextLineInArray[1].equals("ALL")) {
                  return ServerCommand.SEND_MESSAGE_TO_FORUM;
              }
              if (!newTextLineInArray[1].equals("ALL")) {
                  return ServerCommand.SEND_MESSAGE_TO_ONE_USER;
              }
          }
          return null;
      }

      @Override
      public ClientCommand analyseClientLine(String newLine) {
          return null;
      }

  }
  ```

- From this, the server now, clear have the correct command to follow, upon it's clients.

  #### Client interpretation of the protocol

- ```

  ```

- ?

## Chat Server

#### Design choices

- We have written the protocol in an interface firstly, to make sure what kinds of options you would have in this protocol.
- Also to have a way to overview, what is possible in terms of method, on the client, as to the service.
- Then of course there is just different kinds of implementations on the server and the client.
- The design is created with top-down approach, in the way; There is a server setup and a client setup.
- To this top-down structure design, each setup, both server and client, is seperated with a kind of industri standard package pattern named like this:
  - Web (here usually the .jsp files or other html files is placed to be used before responding to the user)
  - Controller (directs the requests and controls the threads overview)
  - Service (services the entities or the handles the data through the protocol for example, to be directed back to the controller or directly to be persisted for longer saving in the db, through dao)
  - Dao (this controls data acces to the database, keeping all data sent and recieved from database persistent)
  - Database (the relational table base with data saved)
- Here is our Data modal, as initiated database inside the java chatserver:

![alt tag](images/chat_server_domain_diagram.png)



- The folder structure of the server application, is then looking like this.
- ![alt tag](images/folder_structure_of_chat_server.png)
- ```root/ChatServer```  is containing the main method, that starts the ChatServerSocket as an thread.
- ```controller/``` folder is to have the ingoing new connection handeling to the server. ChatServerSocket, can be initiated and binded to a port. When this is done, a while loop with a reentrantlock is placed in the run method, from the implementation for Runnable.
- ```service/``` folder is then where ```ChatServerSocket``` sends all the new connection down, and get's them mounted to a ClientConnection object, with the savings and start of a runnable while loop for receiving text lines, from the open socket connection. This is for singleton initiated classes
- ```dao/``` folder is then to have all the initiated Messages, Users, Clients as ClientConnections saved, either as active or disconnected clients, in the ```Clients``` class.
- ```domain/``` is the entity's of the data model layed out. And initiated through the Clients "database" in ```dao```  package.

#### Practical chat server

- The ChatServerSocket

- ```java
  package controller;

  import service.ClientService;

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
      private ClientService clientService = ClientService.getSingleton();

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
                  clientService.addNewClient( (Socket) socket );
                  lock.unlock();
              } catch (IOException exception) {
                  lock.unlock();
                  System.out.println("...Accept failed on: " + portNumber);
                  exception.printStackTrace();
              }
          }
      }
  }

  ```

- The ClientService

- ```java
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
              if (!isInActiceClientList) {
                  System.err.println("...While trying to remove a client the the active client list, it wasn't able to be found, with reference: " + clientConnection.hashCode() + ", and username: " + clientConnection.getUser().getName());
              } else if (!isInNewClientList) {
                  System.err.println("...While trying to remove a disconnected client the new client list, wasn't not to found in, client: " + clientConnection.toString());
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
                          clientConnectionLooper.sendText("UPDATE#" + newTextAsArray[1]);
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
  ```

- ​

## Web server

And to end this project, the demands were to let this client and server be available on the web through a web server with https.

it is therefore online and working on this link:

- [online web server with chat client and server for download](https://77.66.48.34)

---
