import config.DataConfig;
import dao.CustomerDaoImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 * Created by scheldejonas on 22/02/2017.
 */
public class Application {
    public static void main(String[] args) {
        // SP3 -> Task One -> Part Four
        EntityManager entityManager = DataConfig.getSingleton().getEntityManagerFactory().createEntityManager();
        
    }
}
