package logic;

import java.util.LinkedList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

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
        
        Event event = Dummies.getEventDummy(outcomes);
        CompanyUser companyUser = Dummies.getCompanyUserDummy();
        companyUser.balance = 500.0;
        event.setCompanyUser(companyUser);
        List<Bet> bets1 = new LinkedList<>();
        
        Outcome outcome1 = Dummies.getOutcomeDummy(bets1, 0);
        outcome1.setEvent(event);
        SelfUser selfUser1 = Dummies.getSelfUserDummy();
        SelfUser selfUser2 = Dummies.getSelfUserDummy();

        Bet bet1 = Dummies.getBetDummy();
        bet1.assignBet(selfUser1, outcome1, 50.0);
        bets1.add(bet1);
        Bet bet2 = Dummies.getBetDummy();
        bet2.assignBet(selfUser2, outcome1, 70.0);
        bets1.add(bet2);
        
        outcomes.add(outcome1);
        
        List<Bet> bets2 = new LinkedList<>();
        
        Outcome outcome2 = Dummies.getOutcomeDummy(bets2, 1);
        outcome2.setEvent(event);
        SelfUser selfUser3 = Dummies.getSelfUserDummy();
        SelfUser selfUser4 = Dummies.getSelfUserDummy();

        Bet bet3 = Dummies.getBetDummy();
        bet3.assignBet(selfUser3, outcome2, 25.0);
        bets2.add(bet3);
        Bet bet4 = Dummies.getBetDummy();
        bet4.assignBet(selfUser4, outcome2, 30.0);
        bets2.add(bet4);
        
        outcomes.add(outcome2);
        
        Payments payment = Dummies.getPaymentDummy();
        payment.assignPayment(event, outcome1);
        
        payment.payAll();
        
        assertEquals(315.0, companyUser.getBalance(), 0.1);
    }
    
}
