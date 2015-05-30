package logic;

import java.util.LinkedList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class OutcomeTest {
    
    public OutcomeTest() {
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
     * Test of payAll method, of class Outcome.
     */
    @Test
    public void testPayAllWin() {
        System.out.print("payAll  \t");
        CompanyUser companyUser = Dummies.getCompanyUserDummy();
        companyUser.balance = 500.0;
        SelfUser selfUser1 = Dummies.getSelfUserDummy();
        SelfUser selfUser2 = Dummies.getSelfUserDummy();
        List<Bet> bets = new LinkedList<>();
        Outcome outcome = Dummies.getOutcomeDummy(bets, 0);
        Event event = Dummies.getEventDummy(null);
        event.setCompanyUser(companyUser);
        outcome.setEvent(event);

        Bet bet1 = Dummies.getBetDummy();
        bet1.assignBet(selfUser1, outcome, 50.0);
        bets.add(bet1);
        Bet bet2 = Dummies.getBetDummy();
        bet2.assignBet(selfUser2, outcome, 70.0);
        bets.add(bet2);
               
        outcome.payAll(true);
                
        assertEquals(250.0, selfUser1.getBalance(),0.1);
        assertEquals(270.0, selfUser2.getBalance(),0.1);
        assertEquals(260.0, companyUser.getBalance(),0.1);
        
        System.out.println("OK");
    }
    
    /**
     * Test of payAll method, of class Outcome.
     */
    @Test
    public void testPayAllLoose() {
        System.out.print("payAllLoose  \t");
        CompanyUser companyUser = Dummies.getCompanyUserDummy();
        companyUser.balance = 500.0;
        SelfUser selfUser1 = Dummies.getSelfUserDummy();
        SelfUser selfUser2 = Dummies.getSelfUserDummy();
        List<Bet> bets = new LinkedList<>();
        Outcome outcome = Dummies.getOutcomeDummy(bets, 0);
        Event event = Dummies.getEventDummy(null);
        event.setCompanyUser(companyUser);
        outcome.setEvent(event);

        Bet bet1 = Dummies.getBetDummy();
        bet1.assignBet(selfUser1, outcome, 50.0);
        bets.add(bet1);
        Bet bet2 = Dummies.getBetDummy();
        bet2.assignBet(selfUser2, outcome, 70.0);
        bets.add(bet2);
               
        outcome.payAll(false);
                
        assertEquals(150.0, selfUser1.getBalance(),0.1);
        assertEquals(130.0, selfUser2.getBalance(),0.1);
        assertEquals(620.0, companyUser.getBalance(),0.1);
        
        System.out.println("OK");
    }

    /**
     * Test of riskAssesment method, of class Outcome.
     */
    @Test
    public void testRiskAssesment() {
        System.out.println("riskAssesment");
        
        CompanyUser companyUser = Dummies.getCompanyUserDummy();
        companyUser.balance = 500.0;
        SelfUser selfUser1 = Dummies.getSelfUserDummy();
        SelfUser selfUser2 = Dummies.getSelfUserDummy();
        List<Bet> bets = new LinkedList<>();
        Outcome outcome = Dummies.getOutcomeDummy(bets, 0);
        Event event = Dummies.getEventDummy(null);
        event.setCompanyUser(companyUser);
        outcome.setEvent(event);

        Bet bet1 = Dummies.getBetDummy();
        bet1.assignBet(selfUser1, outcome, 50.0);
        bets.add(bet1);
        Bet bet2 = Dummies.getBetDummy();
        bet2.assignBet(selfUser2, outcome, 70.0);
        bets.add(bet2);
        double result = outcome.riskAssesment();
        
        assertEquals(240.0, result, 0.1);
    }
  
}
