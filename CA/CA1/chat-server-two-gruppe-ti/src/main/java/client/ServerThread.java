package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by scheldejonas on 16/02/2017.
 */
public class ServerThread extends Client implements Runnable {

    private Socket socket;
    private String name;
    private PrintWriter out;
    private BufferedReader serverIn;
    private BufferedReader userIn;

    public ServerThread(Socket socket, String name) {

        this.socket = socket;

        this.name = name;

    }

    @Override
    public void run() {
        System.out.println("Server is connected to you");

        try {

            out = new PrintWriter(socket.getOutputStream(),true);

            serverIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            userIn = new BufferedReader(new InputStreamReader(System.in));

            while (!socket.isClosed()) {
                try {
                    Thread.sleep(1000);
                    System.out.printf("TEST, userIn.ready: %s serverIn.ready: %s \n",serverIn.ready(), userIn.ready() );
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (serverIn.ready()) {

                    String input = serverIn.readLine();

                    if (input != null) {

                        System.out.println(input);

                    }

                }

                if (userIn.ready()) {

                    clientForm.appendTextAreaWithMessage(name,userIn.readLine());

                }

            }
            System.out.printf("Socket became closed \n");

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

    protected Socket getSocket() {
        return socket;
    }
}
