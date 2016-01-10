package com.ejbbet.sessionbeans;

import com.ejbbet.entities.Selfuser;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

@Stateless
public class SelfuserFacade extends AbstractFacade<Selfuser> {

    @PersistenceContext(unitName = "com.ejbbet_EjbBet_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SelfuserFacade() {
        super(Selfuser.class);
    }
    
    public Selfuser getSelfuser(String username, String password){
        try{
            TypedQuery<Selfuser> query = em.createNamedQuery("Selfuser.findByLogname", Selfuser.class);
            query.setParameter("logname", username);
            Selfuser selfuser = query.getSingleResult();
            if (selfuser.getPassword().equals(password)) return selfuser;
        } catch (NoResultException ex){}
        return null;
    }
    
    public void deposit(Selfuser user, float amount){
        if (amount>0) {
            user.setBalance(user.getBalance()+amount);
            edit(user);
        }
    }
    
    public void withdraw(Selfuser user, float amount){
        if (user.getBalance()>=amount) {
            user.setBalance(user.getBalance()-amount);
            edit(user);
        }
    }
    
    public boolean createUser(String username, String fullname, String password){
        if (username==null || fullname==null || password==null) return false;
        Query query = em.createNamedQuery("Selfuser.findByLogname");
        query.setParameter("logname", username);
        if (!query.getResultList().isEmpty()) return false;
        Selfuser user = new Selfuser();
        user.setBalance(0);
        user.setLogname(username);
        user.setFullname(fullname);
        user.setPassword(password);
        em.persist(user);
        return true;
    }
}
