package country;

import config.DataConfig;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by scheldejonas on 10/03/17.
 */
public class CountryDao {
    private static final CountryDao singleton = new CountryDao();

    private CountryDao() {
    }

    public static CountryDao getSingleton() {
        return singleton;
    }

    public List<Country> findAll() {
        EntityManager entityManager = DataConfig.getSingleton().getEntityManagerFactory().createEntityManager();
        List<Country> countryList = new ArrayList<>();
        try {
            entityManager.getTransaction().begin();
            countryList = entityManager.createQuery("select c from Country c order by c.name asc").getResultList();
            entityManager.getTransaction().commit();
        } catch (Exception exception) {
            entityManager.getTransaction().rollback();
            System.err.println(exception.getMessage());
            exception.printStackTrace();
        } finally {
            entityManager.close();
        }
        countryList.forEach(Country::toString);
        return countryList;
    }
}
