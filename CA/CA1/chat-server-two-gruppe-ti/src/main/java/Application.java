import controller.ChatServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;

/**
 * Created by scheldejonas on 19/02/2017.
 */
public class Application {
    public static void main(String[] args) {
        ChatServer chatServer = new ChatServer("localhost",8081);
        chatServer.startServer();
    }
}
