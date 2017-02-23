package dao;

import config.DataConfig;
import domain.DiscountType;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 * Created by scheldejonas on 23/02/2017.
 */
public class DiscountTypeDaoImpl implements DiscountTypeDao {

    private static DiscountTypeDao singleton = null;

    private DiscountTypeDaoImpl() {
    }

    public static DiscountTypeDao getSingleton() {
        if (singleton == null) {
            singleton = new DiscountTypeDaoImpl();
        }
        return singleton;
    }

    @Override
    public void save(DiscountType discountType) {
        EntityManager entityManager = DataConfig.getSingleton().getEntityManagerFactory().createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(discountType);
            entityManager.getTransaction().commit();
        } catch (Exception exception) {
            entityManager.getTransaction().rollback();
            exception.printStackTrace();
        } finally {
            entityManager.close();
        }
    }
}
