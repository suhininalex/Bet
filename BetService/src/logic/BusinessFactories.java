package logic;

public interface BusinessFactories {
    
    public Bet getBetInstance();
    
    public Event getEventInstance();
    
    public Outcome getOutcomeInstance();
    
    public Payments getPaymentInstance();
    
    public SelfUser getSelfUserInstance();
    
    public CompanyUser getCompanyUserInstance();
}
