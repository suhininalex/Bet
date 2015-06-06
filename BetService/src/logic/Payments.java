package logic;

import util.Storable;

public abstract class Payments implements Storable{
    
    Status status;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
    
    protected abstract void setEvent(Event event);
    
    public abstract Event getEvent();
    
    protected abstract void setWinnerOutcome(Outcome outcome);
    
    protected abstract Outcome getWinnerOutcome();
    
    public void payAll(){
        Outcome winner = getWinnerOutcome();
        winner.payAll(true);
        for (Outcome outcome : getEvent().getOutcomes()){
            if (!winner.equals(outcome)) {
                outcome.payAll(false);
                System.out.println("not winner");
            }
        }
        status = Status.Paid;
        save();
    }
       
    public static enum Status {
        Waiting, Paid
    }
}
