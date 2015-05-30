package logic;

import util.Storable;
import java.sql.Time;
import java.util.List;
import util.Factories;

public abstract class Event implements Storable{
    
    String description;
    Time expirationTime;
    Status status;

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

    public Time getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(Time expirationTime) {
        this.expirationTime = expirationTime;
    }
    
    public abstract CompanyUser getCompanyUser();
    protected abstract void setCompanyUser(CompanyUser companyUser);
    
    public abstract List<Outcome> getOutcomes();
    
    public void assignEvent(CompanyUser companyUser, String description, Time expires, double[] outcomesK){
        setCompanyUser(companyUser);
        setDescription(description);
        setExpirationTime(expires);
        setStatus(Status.Open);
        save();
        for (double k : outcomesK){
            Outcome outcome = Factories.getOutcomeFactory().getObject();
            outcome.assignOutcome(this, k);
        }
    }
    
    public void setWinner(Outcome winner){
        setStatus(Status.Processing);
        Payments payment = Factories.getPaymentsFactory().getObject();
        payment.assignPayment(this, winner);
    }
    
    public static enum Status {
        Open, Closed, Processing
    }
    
}
