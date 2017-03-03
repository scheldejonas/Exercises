package joke;

public class Fetcher {
  private static final Fetcher singleton = new Fetcher();
  private EmailService emailService;
  private FetcherService fetcherService;

  private Fetcher() {
      this.emailService = EmailService.getSingleton();
      this.fetcherService = FetcherService.getSingleton();
  }

  public static Fetcher getSingleton() {
      return singleton;
  }

  /**
   * Fetch a Chuck Norris Joke from http://api.icndb.com
   * @return A new Chuck Norris Joke
   * @throws CommunicationException In case of an error with the external call. 
   *                                This will also send an email to admin
   */
  public Joke fetchJoke() throws CommunicationException {
    try {
      return fetcherService.fetchJokeFromICNB();
    } catch (CommunicationException exception) {
       emailService.sendEmailToAdmin("There is a problem with the server: http://api.icndb.com/jokes/random");
       throw exception; //Rethrow to signal error to the caller
    }
  }
}
