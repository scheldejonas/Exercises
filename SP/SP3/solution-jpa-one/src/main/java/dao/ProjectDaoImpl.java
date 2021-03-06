package dao;

import config.DataConfig;
import domain.Project;

import javax.persistence.EntityManager;

/**
 * Created by scheldejonas on 24/02/17.
 */
public class ProjectDaoImpl implements ProjectDao {

    private static ProjectDaoImpl singleton = null;

    private ProjectDaoImpl() {

    }

    public static ProjectDaoImpl getSingleton() {
        if (singleton == null) {
            singleton = new ProjectDaoImpl();
        }
        return singleton;
    }

    @Override
    public void createProject(Project project) {
        EntityManager entityManager = DataConfig.getSingleton().getEntityManagerFactory().createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(project);
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
    public Project findProject(Long projectId) {
        EntityManager entityManager = DataConfig.getSingleton().getEntityManagerFactory().createEntityManager();
        Project project = null;
        try {
            entityManager.getTransaction().begin();
            project = entityManager.find(Project.class,projectId);
            entityManager.getTransaction().commit();
        } catch (Exception exception) {
            entityManager.getTransaction().rollback();
            System.out.println(exception.getMessage());
            exception.printStackTrace();
        } finally {
            entityManager.close();
        }
        return project;
    }

    @Override
    public void update(Project project) {
        EntityManager entityManager = DataConfig.getSingleton().getEntityManagerFactory().createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(project);
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
