import client.Client;

/**
 * Created by schelde on 07/06/17.
 */
public class ApplicationClient {
    public static void main(String[] args) {
        Client client = new Client();
        client.connectToServer();
    }
}
