package server;

import java.io.*;
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

            System.out.printf("Socket is connected: %s \n",socket.isConnected() );
            while (socket.isConnected()) {

                if (in.ready()) {

                    String input = in.readLine();

                    if (input != null) {
                        System.out.println("Server recieved message from client: " +  input);

                        for (ClientThread clientThread : clientThreadList) {

                            clientThread.getOut().println(input);

                        }
                        System.out.printf("Client Thread List size is now: %s\n",clientThreadList.size());

                    }
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
