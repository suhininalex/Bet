package logic;

import util.Storable;
import java.util.List;

public abstract class Outcome implements Storable{
    double currentK;
    String description;
    long id;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    public double getCurrentK() {
        return currentK;
    }

    protected void setCurrentK(double currentK) {
        this.currentK = currentK;
    }
    
    public abstract List<Bet> getAllBets();
    
    public abstract Event getEvent();
    
    public abstract void setEvent(Event event);
    
    public void payAll(boolean winner){
        double amountForCompany = 0;
        for (Bet bet : getAllBets()){
            if (bet.getStatus()!=Bet.Status.Paid) {
                if (winner) amountForCompany+=bet.closeAsWinner();
                else        amountForCompany+=bet.closeAsLooser();
            }
        }
        CompanyUser companyUser = getEvent().getCompanyUser();
        if (winner) companyUser.withdraw(amountForCompany);
        else        companyUser.deposit(amountForCompany);
        companyUser.save();
    }
    
    public double riskAssesment(){
        double total = 0;
        for (Bet bet : getAllBets()){
            total +=bet.possibleWin();
        }
        return total;
    }
    
    public void assignOutcome(Event event, double currenK){
        setEvent(event);
        setCurrentK(currentK);
        save();
    }
    
    public abstract boolean equals(Outcome outcome);
    
}
