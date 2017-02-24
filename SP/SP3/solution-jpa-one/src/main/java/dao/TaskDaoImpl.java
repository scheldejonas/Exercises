package dao;

import config.DataConfig;
import domain.Task;

import javax.persistence.EntityManager;

/**
 * Created by scheldejonas on 24/02/17.
 */
public class TaskDaoImpl implements TaskDao {

    private static TaskDao singleton = null;

    private TaskDaoImpl() {
    }

    public static TaskDao getSingleton() {
        if (singleton == null) {
            singleton = new TaskDaoImpl();
        }
        return singleton;
    }

    @Override
    public void createTask(Task task) {
        EntityManager entityManager = DataConfig.getSingleton().getEntityManagerFactory().createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(task);
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
