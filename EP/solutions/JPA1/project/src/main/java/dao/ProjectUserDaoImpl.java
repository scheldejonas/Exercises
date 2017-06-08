package dao;

import config.DataConfig;
import domain.ProjectUser;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by scheldejonas on 22/02/2017.
 */
public class ProjectUserDaoImpl implements ProjectUserDao {

    private static ProjectUserDao singleton = null;
    private EntityManager entityManager = null;

    private ProjectUserDaoImpl() {
        this.entityManager = DataConfig.getSingleton().getEntityManager();
    }

    public static ProjectUserDao getSingleton() {
        if (singleton == null) {
            singleton = new ProjectUserDaoImpl();
        }
        return singleton;
    }

    @Override
    public void create(ProjectUser projectUser) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(projectUser);
            entityManager.getTransaction().commit();
        } catch (Exception exception) {
            entityManager.getTransaction().rollback();
            System.err.println(exception.getMessage());
            exception.printStackTrace();
        }
    }

    @Override
    public ProjectUser findUser(Long id) {
        ProjectUser projectUser = null;
        try {
            projectUser = entityManager.find(ProjectUser.class,id);
        } catch (NoResultException exception) {
            System.err.println(exception.getMessage());
            exception.printStackTrace();
        }
        return projectUser;
    }

    @Override
    public List<ProjectUser> getAllUsers() {
        List<ProjectUser> projectUserList = new ArrayList<>();
        try {
            projectUserList = entityManager.createQuery("select c from ProjectUser c order by c.id asc").getResultList();
        } catch (NoResultException exception) {
            System.err.println(exception.getMessage());
            exception.printStackTrace();
        }
        return projectUserList;
    }

    @Override
    public void update(ProjectUser projectUser) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(projectUser);
            entityManager.getTransaction().commit();
        } catch (Exception exception) {
            entityManager.getTransaction().rollback();
            exception.printStackTrace();
        }
    }

}
