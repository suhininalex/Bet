package com.ejbbet.sessionbeans;

import com.ejbbet.entities.Bet;
import com.ejbbet.entities.Companyuser;
import com.ejbbet.entities.Event;
import com.ejbbet.entities.Outcome;
import com.ejbbet.entities.Owneruser;
import com.ejbbet.entities.Payment;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class PaymentFacade extends AbstractFacade<Payment>{

    @EJB
    private SelfuserFacade selfuserFacade;

    @EJB
    private OwneruserFacade owneruserFacade;
   
    @PersistenceContext(unitName = "com.ejbbet_EjbBet_war_1.0-SNAPSHOTPU")
    private EntityManager em;    
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PaymentFacade() {
        super(Payment.class);
    }
    
    public void createPayment(Outcome winner){
        Payment payment = new Payment();
        payment.setIdEvent(winner.getIdEvent());
        payment.setIdWinneroutcome(winner);
        payment.setStatus(Payment.Status.Waiting);
        payment.getIdEvent().setStatus(Event.Status.Processing);
        create(payment);
        em.merge(payment.getIdEvent());
    }
    
    public void reject(Payment payment){
        payment.getIdEvent().setStatus(Event.Status.Open);
        em.merge(payment.getIdEvent());
        remove(payment);
    }
    
    public void approve(Payment payment){
        float comission = 0.02f;
        Event event = payment.getIdEvent();
        Companyuser companyUser = event.getIdCompany();
        Owneruser owneruser = owneruserFacade.getSingleAdmin();
        
        float totalFromCompany = payAsWinners(payment.getIdWinneroutcome(), comission);
        companyUser.setBalance(companyUser.getBalance() - totalFromCompany);
        owneruser.setBalance(owneruser.getBalance()+totalFromCompany*comission);
        
        float totalToCompany = 0f;
        for (Outcome outcome : event.getOutcomeCollection()){
            if (outcome.equals(payment.getIdWinneroutcome())) continue;
            totalToCompany+=payAsLoosers(outcome);
        }
        companyUser.setBalance(companyUser.getBalance()+totalToCompany*(1-comission));
        owneruser.setBalance(owneruser.getBalance()+totalToCompany*comission);
        
        event.setStatus(Event.Status.Closed);
        payment.setStatus(Payment.Status.Paid);
        em.merge(owneruser);
        em.merge(companyUser);
        em.merge(event);
        em.merge(payment);
    }
    
    private float payAsWinners(Outcome outcome, float comission){
        float totalFromCompany = 0f;
        for (Bet bet : outcome.getBetCollection()){
            bet.setStatus(Bet.Status.Paid);
            float amount = bet.getAmount()*bet.getK();
            selfuserFacade.deposit(bet.getUser(), amount*(1-comission));
            totalFromCompany+=amount;
        }
        return totalFromCompany;
    }
    
    private float payAsLoosers(Outcome outcome){
        float totalToCompany = 0f;
        for (Bet bet : outcome.getBetCollection()){
            bet.setStatus(Bet.Status.Paid);
            em.merge(bet);
            totalToCompany+=bet.getAmount();
        }
        return totalToCompany;
    }
}
