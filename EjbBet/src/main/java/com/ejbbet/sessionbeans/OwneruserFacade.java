package com.ejbbet.sessionbeans;

import com.ejbbet.entities.Owneruser;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class OwneruserFacade extends AbstractFacade<Owneruser> {

    @PersistenceContext(unitName = "com.ejbbet_EjbBet_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OwneruserFacade() {
        super(Owneruser.class);
    }
    
    public Owneruser getOwnerUser(String username, String password){
        try{
            TypedQuery<Owneruser> query = em.createNamedQuery("Owneruser.findByLogname", Owneruser.class);
            query.setParameter("logname", username);
            Owneruser owneruser = query.getSingleResult();
            if (owneruser.getPassword().equals(password)) return owneruser;
        } catch (NoResultException ex){}
        return null;
    }
    
    public Owneruser getSingleAdmin(){
        TypedQuery<Owneruser> query = em.createNamedQuery("Owneruser.findByLogname", Owneruser.class);
        query.setParameter("logname", "admin");
        return query.getSingleResult();
    }
    
}
