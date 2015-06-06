package logic;

import java.util.Date;
import util.Storable;
import java.util.List;
import util.EntityProvider;

public abstract class Event implements Storable{
    
    String description;
    Date expirationTime;
    Status status = Status.Open;
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

    protected void setStatus(Status status) {
        this.status = status;
    }
       
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(Date expirationTime) {
        this.expirationTime = expirationTime;
    }
    
    public abstract CompanyUser getCompanyUser();
    
    protected abstract void setCompanyUser(CompanyUser companyUser);
    
    public abstract List<Outcome> getOutcomes();
    
    public void addOutcome(String name, double k){
        Outcome outcome = EntityProvider.getBusinessFactories().getOutcomeInstance();
        outcome.setCurrentK(k);
        outcome.setName(name);
        outcome.setEvent(this);
        outcome.save();
    }
       
    public void setWinner(Outcome winner){
        setStatus(Status.Processing);
        save();
        Payments payment = EntityProvider.getBusinessFactories().getPaymentInstance();
        payment.setEvent(this);
        payment.setStatus(Payments.Status.Waiting);
        payment.setWinnerOutcome(winner);
        payment.save();
    }
    
    public static enum Status {
        Open (0), 
        Closed (1), 
        Processing(2);
        
        private final int code;

        Status(int code) {
            this.code = code;
        }
        
        public static Status getFromCode(int code){
            switch (code) {
                case 0: return Open;
                case 1: return Closed;
                case 2: return Processing;
                default: throw new IllegalArgumentException("Accepted codes are 0-2");
            }
        }

        public int getCode() {
            return code;
        }
    }
    
}
