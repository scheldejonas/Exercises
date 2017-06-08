package threadsObserver.synchron_gui_actions;

import randomperson.RandomUser;
import threadsObserver.RandomUserControl;
import threadsObserver.RandomUserFormOne;

import java.util.Observable;

/**
 * Created by scheldejonas on 10/04/2017.
 */
public class StartFecthingNewRandomUser_ToObservers extends Observable implements Runnable {
    private RandomUserControl randomUserControl = new RandomUserControl();

    public StartFecthingNewRandomUser_ToObservers() {
    }

    @Override
    public void run() {
        System.out.println("...synchron Random User Fetcher started.");
        RandomUser randomUser = randomUserControl.fetchRandomUser();
        setChanged();
        notifyObservers(randomUser);
        System.out.println("...synchron Random User Fetcher is finished, and has notified listeners.");
    }
}
