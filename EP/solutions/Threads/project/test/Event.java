import java.util.Observable;
import java.util.Scanner;

/**
 * Created by scheldejonas on 10/04/2017.
 */
public class Event extends Observable implements Runnable {
    @Override
    public void run() {
        while (true) {
            String response = new Scanner(System.in).next();
            this.setChanged();
            notifyObservers(response);
        }
    }
}
