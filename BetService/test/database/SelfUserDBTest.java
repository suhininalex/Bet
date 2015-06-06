package database;

import java.sql.Connection;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import util.MySqlUtil;

public class SelfUserDBTest {
    
    public SelfUserDBTest() {
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
     * Test of save method, of class SelfUserDB.
     */
    @Test
    public void testSave() {
        SelfUserDB user = new SelfUserDB();
        user.setConnectionToUse(MySqlUtil.getConnection());
        user.setLogname("llama");
        user.setFullname("crazy llama");
        user.setPassword("11235813");
        user.save();
        System.out.println(user.getId());
    }
    
}
