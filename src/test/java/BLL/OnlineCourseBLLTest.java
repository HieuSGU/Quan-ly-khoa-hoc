/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package BLL;

import DTO.OnlineCourseDTO;
import java.sql.ResultSet;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author nqbao
 */
public class OnlineCourseBLLTest {
    
    public OnlineCourseBLLTest() {
    }

    @org.junit.jupiter.api.BeforeAll
    public static void setUpClass() throws Exception {
    }

    @org.junit.jupiter.api.AfterAll
    public static void tearDownClass() throws Exception {
    }

    @org.junit.jupiter.api.BeforeEach
    public void setUp() throws Exception {
    }

    @org.junit.jupiter.api.AfterEach
    public void tearDown() throws Exception {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of add method, of class OnlineCourseBLL.
     */
    @org.junit.jupiter.api.Test
    public void testAdd() {
        System.out.println("add");
        OnlineCourseDTO object = null;
        OnlineCourseBLL instance = new OnlineCourseBLL();
        instance.add(object);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of delete method, of class OnlineCourseBLL.
     */
    @org.junit.jupiter.api.Test
    public void testDelete() {
        System.out.println("delete");
        OnlineCourseDTO object = null;
        OnlineCourseBLL instance = new OnlineCourseBLL();
        instance.delete(object);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of edit method, of class OnlineCourseBLL.
     */
    @org.junit.jupiter.api.Test
    public void testEdit() {
        System.out.println("edit");
        OnlineCourseDTO object = null;
        OnlineCourseBLL instance = new OnlineCourseBLL();
        instance.edit(object);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addFromFile method, of class OnlineCourseBLL.
     */
    @org.junit.jupiter.api.Test
    public void testAddFromFile() {
        System.out.println("addFromFile");
        String filePath = "";
        OnlineCourseBLL instance = new OnlineCourseBLL();
        instance.addFromFile(filePath);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of find method, of class OnlineCourseBLL.
     */
    @org.junit.jupiter.api.Test
    public void testFind() {
        System.out.println("find");
        String objectId = "";
        OnlineCourseBLL instance = new OnlineCourseBLL();
        OnlineCourseDTO expResult = null;
        OnlineCourseDTO result = instance.find(objectId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllCoursesResultSet method, of class OnlineCourseBLL.
     */
    @org.junit.jupiter.api.Test
    public void testGetAllCoursesResultSet() {
        System.out.println("getAllCoursesResultSet");
        OnlineCourseBLL instance = new OnlineCourseBLL();
        ResultSet expResult = null;
        ResultSet result = instance.getAllCoursesResultSet();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
