import controller.ChatServerSocket;

/**
 * Created by scheldejonas on 17/04/2017.
 */
public class ChatServer {
    public static void main(String[] args) {
        ChatServerSocket chatServerSocket = new ChatServerSocket("localhost", 8084);
        chatServerSocket.startRunningTheServer();
        Thread serverThread = new Thread(chatServerSocket);
        serverThread.start();
    }
}
