package service;

import dao.*;
import domain.Grade;
import domain.Person;
import domain.PersonStudent;

import java.util.List;

/**
 * Created by scheldejonas on 24/02/17.
 */
public class PersonServiceImpl implements PersonService {

    private static final PersonService singleton = new PersonServiceImpl();
    private PersonDao personDao = PersonDaoImpl.getSingleton();
    private GradeService gradeService = GradeServiceImpl.getSingleton();

    private PersonServiceImpl() {
    }

    public static PersonService getSingleton() {
        return singleton;
    }

    @Override
    public void createPerson(Person person) {
        personDao.createPerson(person);
    }

    @Override
    public void giveGradeToPerson(int matNr, String phd) {
        PersonStudent personStudent = personDao.findPersonByMatNr(matNr);
        Grade grade = gradeService.findByName(phd);
        personStudent.setGrade(grade);
        personDao.update(personStudent);
    }

    @Override
    public List<PersonStudent> getStudentsByGrade(String gradeName) {
        return personDao.getStudentsByGrade(gradeName);
    }

    @Override
    public void deleteByMatNr(int matNr) {
        Person person = personDao.findPersonByMatNr(matNr);
        personDao.delete(person);
    }
}
