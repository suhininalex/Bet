package com.ejbbet.authorization;
import com.ejbbet.entities.Owneruser;
import com.ejbbet.sessionbeans.OwneruserFacade;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "adminAuthorizatonBean")
@SessionScoped
public class AdminAuthorizatonBean extends AuthorizationBean{

    @EJB
    private OwneruserFacade owneruserFacade;

    public AdminAuthorizatonBean() {
    }

    @Override
    public boolean checkAuthorization() {
        return getAdminBean()!=null;
    }
    
    public Owneruser getAdminBean(){
        return owneruserFacade.getOwnerUser(getUsername(), getPassword());
    }
    
    
}
