import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

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

        try {
            while ( (socket = serverSocket.accept()) != null) {
                String connectingUnitName = defineTheConnecterUnit(socket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method destinguic if the connector is a Turnstile or a monitor.
     * @param socket
     * @return
     */
    private String defineTheConnecterUnit(Socket socket) {
        String conectionString = null;
        try {
            InputStream inputStream = socket.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            conectionString = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (conectionString.contains("UNIT_TURNSTILE")) {
            return "TURNSTILE";
        }
        if (conectionString.contains("UNIT_MONITOR")) {
            return "MONTIRO";
        }
    }
}
