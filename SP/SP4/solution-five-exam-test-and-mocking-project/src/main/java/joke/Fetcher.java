package joke;

import static com.jayway.restassured.RestAssured.given;

public class Fetcher {
  private EmailService emailService;
  private FetcherService fetcherService;

  public Fetcher() {
    this.emailService = EmailService.getSingleton();
    this.fetcherService = FetcherService.getSingleton();
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
  
  //DO NOT TEST THIS METHOD: It's just meant as a quick manual test, to see the code in action
  public static void main(String[] args) throws CommunicationException {
    Fetcher f = new Fetcher();
    System.out.println(f.fetchJoke());
  }
}
