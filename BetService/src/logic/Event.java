package logic;

import java.util.Date;
import util.Storable;
import java.util.List;
import util.Factories;

public abstract class Event implements Storable{
    
    String description;
    Date expirationTime;
    Status status = Status.Open;
    Long id;

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
    
    public void assignEvent(CompanyUser companyUser, String description, Date expires){
        setCompanyUser(companyUser);
        setDescription(description);
        setExpirationTime(expires);
        setStatus(Status.Open);
        save();
    }
    
    public void setWinner(Outcome winner){
        setStatus(Status.Processing);
        Payments payment = Factories.getPaymentsFactory().getObject();
        payment.assignPayment(this, winner);
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
