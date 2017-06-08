package server_clients;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Created by scheldejonas on 11/04/2017.
 */
public class MonitorClient {

    public static void main(String[] args) throws IOException {
        MonitorClient monitorClient = new MonitorClient("localhost",8080);
        monitorClient.open();
        monitorClient.sendMessage("UNIT_MONITOR");
        for (int i = 0; i < 10; i++) {
            monitorClient.sendMessage("EAUWC");
        }
    }

    private final String host;
    private final int port;
    private Socket clientSocket;
    protected BufferedReader reader;

    public MonitorClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void open() throws IOException {
        clientSocket = new Socket();
        clientSocket.connect(new InetSocketAddress(host, port));
        InputStream input = clientSocket.getInputStream();
        reader = new BufferedReader(new InputStreamReader(input));
        new Thread(new ReceiveRunner()).start();
        System.out.println("Monitor Client connected to server on port " + port);
    }

    public void sendMessage(String message) throws IOException {
        System.out.println("...Writing a message: " + message + " to server.");
        OutputStream output = clientSocket.getOutputStream();
        PrintWriter writer = new PrintWriter(output);
        writer.println(message);
        writer.flush();
        System.out.println("...Done writing message to server.");
    }

    class ReceiveRunner implements Runnable {
        @Override
        public void run() {
            try {
                String fromServer;
                while ( (fromServer = reader.readLine()) != null ) {
                    System.out.println("...Recieved from Server: " + fromServer);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
