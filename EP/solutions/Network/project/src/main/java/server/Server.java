package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by schelde on 07/06/17.
 */
public class Server {
    private String host = "localhost";
    private int port = 8080;
    private ServerSocket serverSocket = null;
    private List<Socket> socketList = new ArrayList<Socket>();

    public Server() {
    }

    public Server(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void startServer() throws IOException {
        this.serverSocket = new ServerSocket(this.port);

        Socket socket = null;
        while (true) {
            socket = this.serverSocket.accept();
            System.out.println("...we have received a connection on socket: " + socket.toString());
            socketList.add(socket);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF8"));
            while (true) {
                while (bufferedReader.ready()) {
                    String textLine = bufferedReader.readLine();
                    System.out.println("...we received this text from a client: " + textLine);

                }
            }
        }
    }
}