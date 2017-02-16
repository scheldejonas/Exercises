package client;

import sun.awt.windows.ThemeReader;

import java.io.*;
import java.util.Scanner;

/**
 * Created by scheldejonas on 16/02/2017.
 */
public class Application {
    public static void main(String[] args) throws InterruptedException, IOException {
        Client client = new Client("localhost",8081);
        Thread thread = new Thread(client);
        thread.start();
    }
}
