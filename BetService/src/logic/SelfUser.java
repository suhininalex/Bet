package logic;

import java.util.List;
import util.EntityProvider;

public abstract class SelfUser extends BasicUser{
    String fullname;

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
    
    public void createBet(Outcome outcome, double amount){
        Bet bet = EntityProvider.getBusinessFactories().getBetInstance();
        this.withdraw(amount);
        bet.setUser(this);
        bet.setOutcome(outcome);
        bet.amount = amount;
        bet.K = outcome.getCurrentK();
        bet.save();
    }
      
    public abstract List<Bet> getBets();
}
