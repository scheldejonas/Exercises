package dao;

import config.DataConfig;
import domain.Grade;
import domain.PersonStudent;
import service.GradeService;
import service.GradeServiceImpl;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * Created by schelde on 09/06/17.
 */
public class GradeDaoImpl implements GradeDao {
    private static final GradeDao singleton = new GradeDaoImpl();
    private EntityManager entityManager = DataConfig.getSingleton().getEntityManager();

    private GradeDaoImpl() {
    }

    public static GradeDao getSingleton() {
        return singleton;
    }

    @Override
    public Grade findByName(String phd) {
        Grade grade = null;
        try {
            Query query = entityManager.createQuery("select g from Grade g where g.name = :phd");
            query.setParameter("phd", phd);
            grade = (Grade) query.getSingleResult();
            ;
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            exception.printStackTrace();
        }
        return grade;
    }

    @Override
    public void create(Grade grade) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(grade);
            entityManager.getTransaction().commit();
        } catch (Exception exception) {
            entityManager.getTransaction().rollback();
            System.err.println(exception.getMessage());
            exception.printStackTrace();
        }
    }
}
