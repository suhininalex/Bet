package com.ejbbet.sessionbeans;

import com.ejbbet.entities.Bet;
import com.ejbbet.entities.Outcome;
import com.ejbbet.entities.Selfuser;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class BetFacade extends AbstractFacade<Bet> {

    @EJB
    private OutcomeFacade outcomeFacade;

    @EJB
    private SelfuserFacade selfuserFacade;

    @PersistenceContext(unitName = "com.ejbbet_EjbBet_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BetFacade() {
        super(Bet.class);
    }
    
    public boolean createBet(Selfuser user, Outcome outcome, float amount){
        if (amount<=0 || user.getBalance()<amount) return false;
        user.setBalance(user.getBalance()-amount);
        Bet bet = new Bet();
        bet.setAmount(amount);
        bet.setIdOutcome(outcome);
        bet.setK(outcome.getK());
        bet.setStatus(Bet.Status.Open);
        bet.setUser(user);
        user.getBetCollection().add(bet);
        selfuserFacade.edit(user);
        outcome.getBetCollection().add(bet);
        outcomeFacade.edit(outcome);
        create(bet);
        return true;
    }
    
}
