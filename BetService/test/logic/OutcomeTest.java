package logic;

import java.util.LinkedList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import util.EntityProvider;

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
        ConfigTest.config();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of payAll method, of class Outcome.
     */
    @Test
    public void testPayAllWin() {
        System.out.print("outcome payAll win\t");
        CompanyUser companyUser = EntityProvider.getBusinessFactories().getCompanyUserInstance();
        companyUser.balance = 500.0;
        List<Bet> bets = new LinkedList<>();
        
        SelfUser selfUser1 = EntityProvider.getBusinessFactories().getSelfUserInstance();
        Bet bet1 = EntityProvider.getBusinessFactories().getBetInstance();
        bet1.setUser(selfUser1);
        bet1.setK(2);
        bet1.setAmount(50);
        
        SelfUser selfUser2 = EntityProvider.getBusinessFactories().getSelfUserInstance();
        bets.add(bet1);
        Bet bet2 = EntityProvider.getBusinessFactories().getBetInstance();
        bet2.setUser(selfUser2);
        bet2.setAmount(70.0);
        bet2.setK(3);
        
        Dummies.outcomeBets = bets;
        Outcome outcome = EntityProvider.getBusinessFactories().getOutcomeInstance();
        Event event = EntityProvider.getBusinessFactories().getEventInstance();
        event.setCompanyUser(companyUser);
        outcome.setEvent(event);

        bets.add(bet2);
               
        outcome.payAll(true);
                
        assertEquals(300.0, selfUser1.getBalance(),0.1);
        assertEquals(410.0, selfUser2.getBalance(),0.1);
        assertEquals(190.0, companyUser.getBalance(),0.1);
        
        System.out.println("OK");
    }
    
    /**
     * Test of payAll method, of class Outcome.
     */
    @Test
    public void testPayAllLoose() {
        System.out.print("outcome payAll loose \t");
        CompanyUser companyUser = EntityProvider.getBusinessFactories().getCompanyUserInstance();
        companyUser.balance = 500.0;
        List<Bet> bets = new LinkedList<>();
        
        SelfUser selfUser1 = EntityProvider.getBusinessFactories().getSelfUserInstance();
        Bet bet1 = EntityProvider.getBusinessFactories().getBetInstance();
        bet1.setUser(selfUser1);
        bet1.setK(2);
        bet1.setAmount(50);
        
        SelfUser selfUser2 = EntityProvider.getBusinessFactories().getSelfUserInstance();
        bets.add(bet1);
        Bet bet2 = EntityProvider.getBusinessFactories().getBetInstance();
        bet2.setUser(selfUser2);
        bet2.setAmount(70.0);
        bet2.setK(3);
        
        Dummies.outcomeBets = bets;
        Outcome outcome = EntityProvider.getBusinessFactories().getOutcomeInstance();
        Event event = EntityProvider.getBusinessFactories().getEventInstance();
        event.setCompanyUser(companyUser);
        outcome.setEvent(event);

        bets.add(bet2);
               
        outcome.payAll(false);
                
        assertEquals(200.0, selfUser1.getBalance(),0.1);
        assertEquals(200.0, selfUser2.getBalance(),0.1);
        assertEquals(620.0, companyUser.getBalance(),0.1);
        
        System.out.println("OK");
    }
    
    /**
     * Test of riskAssesment method, of class Outcome.
     */
    @Test
    public void testRiskAssesment() {
        System.out.print("risk assesment \t");
        CompanyUser companyUser = EntityProvider.getBusinessFactories().getCompanyUserInstance();
        companyUser.balance = 500.0;
        List<Bet> bets = new LinkedList<>();
        
        SelfUser selfUser1 = EntityProvider.getBusinessFactories().getSelfUserInstance();
        Bet bet1 = EntityProvider.getBusinessFactories().getBetInstance();
        bet1.setUser(selfUser1);
        bet1.setK(2);
        bet1.setAmount(50);
        
        SelfUser selfUser2 = EntityProvider.getBusinessFactories().getSelfUserInstance();
        bets.add(bet1);
        Bet bet2 = EntityProvider.getBusinessFactories().getBetInstance();
        bet2.setUser(selfUser2);
        bet2.setAmount(70.0);
        bet2.setK(3);
        
        Dummies.outcomeBets = bets;
        Outcome outcome = EntityProvider.getBusinessFactories().getOutcomeInstance();
        Event event = EntityProvider.getBusinessFactories().getEventInstance();
        event.setCompanyUser(companyUser);
        outcome.setEvent(event);

        bets.add(bet2);
              
        assertEquals(310.0, outcome.riskAssesment(),0.1);
        
        System.out.println("\tOK");
    }
  
}
