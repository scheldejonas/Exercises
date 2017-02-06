import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * Created by scheldejonas on 03/02/17.
 */
public class TurnstileServer {

    public static void main(String[] args) {
        TurnstileServer turnstileServer = new TurnstileServer("localhost", 8080);
        turnstileServer.startServer();
    }

    private final String host;
    private final int port;
    private static final ExecutorService executorservice = Executors.newCachedThreadPool();

    public TurnstileServer(String host, int port) {
        this.host = host;
        this.port = port;
    }

    private void startServer() {

        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            serverSocket.bind(new InetSocketAddress(this.host, this.port));
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.printf("Server listening on %s:%s \n", this.host, this.port);

        Socket socket;
        System.out.printf("Waiting... \n");

        ThreadFactory threadFactory = Executors.defaultThreadFactory();

        ExecutorService executorService = Executors.newFixedThreadPool(64);

        try {
            while ( (socket = serverSocket.accept()) != null) {
                System.out.printf("Handling connection... \n");

                executorService.execute();

                socket.close();

                System.out.printf("Connection handled and closed from %s... \nWaiting...", connectingUnitType);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
