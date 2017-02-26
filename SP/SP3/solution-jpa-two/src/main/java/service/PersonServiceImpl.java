package service;

import dao.*;
import domain.Person;

/**
 * Created by scheldejonas on 24/02/17.
 */
public class PersonServiceImpl implements PersonService {

    private static PersonService singleton = null;
    private PersonDao personDao = null;

    private PersonServiceImpl() {
        this.personDao = PersonDaoImpl.getSingleton();
    }

    public static PersonService getSingleton() {
        if (singleton == null) {
            singleton = new PersonServiceImpl();
        }
        return singleton;
    }

    @Override
    public void createPerson(Person person) {
        personDao.createPerson(person);
    }
}
