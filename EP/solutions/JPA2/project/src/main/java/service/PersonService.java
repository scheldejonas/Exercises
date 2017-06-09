package service;

import domain.Person;
import domain.PersonStudent;

import java.util.List;

/**
 * Created by scheldejonas on 24/02/17.
 */
public interface PersonService {

    void createPerson(Person person);

    void giveGradeToPerson(int matNr, String phd);

    List<PersonStudent> getStudentsByGrade(String phd);

    void deleteByMatNr(int matNr);
}
