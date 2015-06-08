package logic;

public interface BusinessFactories {
    
    public Bet getBetInstance(Object dataProvider);
    
    public Event getEventInstance(Object dataProvider);
    
    public Outcome getOutcomeInstance(Object dataProvider);
    
    public Payments getPaymentInstance(Object dataProvider);
    
    public SelfUser getSelfUserInstance(Object dataProvider);
    
    public CompanyUser getCompanyUserInstance(Object dataProvider);
    
    public OwnerUser getOwnerUserInstance(Object dataProvider);
}
