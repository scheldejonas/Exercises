/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import javax.persistence.EntityManager;

/**
 *
 * @author schelde
 */
public class StudypointFacade {
    private final static StudypointFacade singleton = new StudypointFacade();
    private EntityManager entityManager = Facade.getSingleton().getEntityManager();

    private StudypointFacade() {
    }

    public static StudypointFacade getSingleton() {
        return singleton;
    }

    public long getSumOfStudypointsByStudentId(int id) {
        long studyPointsTotal = (long) entityManager.createQuery("select sum(p.score) from Studypoint p where p.studentId.id = :id").setParameter("id",id).getSingleResult();
        return studyPointsTotal;
    }

    public long getSumOfAllStudypoints() {
        long studypointsTotal = (long) entityManager.createQuery("select sum(p.score) from Studypoint p").getSingleResult();
        return studypointsTotal;
    }
}
