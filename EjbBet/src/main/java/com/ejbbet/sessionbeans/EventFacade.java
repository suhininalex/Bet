package com.ejbbet.sessionbeans;

import com.ejbbet.entities.Companyuser;
import com.ejbbet.entities.Event;
import com.ejbbet.entities.Outcome;
import java.util.Collection;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class EventFacade extends AbstractFacade<Event> {

    @PersistenceContext(unitName = "com.ejbbet_EjbBet_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EventFacade() {
        super(Event.class);
    }
    
    public Collection<Event> getAllAccessableEvents(){
        TypedQuery<Event> query = em.createNamedQuery("Event.findUnexpiredByStatus", Event.class);
        query.setParameter("status", Event.Status.Open.getCode());
        return query.getResultList();
    }
    
    public boolean createEvent(Companyuser company, String description, Date expiration, Collection<Outcome> outcomes){
        if (description==null || expiration==null || outcomes==null || outcomes.size()<2 || !expiration.after(new Date()))
            return false;
        Event event = new Event();
        event.setOutcomeCollection(outcomes);
        event.setDescription(description);
        event.setStatus(Event.Status.Open);
        event.setExpirationtime(expiration);
        event.setIdCompany(company);
        outcomes.stream().forEach(outcome -> outcome.setIdEvent(event));
        company.getEventCollection().add(event);
        em.merge(company);
        create(event);
        return true;
    }
}
