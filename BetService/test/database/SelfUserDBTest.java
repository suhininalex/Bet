package database;

import logic.SelfUser;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import util.DBBusinessFactories;
import util.EntityProvider;
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
        EntityProvider.setBusinessFactoris(new DBBusinessFactories());
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of save method, of class SelfUserDB.
     */
    @Test
    public void testSave() {
        SelfUser user = EntityProvider.getBusinessFactories().getSelfUserInstance(MySqlUtil.getConnection());
        user.setLogname("llama");
        user.setFullname("crazy llama");
        user.setPassword("11235813");
        user.save();
        System.out.println(user.getId());
    }
    
}
