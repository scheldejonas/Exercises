package dao;

import config.DataConfig;
import domain.Task;

import javax.persistence.EntityManager;

/**
 * Created by scheldejonas on 24/02/17.
 */
public class TaskDaoImpl implements TaskDao {

    private static TaskDao singleton = null;
    private EntityManager entityManager = null;

    private TaskDaoImpl() {
        this.entityManager = DataConfig.getSingleton().getEntityManager();
    }

    public static TaskDao getSingleton() {
        if (singleton == null) {
            singleton = new TaskDaoImpl();
        }
        return singleton;
    }

    @Override
    public void createTask(Task task) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(task);
            entityManager.getTransaction().commit();
        } catch (Exception exception) {
            entityManager.getTransaction().rollback();
            System.out.println(exception.getMessage());
            exception.printStackTrace();
        }
    }
}
