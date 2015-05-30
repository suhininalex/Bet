/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author llama
 */
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
    }
    
    @After
    public void tearDown() {
    }
    
    /**
     * Test of closeAsWinner method, of class Bet.
     */
    @Test
    public void testCloseAsWinner() {
        System.out.println("closeAsWinner");
        Bet instance = getTestBet();
        double result = instance.closeAsWinner();
        assertEquals(100.0, result, 0.1);
        result = instance.getUser().getBalance();
        assertEquals(250.0, result, 0.1);
    }

    /**
     * Test of possibleWin method, of class Bet.
     */
    @Test
    public void testPossibleWin() {
        System.out.print("possibleWin \t");
        Bet instance = getTestBet();
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
        Bet instance = getTestBet();
        double result = instance.closeAsLooser();
        assertEquals(50.0, result, 0.1);
        result = instance.getUser().getBalance();
        assertEquals(150.0, result, 0.1);
        System.out.println("OK");
    }
    
        /**
     * Test of testAssignBet method, of class Bet.
     */
    @Test
    public void testAssignBet() {
        System.out.print("testAssignBet \t");
        Bet instance = Dummies.getBetDummy();
        SelfUser user = Dummies.getSelfUserDummy();
        Outcome outcome = Dummies.getOutcomeDummy(null, 0);
        instance.assignBet(user, outcome, 50.0);
        double result = user.getBalance();
        assertEquals(150.0, result, 0.1);
        System.out.println("OK");
    }
    
    private Bet getTestBet(){
        Bet bet = Dummies.getBetDummy();
        SelfUser user = Dummies.getSelfUserDummy();
        Outcome outcome = Dummies.getOutcomeDummy(null, 0);
        bet.assignBet(user, outcome, 50.0);
        return bet;
    }    
}
