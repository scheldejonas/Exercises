/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

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
public class StudypointFacadeTest {

    public StudypointFacadeTest() {
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
     * Test of getSingleton method, of class StudypointFacade.
     */
    @Test
    public void testGetSingleton() {
        System.out.println("getSingleton");
        StudypointFacade result = StudypointFacade.getSingleton();
        assertNotNull(result);
    }

    /**
     * Test of getSumOfStudypointsByStudentId method, of class StudentFacade.
     */
    @Test
    public void testGetSumOfStudypointsByStudentId() {
        System.out.println("getSumOfStudypointsByStudentId");
        int id = 1;
        StudypointFacade instance = StudypointFacade.getSingleton();
        long expResult = 8;
        long result = instance.getSumOfStudypointsByStudentId(id);
        assertEquals(expResult, result);
    }

    /**
     * Test of getSumOfAllStudypoints method, of class StudentFacade.
     */
    @Test
    public void testGetSumOfAllStudypoints() {
        System.out.println("getSumOfAllStudypoints");
        StudypointFacade instance = StudypointFacade.getSingleton();
        long expResult = 8L;
        long result = instance.getSumOfAllStudypoints();
        assertTrue(result > expResult);
    }

}
