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

    @InjectMocks
    Fetcher fetcher = Fetcher.getSingleton();

    @Test
    public void fetchJokeShouldReturnOne() throws CommunicationException {
        when(fetcher.fetchJoke()).thenReturn(new Joke("This is the joke", "https://foundhere.com"));
        assertThat(fetcher.fetchJoke(),instanceOf(Joke.class));
        verify(fetcherService).fetchJokeFromICNB();
    }

    @Test(expected = CommunicationException.class)
    public void fetchJokeShouldThrowException() {

    }
}