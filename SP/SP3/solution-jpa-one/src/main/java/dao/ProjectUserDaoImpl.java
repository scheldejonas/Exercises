package dao;

import config.DataConfig;
import domain.ProjectUser;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by scheldejonas on 22/02/2017.
 */
public class ProjectUserDaoImpl implements ProjectUserDao {

    private static ProjectUserDao singleton = null;
    private EntityManagerFactory trainingJpaEntityManagerFactory = null;

    private ProjectUserDaoImpl() {
        this.trainingJpaEntityManagerFactory = DataConfig.getSingleton().getEntityManagerFactory();
    }

    public static ProjectUserDao getSingleton() {
        if (singleton == null) {
            singleton = new ProjectUserDaoImpl();
        }
        return singleton;
    }

    @Override
    public void create(ProjectUser projectUser) {
        EntityManager entityManager = trainingJpaEntityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(projectUser);
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
    public ProjectUser findUser(Long id) {
        EntityManager entityManager = trainingJpaEntityManagerFactory.createEntityManager();
        ProjectUser projectUser = null;
        try {
            entityManager.getTransaction().begin();
            projectUser = entityManager.find(ProjectUser.class,id);
            entityManager.getTransaction().commit();
        } catch (Exception exception) {
            entityManager.getTransaction().rollback();
            System.err.println(exception.getMessage());
            exception.printStackTrace();
        } finally {
            entityManager.close();
        }
        return projectUser;
    }

    @Override
    public List<ProjectUser> getAllUsers() {
        EntityManager entityManager = trainingJpaEntityManagerFactory.createEntityManager();
        List<ProjectUser> projectUserList = new ArrayList<>();
        try {
            entityManager.getTransaction().begin();
            projectUserList = entityManager.createQuery("select c from ProjectUser c order by c.id asc").getResultList();
            entityManager.getTransaction().commit();
        } catch (Exception exception) {
            entityManager.getTransaction().rollback();
            System.err.println(exception.getMessage());
            exception.printStackTrace();
        } finally {
            entityManager.close();
        }
        return projectUserList;
    }

    @Override
    public void update(ProjectUser projectUser) {
        EntityManager entityManager = trainingJpaEntityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            ProjectUser projectUser1 = entityManager.find(ProjectUser.class, projectUser.getId());
            projectUser1.setUserName(projectUser.getUserName());
            projectUser1.setEmail(projectUser.getEmail());
            projectUser1.setCreated(projectUser.getCreated());
            entityTransaction.commit();
        } catch (Exception exception) {
            entityTransaction.rollback();
            exception.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

}
