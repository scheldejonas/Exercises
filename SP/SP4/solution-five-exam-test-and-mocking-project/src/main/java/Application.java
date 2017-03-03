import joke.CommunicationException;
import joke.Fetcher;
import joke.Joke;

/**
 * Created by scheldejonas on 03/03/17.
 */
public class Application {
    public static void main(String[] args) {
        Fetcher fetcher = Fetcher.getSingleton();
        Joke joke = null;
        try {
            joke = fetcher.fetchJoke();
        } catch (CommunicationException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        System.out.println(joke);
    }
}
