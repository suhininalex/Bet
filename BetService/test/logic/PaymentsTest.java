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

public class PaymentsTest {
    
    public PaymentsTest() {
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
     * Test of payAll method, of class Payments.
     */
    @Test
    public void testPayAll() {
        System.out.println("payAll");
        
        List<Outcome> outcomes = new LinkedList<>();
        Dummies.eventOutcomes = outcomes;
        
        Event event = EntityProvider.getBusinessFactories().getEventInstance();
        CompanyUser companyUser = EntityProvider.getBusinessFactories().getCompanyUserInstance();
        companyUser.balance = 500.0;
        event.setCompanyUser(companyUser);
        List<Bet> bets1 = new LinkedList<>();
        
        Dummies.outcomeBets = bets1;
        Outcome outcome1 = EntityProvider.getBusinessFactories().getOutcomeInstance();
        outcome1.setEvent(event);
        SelfUser selfUser1 = EntityProvider.getBusinessFactories().getSelfUserInstance();
        SelfUser selfUser2 = EntityProvider.getBusinessFactories().getSelfUserInstance();

        Bet bet1 = EntityProvider.getBusinessFactories().getBetInstance();
        bet1.setK(2);
        bet1.setUser(selfUser1);
        bet1.setOutcome(outcome1);
        bet1.setAmount(50.0);
        bets1.add(bet1);

        Bet bet2 = EntityProvider.getBusinessFactories().getBetInstance();
        bet2.setUser(selfUser2);
        bet2.setOutcome(outcome1);
        bet2.setAmount(70.0);
        bet2.setK(2);
        bets1.add(bet2);
        
        outcomes.add(outcome1);
        
        List<Bet> bets2 = new LinkedList<>();
        Dummies.outcomeBets = bets2;
        
        Outcome outcome2 = EntityProvider.getBusinessFactories().getOutcomeInstance();
        outcome2.setEvent(event);
        SelfUser selfUser3 = EntityProvider.getBusinessFactories().getSelfUserInstance();
        SelfUser selfUser4 = EntityProvider.getBusinessFactories().getSelfUserInstance();

        Bet bet3 = EntityProvider.getBusinessFactories().getBetInstance();
        bet3.setUser(selfUser3);
        bet3.setOutcome(outcome2);
        bet3.setAmount(25.0);
        bet3.setK(3);
        bets2.add(bet3);
        
        Bet bet4 = EntityProvider.getBusinessFactories().getBetInstance();
        bet4.setUser(selfUser4);
        bet4.setOutcome(outcome2);
        bet4.setAmount(30.0);
        bet4.setK(3);
        bets2.add(bet4);
        outcomes.add(outcome2);
        
        Payments payment = EntityProvider.getBusinessFactories().getPaymentInstance();
        payment.setEvent(event);
        payment.setWinnerOutcome(outcome1);
        
        payment.payAll();
        
        assertEquals(315.0, companyUser.getBalance(), 0.1);
    }
    
}
