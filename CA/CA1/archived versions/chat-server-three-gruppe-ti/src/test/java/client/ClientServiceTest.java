package client;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

/**
 * Created by scheldejonas on 16/02/2017.
 */
public class ClientServiceTest {

    @Test
    public void RunServiceShouldPrintMessageToSystemOut() throws InterruptedException, IOException {
        Client client = new Client("localhost",8081);
        Thread thread = new Thread(client);
        thread.start();

        Thread.sleep(1000);

        // Set the username to Service
        client.setUsername( "Jonas" );

        // Wait for the service to connect to server
        Thread.sleep(5*1000);

        ProcessBuilder builder = new ProcessBuilder("java", "ChatService");
        builder.directory(new File("/"));
        Process process = builder.start();

        InputStream stdout = process.getInputStream();

        Scanner scanner = new Scanner(stdout);
        while (scanner.hasNextLine()) {
            System.out.println(scanner.nextLine());
        }
    }
}
