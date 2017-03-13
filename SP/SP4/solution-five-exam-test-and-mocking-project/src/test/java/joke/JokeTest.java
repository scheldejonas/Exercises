package joke;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class JokeTest {

    @Mock
    FetcherService fetcherService;

    @Mock
    EmailService emailService;

    @InjectMocks
    Fetcher fetcher = Fetcher.getSingleton();

    @Test
    public void fetchJoke_ShouldReturnOne() throws CommunicationException {
        when(fetcherService.fetchJokeFromICNB()).thenReturn(new Joke("This is the joke", "https://foundhere.com"));
        assertThat(fetcher.fetchJoke(),instanceOf(Joke.class));
        verify(fetcherService).fetchJokeFromICNB();
    }

    @Test(expected = CommunicationException.class)
    public void fetchJoke_ShouldSendEmail() throws CommunicationException {
        when(fetcherService.fetchJokeFromICNB()).thenThrow(new CommunicationException());
        fetcher.fetchJoke();
        verify(emailService).sendEmailToAdmin("Test Email");
    }
}M