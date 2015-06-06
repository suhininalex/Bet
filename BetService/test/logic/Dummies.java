package logic;

import java.util.List;

public class Dummies {
    public static Bet getBetDummy(){
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
        return bet;
    }
    
    public static SelfUser getSelfUserDummy(){
        SelfUser selfUser = new SelfUser() {
            @Override
            public void save() {

            }

            @Override
            public void login(String logname, String password) {
            }
        };
        selfUser.balance = 200.0;
        return selfUser;
    }
    
    public static Outcome getOutcomeDummy(final List<Bet> allbets, final long dummyId){
        Outcome outcome = new Outcome() {
            public final long id = dummyId;
            public final List<Bet> bets= allbets;
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
            public boolean equals(Outcome outcome) {
                if (this.getClass().isInstance(outcome)) return this.id==this.getClass().cast(outcome).id;
                else return false;
            }

            @Override
            public void save() {
            }
        };
        outcome.setCurrentK(2);
        return outcome;
    }
    
    public static CompanyUser getCompanyUserDummy(){
        CompanyUser companyUser = new CompanyUser() {
            @Override
            public void save() {
            }

            @Override
            public void login(String logname, String password) {
            }
        };
        companyUser.balance = 200.0;
        return companyUser;
    }
    
    public static Event getEventDummy(final List<Outcome> outcomes){
        return new Event() {

            CompanyUser companyUser;
            List<Outcome> alloutcomes = outcomes;
            
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
                return alloutcomes;
            }

            @Override
            public void save() {
            }
        };
    }
    
    public static Payments getPaymentDummy(){
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
}
