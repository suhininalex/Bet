package logic;

import util.Storable;

public abstract class Bet implements Storable{
    double amount;
    double K;
    Status status;

    public double getAmount() {
        return amount;
    }

    protected void setAmount(double amount) {
        this.amount = amount;
    }

    public double getK() {
        return K;
    }

    protected void setK(double K) {
        this.K = K;
    }

    public Status getStatus() {
        return status;
    }

    protected void setStatus(Status status) {
        this.status = status;
    }
    
    public abstract SelfUser getUser();
    
    public abstract void setUser(SelfUser user);
    
    public abstract Outcome getOutcome();
    
    public abstract void setOutcome(Outcome outcome);
    
    public double closeAsWinner(){
        SelfUser user = getUser();
        user.deposit(possibleWin());
        user.save();
        this.status=Status.Paid;
        this.save();
        return possibleWin();
    }
    
    public double possibleWin(){
        return getAmount()*getK();
    }
    
    public double closeAsLooser(){
        this.status=Status.Paid;
        this.save();
        return amount;
    }
    
    public void assignBet(SelfUser selfUser, Outcome outcome, double amount){
        selfUser.withdraw(amount);
        this.setUser(selfUser);
        this.setOutcome(outcome);
        this.amount = amount;
        this.K = outcome.getCurrentK();
        this.save();
    }
        
    public static enum Status {
        Open, Paid
    }
}
