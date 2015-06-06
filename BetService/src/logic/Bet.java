package logic;

import util.Storable;

public abstract class Bet extends WithDataProvider implements Storable{
    double amount;
    double K;
    Status status;
    Long id = null;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
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
    
    public static enum Status {
        Open (0), 
        Paid (1);
        
        private final int code;

        Status(int code) {
            this.code = code;
        }
        
        public static Status getFromCode(int code){
            switch (code) {
                case 0: return Open;
                case 1: return Paid;
                default: throw new IllegalArgumentException("Accepted codes are 0-2");
            }
        }

        public int getCode() {
            return code;
        }
    }

}
