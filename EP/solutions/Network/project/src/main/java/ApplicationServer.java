import server.Server;

import java.io.IOException;

/**
 * Created by schelde on 07/06/17.
 */
public class ApplicationServer {
    public static void main(String[] args) {
        Server server = new Server();
        try {
            server.startServer();
        } catch (IOException e) {
            System.out.println("...error while trying to start server");
            e.printStackTrace();
        }

    }
}
