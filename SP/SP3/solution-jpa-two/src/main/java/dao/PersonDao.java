package dao;

import domain.Person;

/**
 * Created by scheldejonas on 24/02/17.
 */
public interface PersonDao {

    void createPerson(Person person);

    Person findPerson(Long personId);

    void update(Person person);

    void delete(Person person);
}
