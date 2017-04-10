import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Created by scheldejonas on 03/02/17.
 */
public class TimeServer {

    /**
     * Creating an instance and starting our respond server for a time now request
     *
     * @param args
     */
    public static void main(String[] args) {
        TimeServer timeServer = new TimeServer("localhost",8080);
        timeServer.startServer();
    }

    private final String host;
    private final int port;

    public TimeServer(String host, int port) {
        this.host = host;
        this.port = port;
    }

    /**
     * Basicly starts the server on the set host and port, during instance creation of TimeServer
     *
     * All exceptions is handled inside this for more control with this server.
     *
     * @Return nothing
     */
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

                LocalDateTime localDateTime = LocalDateTime.now();

                System.out.printf("Writing LocalDate to client now as this: %s \n", localDateTime);

                OutputStream outputStream = socket.getOutputStream();
                PrintStream printStream = new PrintStream(outputStream);

                printStream.printf("This is today's date and time: %s",localDateTime);

                System.out.printf("The remote Socket Adress: %s \n", socket.getRemoteSocketAddress() );

                socket.close();

                System.out.printf("Done writing LocalDate to client...and waiting \n");

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
