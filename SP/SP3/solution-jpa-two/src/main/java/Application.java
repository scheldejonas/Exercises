import config.DataConfig;
import domain.Person;
import service.PersonService;
import service.PersonServiceImpl;

import javax.persistence.EntityManager;

/**
 * Created by scheldejonas on 22/02/2017.
 */
public class Application {
    public static void main(String[] args) {
        try {
            // SP3 - JPA TWO - Part Two
            PersonService personService = PersonServiceImpl.getSingleton();
            personService.createPerson(new Person());
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            exception.printStackTrace();
        } finally {
            // No need to let it stand and run
            System.exit(1);
        }
    }
}
