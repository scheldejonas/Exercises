import config.DataConfig;
import domain.Grade;
import domain.Person;
import domain.PersonEmployee;
import domain.PersonStudent;
import service.GradeService;
import service.GradeServiceImpl;
import service.PersonService;
import service.PersonServiceImpl;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by scheldejonas on 22/02/2017.
 */
public class Application {
    public static void main(String[] args) {
        PersonService personService = PersonServiceImpl.getSingleton();
        GradeService gradeService = GradeServiceImpl.getSingleton();
        try {
            // SP3 - JPA TWO - Part Two
            personService.createPerson(new PersonStudent("Peter", "Andersen", LocalDate.now(), 22, true, 9922, LocalDate.now()));
            personService.createPerson(new PersonStudent("Søren", "Andersen", LocalDate.now(), 22, true, 9923, LocalDate.now()));
            personService.createPerson(new PersonStudent("Chris", "Andersen", LocalDate.now(), 22, true, 9924, LocalDate.now()));
            personService.createPerson(new PersonStudent("Jonas", "Andersen", LocalDate.now(), 22, true, 9925, LocalDate.now()));
            personService.createPerson(new PersonStudent("Anders", "Andersen", LocalDate.now(), 22, true, 9926, LocalDate.now()));
            personService.createPerson(new PersonStudent("Emma", "Andersen", LocalDate.now(), 22, true, 9927, LocalDate.now()));

            personService.createPerson(new PersonEmployee("Gurli", "Klausen", LocalDate.now(), 48, true, 1556895555, (float) 120.5, "taxed"));

            // Creatin the PHD Grade
            gradeService.createGrade(new Grade("PHD", 5));

            // Delete a student
            personService.deleteByMatNr(9924);

            // Giving grades to some students
            personService.giveGradeToPerson(9922, "PHD");
            personService.giveGradeToPerson(9923, "PHD");
            personService.giveGradeToPerson(9927, "PHD");

            // Get list of PhDStudents
            List<PersonStudent> personStudentList = personService.getStudentsByGrade("PHD");
            for (PersonStudent personStudent : personStudentList) {
                System.out.println("...PhD Student: " + personStudent.toString());
            }

        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            exception.printStackTrace();
        } finally {
            // No need to let it stand and run
            System.exit(1);
        }
    }
}
