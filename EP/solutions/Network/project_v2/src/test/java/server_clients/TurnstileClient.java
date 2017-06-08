package server_clients;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Created by scheldejonas on 10/04/2017.
 */
public class TurnstileClient {

    public static void main(String[] args) throws IOException {
        TurnstileClient turnstileClient = new TurnstileClient("localhost",8080);
        turnstileClient.open();
        turnstileClient.sendMessage("UNIT_TURNSTILE");
        for (int i = 0; i < 10; i++) {
            turnstileClient.sendMessage("AP");
        }
    }

    private final String host;
    private final int port;
    private Socket clientSocket;

    public TurnstileClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void open() throws IOException {
        clientSocket = new Socket();
        clientSocket.connect(new InetSocketAddress(host, port));
        System.out.println("Client connected to server on port " + port);
    }

    public void sendMessage(String message) throws IOException {
        System.out.println("...Writing a message: " + message + " to server.");
        OutputStream output = clientSocket.getOutputStream();
        PrintWriter writer = new PrintWriter(output);
        writer.println(message);
        writer.flush();
        System.out.println("...Done writing message to server.");
    }
}
