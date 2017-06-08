import controller.ConnectionProcessThread;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * Created by scheldejonas on 03/02/17.
 */
public class Server {

    public static void main(String[] args) {
        Server server = new Server("localhost", 8080);
        server.startServer();
    }

    private final String host;
    private final int port;
    private ThreadFactory threadFactory = Executors.defaultThreadFactory();
    private ExecutorService executorService = Executors.newCachedThreadPool(threadFactory);
    private List<ConnectionProcessThread> procesThreadList = new LinkedList<>();
    private ServerSocket serverSocket = null;

    public Server(String host, int port) {
        this.host = host;
        this.port = port;
    }

    private void startServer() {
        try {
            serverSocket = new ServerSocket();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            System.out.println("...Server is getting binded on " + this.host + ":" + this.port);
            serverSocket.bind(new InetSocketAddress(this.host, this.port));
        } catch (IOException e) {
            System.err.println("...Server didn't succeded getting binded on " + this.host + ":" + this.port);
            e.printStackTrace();
        }
        System.out.println("...Server listening on " + this.host + ":" + this.port);
        System.out.println("...Waiting");
        try {
            Socket socket;
            while ( (socket = serverSocket.accept()) != null) {
                System.out.println("...Handling connection");
                ConnectionProcessThread connectionProcessThread = new ConnectionProcessThread();
                connectionProcessThread.readyProcesWithSocket(socket);
                procesThreadList.add(connectionProcessThread);
                executorService.execute(connectionProcessThread);
                //socket.close();
                System.out.println("...Connection handled");
                System.out.println("...Waiting");
            }
        } catch (IOException e) {
            System.err.println("...Error while handling server connections on server");
            e.printStackTrace();
        }
    }
}
