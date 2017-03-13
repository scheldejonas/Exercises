package info.schelde.trainingrestbackend.person;

import info.schelde.trainingrestbackend.config.DataConfig;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by scheldejonas on 10/03/17.
 */
public class PersonDao {

    public List<Person> findAll() {
        EntityManager entityManager = DataConfig.getSingleton().getEntityManagerFactory().createEntityManager();
        List<Person> personList = new ArrayList<>();
        try {
            entityManager.getTransaction().begin();
            personList = entityManager.createQuery("select c from Person c order by c.id asc").getResultList();
            entityManager.getTransaction().commit();
        } catch (Exception exception) {
            entityManager.getTransaction().rollback();
            System.err.println(exception.getMessage());
            exception.printStackTrace();
        } finally {
            entityManager.close();
        }
        return personList;
    }
}
