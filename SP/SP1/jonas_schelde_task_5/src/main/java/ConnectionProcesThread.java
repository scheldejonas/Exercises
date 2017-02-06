import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by scheldejonas on 06/02/17.
 */
public class ConnectionProcesThread extends Thread {
    @Override
    public void run() {
        super.run();
    }

    /**
     * This method destinguic if the connector is a Turnstile or a monitor.
     * @param socket
     * @return unit type
     */
    private String defineTheConnecterUnit(Socket socket) {
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
            return "TURNSTILE";
        }
        if (conectionString.contains("UNIT_MONITOR")) {
            return "MONTIRO";
        }
    }
}
