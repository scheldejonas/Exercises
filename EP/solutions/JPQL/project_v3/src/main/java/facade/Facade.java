/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import domain.Student;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 *
 * @author schelde
 */
public class Facade {
    private static final Facade singleton = new Facade();
    private EntityManager entityManager = Persistence.createEntityManagerFactory("jpqlDemoPU").createEntityManager();

    private Facade() {
    }

    public static Facade getSingleton() {
        return singleton;
    }

    public List<Student> findAllStudents() {
        return StudentFacade.getSingleton() .findAllStudents();
    }

    public List<Student> findAllStudentsByFirstname(String firstname) {
        return StudentFacade.getSingleton().findAllStudentsByFirstname(firstname);
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public List<Student> findAllStudentsByLastname(String lastname) {
        return StudentFacade.getSingleton().findAllStudentsByLastname(lastname);
    }

    public long getSumOfStudypointsByStudentId(int id) {
        return StudypointFacade.getSingleton().getSumOfStudypointsByStudentId(id);
    }

    public long getSumOfAllStudypoints() {
        return StudypointFacade.getSingleton().getSumOfAllStudypoints();
    }

    public Student getStudentWithGreatesScore() {
        return StudentFacade.getSingleton().getStudentWithGreatestScore();
    }




}

