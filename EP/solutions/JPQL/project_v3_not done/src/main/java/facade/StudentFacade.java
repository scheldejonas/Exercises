/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import domain.Student;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author schelde
 */
public class StudentFacade {
    private final static StudentFacade singleton = new StudentFacade();
    private EntityManager entityManager = Facade.getSingleton().getEntityManager();

    private StudentFacade() {
    }

    public static StudentFacade getSingleton() {
        System.out.println(singleton.toString());
        return singleton;
    }

    public List<Student> findAllStudents() {
        List<Student> studentList = entityManager.createNamedQuery("Student.findAll").getResultList();
        return studentList;
    }

    public List<Student> findAllStudentsByFirstname(String firstname) {
        List<Student> studentList = entityManager.createQuery("select s from Student s where s.firstname = :firstname").setParameter("firstname", firstname).getResultList();
        return studentList;
    }

    public List<Student> findAllStudentsByLastname(String lastname) {
        List<Student> studentList = entityManager.createQuery("select s from Student s where s.lastname = :lastname").setParameter("lastname", lastname).getResultList();
        return studentList;
    }

    public Student getStudentWithGreatestScore() {
        Student student = (Student) entityManager.createQuery("select p.studentId from Studypoint p group by p.studentId.id order by sum(p.score) desc").getResultList().get(0);
        return student;
    }

    @Override
    public String toString() {
        return "StudentFacade{" +
                "entityManager=" + entityManager +
                '}';
    }
}
