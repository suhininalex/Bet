/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.SQLException;
import java.sql.Statement;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import util.MySqlUtil;

/**
 *
 * @author llama
 */
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
        CompanyUserDB user = new CompanyUserDB();
        user.setConnectionToUse(MySqlUtil.getConnection());
        user.setLogname("llama");
        user.setCompanyName("Llamas corporation");
        user.setPassword("11235815");
        user.save();
        
        System.out.println("Trying to load...");
        Statement s = MySqlUtil.getConnection().createStatement();
        CompanyUserDB userLoaded = new CompanyUserDB();
        userLoaded.load(s.executeQuery("SELECT * FROM COMPANYUSER WHERE ID_COMPANY=1"));
        System.out.println("Company name:" + userLoaded.getCompanyName());
        assertTrue(userLoaded.getCompanyName().equals(userLoaded.getCompanyName()));
    }
    
    @Test
    public void testLogin(){
        System.out.println("Trying to login...");
        CompanyUserDB user = new CompanyUserDB();
        user.setConnectionToUse(MySqlUtil.getConnection());
        user.login("llama", "11235815");
        System.out.println(user.getCompanyName());
    }
    

    
}
