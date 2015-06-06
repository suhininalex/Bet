package logic;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import util.EntityProvider;

public class BetTest {
    
    public BetTest() {
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
     * Test of closeAsWinner method, of class Bet.
     */
    @Test
    public void testCloseAsWinner() {
        System.out.print("closeAsWinner \t");
        Bet instance = EntityProvider.getBusinessFactories().getBetInstance(null);
        SelfUser selfUser = EntityProvider.getBusinessFactories().getSelfUserInstance(null);
        instance.setUser(selfUser);
        double result = instance.closeAsWinner();
        assertEquals(100.0, result, 0.1);
        result = instance.getUser().getBalance();
        assertEquals(300.0, result, 0.1);
        System.out.println("OK");
    }

    /**
     * Test of possibleWin method, of class Bet.
     */
    @Test
    public void testPossibleWin() {
        System.out.print("possibleWin \t");
        Bet instance = EntityProvider.getBusinessFactories().getBetInstance(null);
        SelfUser selfUser = EntityProvider.getBusinessFactories().getSelfUserInstance(null);
        instance.setUser(selfUser);
        double expResult = 100.0;
        double result = instance.possibleWin();
        assertEquals(expResult, result, 0.1);
        System.out.println("OK");
    }

    /**
     * Test of closeAsLooser method, of class Bet.
     */
    @Test
    public void testCloseAsLooser() {
        System.out.print("closeAsLooser \t");
        Bet instance = EntityProvider.getBusinessFactories().getBetInstance(null);
        SelfUser selfUser = EntityProvider.getBusinessFactories().getSelfUserInstance(null);
        instance.setUser(selfUser);
        double result = instance.closeAsLooser();
        assertEquals(50.0, result, 0.1);
        result = instance.getUser().getBalance();
        assertEquals(200.0, result, 0.1);
        System.out.println("OK");
    }
}
