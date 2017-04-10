package threadsObserver;

import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

import randomperson.RandomUser;
import randomperson.RandomUserGenerator;

public class RandomUserControl {

    public RandomUser fetchRandomUser() {
        RandomUser user = null;
        try {
            user = RandomUserGenerator.getRandomUser();
        } catch (InterruptedException ex) {
            Logger.getLogger(RandomUserControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }
}
