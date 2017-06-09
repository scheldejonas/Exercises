package dao;

import config.DataConfig;
import domain.Project;
import domain.Task;

import javax.persistence.EntityManager;
import javax.xml.crypto.Data;

/**
 * Created by scheldejonas on 24/02/17.
 */
public class ProjectDaoImpl implements ProjectDao {

    private static ProjectDaoImpl singleton = null;
    private EntityManager entityManager = null;

    private ProjectDaoImpl() {
        this.entityManager = DataConfig.getSingleton().getEntityManager();
    }

    public static ProjectDaoImpl getSingleton() {
        if (singleton == null) {
            singleton = new ProjectDaoImpl();
        }
        return singleton;
    }

    @Override
    public void createProject(Project project) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(project);
            entityManager.getTransaction().commit();
        } catch (Exception exception) {
            entityManager.getTransaction().rollback();
            System.err.println(exception.getMessage());
            exception.printStackTrace();
        }
    }

    @Override
    public Project findProject(Long projectId) {
        Project project = null;
        try {
            project = entityManager.find(Project.class,projectId);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            exception.printStackTrace();
        }
        return project;
    }

    @Override
    public void update(Project project) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(project);
            entityManager.getTransaction().commit();
        } catch (Exception exception) {
            entityManager.getTransaction().rollback();
            System.out.println(exception.getMessage());
            exception.printStackTrace();
        }
    }
}
