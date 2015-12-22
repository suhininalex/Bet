package com.ejbbet.authorization;

import com.ejbbet.entities.Selfuser;
import com.ejbbet.sessionbeans.SelfuserFacade;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "userAuthorizationBean")
@SessionScoped
public class UserAuthorizationBean extends AuthorizationBean{

    @EJB
    private SelfuserFacade selfuserFacade;

    public UserAuthorizationBean() {
    }

    @Override
    protected boolean checkAuthorization() {
        return getUserBean()!=null;
    }
    
    public Selfuser getUserBean(){
        return selfuserFacade.getSelfuser(getUsername(), getPassword());
    }
    
}
