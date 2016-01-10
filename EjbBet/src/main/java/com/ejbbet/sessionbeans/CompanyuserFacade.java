package com.ejbbet.sessionbeans;

import com.ejbbet.entities.Companyuser;
import com.ejbbet.entities.Selfuser;
import javax.ejb.Stateless;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

@Stateless
public class CompanyuserFacade extends AbstractFacade<Companyuser> {

    @PersistenceContext(unitName = "com.ejbbet_EjbBet_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CompanyuserFacade() {
        super(Companyuser.class);
    }
    
    public Companyuser getCompanyuser(String username, String password){
        try{
            TypedQuery<Companyuser> query = em.createNamedQuery("Companyuser.findByLogname", Companyuser.class);
            query.setParameter("logname", username);
            Companyuser user = query.getSingleResult();
            if (user.getPassword().equals(password)) return user;
        } catch (NoResultException ex){}
        return null;
    }
    
    public boolean createUser(String username, String fullname, String password){
        if (username==null || fullname==null || password==null) return false;
        Query query = em.createNamedQuery("Companyuser.findByLogname");
        query.setParameter("logname", username);
        if (!query.getResultList().isEmpty()) return false;

        Companyuser user = new Companyuser();
        user.setBalance(0);
        user.setLogname(username);
        user.setFullname(fullname);
        user.setPassword(password);
        create(user);
        return true;
    }
}
