/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import domain.Student;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author schelde
 */
public class StudentFacadeTest {

    public StudentFacadeTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of findAllStudents method, of class StudentFacade.
     */
    @Test
    public void testFindAllStudents() {
        System.out.println("findAllStudents");
        StudentFacade instance = StudentFacade.getSingleton();
        List<Student> result = instance.findAllStudents();
        assertTrue(result.size() > 0);
    }

    /**
     * Test of findAllStudentsByFirstname method, of class StudentFacade.
     */
    @Test
    public void testFindAllStudentsByFirstname() {
        System.out.println("findAllStudentsByFirstname");
        String firstname = "Jan";
        StudentFacade instance = StudentFacade.getSingleton();
        List<Student> result = instance.findAllStudentsByFirstname(firstname);
        assertEquals(firstname, result.get(0).getFirstname());
    }

    /**
     * Test of getSingleton method, of class StudentFacade.
     */
    @Test
    public void testGetSingleton() {
        System.out.println("getSingleton");
        StudentFacade result = StudentFacade.getSingleton();
        assertNotNull(result);
    }

    /**
     * Test of findAllStudentsByLastname method, of class StudentFacade.
     */
    @Test
    public void testFindAllStudentsByLastname() {
        System.out.println("findAllStudentsByLastname");
        String lastname = "Hansen";
        StudentFacade instance = StudentFacade.getSingleton();
        List<Student> result = instance.findAllStudentsByLastname(lastname);
        assertEquals(lastname, result.get(0).getLastname());
    }

    /**
     * Test of getStudentWithGreatestScore method, of class StudentFacade.
     */
    @Test
    public void testGetStudentWithGreatestScore() {
        System.out.println("getStudentWithGreatestScore");
        StudentFacade instance = StudentFacade.getSingleton();
        String firstname = "Jonas";
        Student result = instance.getStudentWithGreatestScore();
        assertEquals(firstname, result.getFirstname());
    }

}
