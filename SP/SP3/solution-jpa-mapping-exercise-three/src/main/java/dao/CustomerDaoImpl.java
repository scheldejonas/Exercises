package dao;

import config.DataConfig;
import domain.Customer;
import org.hibernate.Hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by scheldejonas on 22/02/2017.
 */
public class CustomerDaoImpl implements CustomerDao {

    private static CustomerDao singleton = null;
    private EntityManagerFactory trainingJpaEntityManager = null;

    private CustomerDaoImpl() {
        this.trainingJpaEntityManager = DataConfig.getSingleton().getEntityManagerFactory();
    }

    public static CustomerDao getSingleton() {
        if (singleton == null) {
            singleton = new CustomerDaoImpl();
        }
        return singleton;
    }

    @Override
    public void save(Customer customer) {
        EntityManager entityManager = trainingJpaEntityManager.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            entityManager.persist(customer);
            entityTransaction.commit();
        } catch (Exception exception) {
            entityTransaction.rollback();
            exception.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Customer> findAll() {
        EntityManager entityManager = trainingJpaEntityManager.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        List<Customer> customerList = new ArrayList<>();
        try {
            entityTransaction.begin();
            customerList = entityManager.createQuery("select c from Customer c order by c.id asc").getResultList();
            entityTransaction.commit();
        } catch (Exception exception) {
            entityTransaction.rollback();
            exception.printStackTrace();
        } finally {
            entityManager.close();
        }
        return customerList;
    }

    @Override
    public void update(Customer customer) {
        EntityManager entityManager = trainingJpaEntityManager.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            Customer customer1 = entityManager.find(Customer.class, customer.getId());
            Hibernate.initialize(customer1.getDiscountType());
            customer1.setDiscountType(customer.getDiscountType());
            customer1.setFirstName(customer.getFirstName());
            customer1.setPrice(customer.getPrice());
            entityTransaction.commit();
        } catch (Exception exception) {
            entityTransaction.rollback();
            exception.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

}
