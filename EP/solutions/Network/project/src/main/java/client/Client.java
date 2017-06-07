package client;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by schelde on 07/06/17.
 */
public class Client {
    private String host = "localhost";
    private int port = 8080;
    private Socket socket = null;

    public Client() {
    }

    public Client(String host, int port) {
        this.host = host;
        this.port = port;
    }


    public void connectToServer() {
        
    }
}
