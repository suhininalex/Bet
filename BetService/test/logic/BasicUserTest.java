package logic;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import util.EntityProvider;

public class BasicUserTest {
    
    public BasicUserTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        ConfigTest.config();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of withdraw method, of class BasicUser.
     */
    @Test
    public void testWithdraw() {
        System.out.print("withdraw \t");
        double amount = 50.0;
        BasicUser instance = EntityProvider.getBusinessFactories().getSelfUserInstance(null);
        instance.withdraw(amount);
        assertEquals(150.0, instance.getBalance(),0.01);
        System.out.println("OK");
    }

    /**
     * Test of deposit method, of class BasicUser.
     */
    @Test
    public void testDeposit() {
        System.out.print("deposit \t");
        double amount = 15.0;
        BasicUser instance = EntityProvider.getBusinessFactories().getSelfUserInstance(null);
        instance.deposit(amount);
        assertEquals(215.0, instance.getBalance(),0.01);
        System.out.println("OK");
    }
    
    /**
     * CreateBet
     */
    @Test
    public void testCreateBet() {
        System.out.print("create bet \t");
        double amount = 15.0;
        BasicUser instance = EntityProvider.getBusinessFactories().getSelfUserInstance(null);
        Outcome outcome = EntityProvider.getBusinessFactories().getOutcomeInstance(null);
        outcome.setCurrentK(2);
        instance.deposit(amount);
        assertEquals(215.0, instance.getBalance(),0.01);
        System.out.println("OK");
    }
    
   

    
}
