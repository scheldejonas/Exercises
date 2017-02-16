package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by scheldejonas on 16/02/2017.
 */
public class ClientThread extends ChatServer implements Runnable {

    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    public ClientThread(Socket socket) {

        this.socket = socket;

    }

    @Override
    public void run() {

        try {

            out = new PrintWriter(socket.getOutputStream(),true);

            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            while (!socket.isClosed()) {
                System.out.println("TEST - Socket is not closed");

                String input = in.readLine();
                System.out.println("TEST - input line on server is: " +  input);

                if (input != null) {

                    for (ClientThread clientThread : clientThreadList) {

                        clientThread.getOut().write(input);

                    }
                    System.out.printf("TEST - Client Thread List size is: %s\n",clientThreadList.size());

                }

            }

        } catch (IOException e) {
            System.err.println("Input or output error, when relay messages from client on server");
            e.printStackTrace();
        }

    }

    public PrintWriter getOut() {

        return out;

    }
}
