package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import logic.CompanyUser;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import util.DBBusinessFactories;
import util.EntityProvider;
import util.MySqlUtil;

public class CompanyUserDbTest {
    
    public CompanyUserDbTest() {
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
     * Test of save method, of class CompanyUserDB.
     */
    @Test
    public void testSaveAndLoad() throws SQLException {
        System.out.println("Trying to save...");
        CompanyUser user = EntityProvider.getBusinessFactories().getCompanyUserInstance(MySqlUtil.getConnection());
        user.setLogname("llama");
        user.setCompanyName("Llamas corporation");
        user.setPassword("11235815");
        user.setBalance(1000);
        user.save();
        
        System.out.println("Trying to load...");
        Statement s = MySqlUtil.getConnection().createStatement();
        CompanyUserDB userLoaded = new CompanyUserDB();
        ResultSet rs = s.executeQuery("SELECT * FROM COMPANYUSER WHERE ID_COMPANY=1");
        rs.next();
        userLoaded.load(rs);
        System.out.println("Company name:" + userLoaded.getCompanyName());
        assertTrue(userLoaded.getCompanyName().equals(userLoaded.getCompanyName()));
    }
    
    @Test
    public void testLogin(){
        System.out.println("Trying to login...");
        CompanyUser user = EntityProvider.getBusinessFactories().getCompanyUserInstance(MySqlUtil.getConnection());
        user.login("llama", "11235815");
        System.out.println(user.getCompanyName());
    }
    

    
}
