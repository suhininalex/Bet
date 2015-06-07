package database;

import java.sql.SQLException;
import java.util.Date;
import logic.CompanyUser;
import logic.Event;
import logic.Outcome;
import logic.SelfUser;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import util.DBBusinessFactories;
import util.EntityProvider;
import util.MySqlUtil;

public class EventDBTest {
    
    public EventDBTest() {
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
     * Test of save method, of class EventDB.
     */
    @Test
    public void testCreateAndLoad() throws SQLException {
        CompanyUser user = EntityProvider.getBusinessFactories().getCompanyUserInstance(MySqlUtil.getConnection());
        user.login("llama", "11235815");
        user.createEvent("New event, best of all!", new Date(System.currentTimeMillis()+3600*1000*24*31));
        Event event = user.getOpenEvents().get(0);
        event.addOutcome("Text outcome 1!", 2);
        event.addOutcome("Text outcome 2!", 0.35);
        System.out.println("Random k: "+event.getOutcomes().get(1).getCurrentK());
        System.out.println("size of events:"+user.getOpenEvents().size());
        Outcome outcome = event.getOutcomes().get(0);
        SelfUser selfUser = EntityProvider.getBusinessFactories().getSelfUserInstance(MySqlUtil.extractConnection(event));
        selfUser.login("llama", "11235813");
        selfUser.createBet(outcome, 100.0);
        System.out.println("Total bets: " + selfUser.getBets().size());
        System.out.println(outcome.getAllBets().get(0).getUser().getFullname());
        
        event.setWinner(outcome);
    }
}
