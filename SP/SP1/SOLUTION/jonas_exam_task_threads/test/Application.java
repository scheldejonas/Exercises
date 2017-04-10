import java.util.Observable;
import java.util.Observer;

/**
 * Created by scheldejonas on 10/04/2017.
 */
public class Application {
    public static void main(String[] args) {
        System.out.println("Enter text: ");
        Event event = new Event();

        event.addObserver(
            new Observer() {
                @Override
                public void update(Observable o, Object arg) {
                    System.out.println("Received response: " + arg);
                }
            }
        );

        new Thread(event).start();
    }
}
