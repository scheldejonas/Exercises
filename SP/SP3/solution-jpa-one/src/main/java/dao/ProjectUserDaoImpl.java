package dao;

import config.DataConfig;
import domain.ProjectUser;
import org.hibernate.Hibernate;

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
    private EntityManagerFactory trainingJpaEntityManager = null;

    private ProjectUserDaoImpl() {
        this.trainingJpaEntityManager = DataConfig.getSingleton().getEntityManagerFactory();
    }

    public static ProjectUserDao getSingleton() {
        if (singleton == null) {
            singleton = new ProjectUserDaoImpl();
        }
        return singleton;
    }

    @Override
    public void save(ProjectUser projectUser) {
        EntityManager entityManager = trainingJpaEntityManager.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            entityManager.persist(projectUser);
            entityTransaction.commit();
        } catch (Exception exception) {
            entityTransaction.rollback();
            exception.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<ProjectUser> findAll() {
        EntityManager entityManager = trainingJpaEntityManager.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        List<ProjectUser> projectUserList = new ArrayList<>();
        try {
            entityTransaction.begin();
            projectUserList = entityManager.createQuery("select c from ProjectUser c order by c.id asc").getResultList();
            entityTransaction.commit();
        } catch (Exception exception) {
            entityTransaction.rollback();
            exception.printStackTrace();
        } finally {
            entityManager.close();
        }
        return projectUserList;
    }

    @Override
    public void update(ProjectUser projectUser) {
        EntityManager entityManager = trainingJpaEntityManager.createEntityManager();
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
