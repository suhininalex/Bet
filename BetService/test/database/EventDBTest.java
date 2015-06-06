package database;

import java.util.Date;
import java.util.List;
import logic.Outcome;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import util.MySqlUtil;

public class EventDBTest {
    
    public EventDBTest() {
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
     * Test of getOutcomes method, of class EventDB.
     */
    @Test
    public void testGetOutcomes() {
        System.out.println("getOutcomes");
        EventDB instance = new EventDB();
        instance.setConnectionToUse(MySqlUtil.getConnection());
        List<Outcome> expResult = null;
        List<Outcome> result = instance.getOutcomes();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of save method, of class EventDB.
     */
    @Test
    public void testSaveAndLoad() {
        System.out.println("Trying to save...");
        CompanyUserDB companyUserDB = new CompanyUserDB();
        companyUserDB.setConnectionToUse(MySqlUtil.getConnection());
        companyUserDB.login("llama", "11235815");
        EventDB instance = new EventDB();
        instance.setConnectionToUse(MySqlUtil.getConnection());
        instance.assignEvent(companyUserDB, "Best event! Hurry to choose your winner!", new Date(System.currentTimeMillis()));
        instance.save();
    }
    
    
}
