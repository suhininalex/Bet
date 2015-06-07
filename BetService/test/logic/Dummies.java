package logic;

import java.util.List;

public class Dummies implements BusinessFactories{
    @Override
    public Bet getBetInstance(Object dataProvider){
        Bet bet = new Bet() {

            SelfUser selfUser;
            Outcome outcome;
            
            @Override
            public SelfUser getUser() {
                return selfUser;
            }

            @Override
            public void setUser(SelfUser user) {
                selfUser = user;
            }

            @Override
            public Outcome getOutcome() {
                return outcome;
            }

            @Override
            public void setOutcome(Outcome outcome) {
                this.outcome = outcome;
            }

            @Override
            public void save() {
            }
        };
        bet.setAmount(50.0);
        bet.K=2;
        return bet;
    }
    
    @Override
    public SelfUser getSelfUserInstance(Object dataProvider){
        SelfUser selfUser = new SelfUser() {
            @Override
            public void save() {

            }

            @Override
            public void login(String logname, String password) {
            }

            @Override
            public List<Bet> getBets() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        selfUser.balance = 200.0;
        return selfUser;
    }
    
    @Override
    public CompanyUser getCompanyUserInstance(Object dataProvider){
        CompanyUser companyUser = new CompanyUser() {
            @Override
            public void save() {
            }

            @Override
            public void login(String logname, String password) {
            }

            @Override
            public List<Event> getOpenEvents() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        companyUser.balance = 200.0;
        return companyUser;
    }
    
    public static List<Outcome> eventOutcomes;
    @Override
    public Event getEventInstance(Object dataProvider){
        return new Event() {

            CompanyUser companyUser;
            List<Outcome> outcomes = eventOutcomes;
            
            @Override
            public CompanyUser getCompanyUser() {
                return companyUser;
            }

            @Override
            protected void setCompanyUser(CompanyUser companyUser) {
                this.companyUser = companyUser;
            }

            @Override
            public List<Outcome> getOutcomes() {
                return outcomes;
            }

            @Override
            public void save() {
            }
        };
    }
    
    @Override
    public Payments getPaymentInstance(Object dataProvider){
        return new Payments() {

            Event event;
            Outcome winner;
            
            @Override
            protected void setEvent(Event event) {
                this.event = event;
            }

            @Override
            public Event getEvent() {
                return event;
            }

            @Override
            protected void setWinnerOutcome(Outcome outcome) {
                this.winner = outcome;
            }

            @Override
            protected Outcome getWinnerOutcome() {
                return winner;
            }

            @Override
            public void save() {
            }
        };
    }

    public static List<Bet> outcomeBets;
    @Override
    public Outcome getOutcomeInstance(Object dataProvider) {
        return new Outcome() {
            List<Bet> bets = outcomeBets;
            Event event;
            
            @Override
            public List<Bet> getAllBets() {
                return bets;
            }

            @Override
            public Event getEvent() {
                return event;
            }

            @Override
            public void setEvent(Event event) {
                this.event = event;
            }

            @Override
            public void save() {
            }
        };
    }
}
