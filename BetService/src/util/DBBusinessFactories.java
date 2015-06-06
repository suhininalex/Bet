package util;

import database.BetDB;
import database.CompanyUserDB;
import database.EventDB;
import database.OutcomeDB;
import database.PaymentsDB;
import database.SelfUserDB;
import logic.Bet;
import logic.BusinessFactories;
import logic.CompanyUser;
import logic.Event;
import logic.Outcome;
import logic.Payments;
import logic.SelfUser;

public class DBBusinessFactories implements BusinessFactories{

    @Override
    public Bet getBetInstance(Object dataProvider) {
        Bet bet = new BetDB();
        bet.setDataProvider(dataProvider);
        return bet;
    }

    @Override
    public Event getEventInstance(Object dataProvider) {
        Event event = new EventDB();
        event.setDataProvider(dataProvider);
        return event;
    }

    @Override
    public Outcome getOutcomeInstance(Object dataProvider) {
        Outcome outcome = new OutcomeDB();
        outcome.setDataProvider(dataProvider);
        return outcome;
    }

    @Override
    public Payments getPaymentInstance(Object dataProvider) {
        Payments payments = new PaymentsDB();
        payments.setDataProvider(dataProvider);
        return payments;
    }

    @Override
    public SelfUser getSelfUserInstance(Object dataProvider) {
        SelfUser selfUser = new SelfUserDB();
        selfUser.setDataProvider(dataProvider);
        return selfUser;
    }

    @Override
    public CompanyUser getCompanyUserInstance(Object dataProvider) {
        CompanyUser companyUser = new CompanyUserDB();
        companyUser.setDataProvider(dataProvider);
        return companyUser;
    }
    
}
