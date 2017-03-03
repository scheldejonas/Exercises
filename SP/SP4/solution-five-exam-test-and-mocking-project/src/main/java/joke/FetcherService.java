package joke;

import static com.jayway.restassured.RestAssured.given;

/**
 * Created by scheldejonas on 03/03/17.
 */
public class FetcherService {
    private static final FetcherService singleton = new FetcherService();

    private FetcherService() {
    }

    public static FetcherService getSingleton() {
        return singleton;
    }

    /**
     * Fetch a Chuck Norris joke (not political correct jokes ;-), and returns it encapsulated as a Joke instance
     * IT uses REST assured to Fetch jokes, but don't focus on HOW it does what it do
     * @return The Joke
     * @throws CommunicationException If communication failed (i.e. the external server is not responding)
     */
    public Joke fetchJokeFromICNB() throws CommunicationException {
        try {
            String joke = given().get("http://api.icndb.com/jokes/random/1").path("value.joke");
            return new Joke(joke, "http://api.icndb.com/");
        } catch (Exception e) {
            throw new CommunicationException();
        }
    }

}
