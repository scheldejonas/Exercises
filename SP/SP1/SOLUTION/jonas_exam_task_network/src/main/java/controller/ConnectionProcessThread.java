package controller;

import model.AbstractStadiumProtocol;
import model.Database;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by scheldejonas on 06/02/17.
 */
public class ConnectionProcessThread extends Thread {

    private ReentrantLock reentrantLock = new ReentrantLock();
    private Object unit = null;
    private BufferedReader bufferedReader = null;
    private StadiumProtocol stadiumProtocol = new StadiumProtocol();
    private Database database = Database.getSingleton();

    public ConnectionProcessThread() {
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (bufferedReader.ready()) {
                    String recievedLine = bufferedReader.readLine();
                    System.out.println("...Received this text from the client: " + recievedLine);
                    Enum command = stadiumProtocol.getMessageReturnCommand(recievedLine);
                    System.out.println("...Received this command from unit: " + command.name());
                    if (command.name().equals("ADD_PERSON")) {
                        long totalPeople = database.addOnePerson();
                        System.out.println("...Added one person to database and it has now: " + totalPeople + " total people.");
                    }
                }
            } catch (IOException e) {
                System.out.println("...Error occured when trying to read line from client in ConnectionProcessThread");
                e.printStackTrace();
            }
        }
    }

    private void defineAndSetTheConnecterUnit(Socket socket) {
        try {
            reentrantLock.lock();
            String connectionString = null;
            try {
                InputStream inputStream = socket.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                bufferedReader = new BufferedReader(inputStreamReader);
                connectionString = bufferedReader.readLine();
                System.out.println("...Server read line from initial posting: " + connectionString);
                unit = stadiumProtocol.getUnitObject(connectionString);
                System.out.println("...Server recieved connection from a " + unit.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } finally {
            reentrantLock.unlock();
        }
    }

    public void readyProcesWithSocket(Socket connectionSocket) {
        defineAndSetTheConnecterUnit(connectionSocket);
    }

    class StadiumProtocol extends AbstractStadiumProtocol {
        public StadiumProtocol() {
            System.out.println("...New StadiumProtocol created.");
        }
    }
}
