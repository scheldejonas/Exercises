package dao;

import config.DataConfig;
import domain.Person;

import javax.persistence.EntityManager;

/**
 * Created by scheldejonas on 24/02/17.
 */
public class PersonDaoImpl implements PersonDao {

    private static PersonDaoImpl singleton = null;

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
        EntityManager entityManager = DataConfig.getSingleton().getEntityManagerFactory().createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(person);
            entityManager.getTransaction().commit();
        } catch (Exception exception) {
            entityManager.getTransaction().rollback();
            System.err.println(exception.getMessage());
            exception.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Person findPerson(Long personId) {
        EntityManager entityManager = DataConfig.getSingleton().getEntityManagerFactory().createEntityManager();
        Person person = null;
        try {
            entityManager.getTransaction().begin();
            person = entityManager.find(Person.class,personId);
            entityManager.getTransaction().commit();
        } catch (Exception exception) {
            entityManager.getTransaction().rollback();
            System.out.println(exception.getMessage());
            exception.printStackTrace();
        } finally {
            entityManager.close();
        }
        return person;
    }

    @Override
    public void update(Person person) {
        EntityManager entityManager = DataConfig.getSingleton().getEntityManagerFactory().createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(person);
            entityManager.getTransaction().commit();
        } catch (Exception exception) {
            entityManager.getTransaction().rollback();
            System.out.println(exception.getMessage());
            exception.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void delete(Person person) {
        EntityManager entityManager = DataConfig.getSingleton().getEntityManagerFactory().createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(person);
            entityManager.getTransaction().commit();
        } catch (Exception exception) {
            entityManager.getTransaction().rollback();
            System.out.println(exception.getMessage());
            exception.printStackTrace();
        } finally {
            entityManager.close();
        }
    }
}
