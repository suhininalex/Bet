package logic;

import util.Storable;

public abstract class Payments extends WithDataProvider implements Storable{
    
    Status status;
    Long id = null;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
        Waiting (0), 
        Paid (1);
        
        private final int code;

        Status(int code) {
            this.code = code;
        }
        
        public static Status getFromCode(int code){
            switch (code) {
                case 0: return Waiting;
                case 1: return Paid;
                default: throw new IllegalArgumentException("Accepted codes are 0-2");
            }
        }

        public int getCode() {
            return code;
        }
    }
}
