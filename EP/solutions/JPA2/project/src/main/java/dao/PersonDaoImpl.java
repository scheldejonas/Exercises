package dao;

import config.DataConfig;
import domain.Person;
import domain.PersonStudent;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by scheldejonas on 24/02/17.
 */
public class PersonDaoImpl implements PersonDao {
    private static PersonDaoImpl singleton = null;
    private EntityManager entityManager = DataConfig.getSingleton().getEntityManager();

    private PersonDaoImpl() {
    }

    public static PersonDaoImpl getSingleton() {
        if (singleton == null) {
            singleton = new PersonDaoImpl();
        }
        return singleton;
    }

    @Override
    public void createPerson(Person person) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(person);
            entityManager.getTransaction().commit();
        } catch (Exception exception) {
            entityManager.getTransaction().rollback();
            System.err.println(exception.getMessage());
            exception.printStackTrace();
        }
    }

    @Override
    public Person findPerson(Long personId) {
        Person person = null;
        try {
            person = entityManager.find(Person.class,personId);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            exception.printStackTrace();
        }
        return person;
    }

    @Override
    public void update(Person person) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(person);
            entityManager.getTransaction().commit();
        } catch (Exception exception) {
            entityManager.getTransaction().rollback();
            System.out.println(exception.getMessage());
            exception.printStackTrace();
        }
    }

    @Override
    public void delete(Person person) {
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(person);
            entityManager.getTransaction().commit();
        } catch (Exception exception) {
            entityManager.getTransaction().rollback();
            System.out.println(exception.getMessage());
            exception.printStackTrace();
        }
    }

    @Override
    public PersonStudent findPersonByMatNr(int matNr) {
        PersonStudent personStudent = null;
        try {
            Query query = entityManager.createQuery("select s from PersonStudent s where s.matNr = :matNr");
            query.setParameter("matNr", matNr);
            personStudent = (PersonStudent) query.getSingleResult();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            exception.printStackTrace();
        }
        return personStudent;
    }

    @Override
    public List<PersonStudent> getStudentsByGrade(String gradeName) {
        List<PersonStudent> studentListWithGrade = null;
        try {
            Query query = entityManager.createQuery("select s from PersonStudent s where s.grade.name = :gradeName");
            query.setParameter("gradeName", gradeName);
            studentListWithGrade = (List<PersonStudent>) query.getResultList();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            exception.printStackTrace();
        }
        return studentListWithGrade;
    }
}
