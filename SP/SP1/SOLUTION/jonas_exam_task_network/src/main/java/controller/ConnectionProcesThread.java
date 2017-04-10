package controller;

import domain.Monitor;
import domain.Turnstile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by scheldejonas on 06/02/17.
 */
public class ConnectionProcesThread extends Thread {

    private ReentrantLock reentrantLock = new ReentrantLock();
    private Turnstile turnstile;
    private Monitor monitor;

    public ConnectionProcesThread() {
    }

    @Override
    public void run() {
        // TODO: make the instruks for this thread to run and save the info of the Turnstile to the database
    }

    /**
     * This method destinguic if the connector is a domain.Turnstile or a monitor.
     * @param socket
     * @return unit type
     */
    private void defineAndSetTheConnecterUnit(Socket socket) {
        try {
            reentrantLock.lock();
            String conectionString = null;
            try {
                InputStream inputStream = socket.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                conectionString = bufferedReader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (conectionString.contains("UNIT_TURNSTILE")) {
                this.turnstile = new Turnstile();
            }
            if (conectionString.contains("UNIT_MONITOR")) {
                this.monitor = new Monitor();
            }
        } finally {
            reentrantLock.unlock();
        }
    }

    public void readyProcesWithSocket(Socket connectionSocket) {

        defineAndSetTheConnecterUnit(connectionSocket);

    }

    public String getUnitType() {
        if (this.turnstile != null) {
            return "UNIT_TURNSTILE";
        }
        if (this.monitor != null) {
            return "UNIT_MONITOR";
        }
        return null;
    }
}
