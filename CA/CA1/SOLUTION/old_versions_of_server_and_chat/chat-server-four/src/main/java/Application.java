import controller.ChatServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;

/**
 * Created by scheldejonas on 19/02/2017.
 */
public class Application {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        ChatServer chatServer = new ChatServer("77.66.48.34",8081);
        chatServer.startServer();
    }
}