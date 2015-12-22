package com.ejbbet.sessionbeans;

import com.ejbbet.entities.Outcome;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class OutcomeFacade extends AbstractFacade<Outcome> {
    
    @PersistenceContext(unitName = "com.ejbbet_EjbBet_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OutcomeFacade() {
        super(Outcome.class);
    }
   
}
