package client;

import sun.awt.windows.ThemeReader;

import java.io.*;
import java.util.Scanner;

/**
 * Created by scheldejonas on 16/02/2017.
 */
public class Application {
    public static void main(String[] args) throws InterruptedException, IOException {
        String host = "10.50.137.177";
        String host2 = "10.50.130.116";
        Client client = new Client(host2,8081);
        Thread thread = new Thread(client);
        thread.start();
    }
}
