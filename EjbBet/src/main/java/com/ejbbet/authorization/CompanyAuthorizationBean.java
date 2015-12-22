package com.ejbbet.authorization;

import com.ejbbet.entities.Companyuser;
import com.ejbbet.sessionbeans.CompanyuserFacade;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "companyAuthorizationBean")
@SessionScoped
public class CompanyAuthorizationBean extends AuthorizationBean{

    @EJB
    private CompanyuserFacade companyuserFacade;

    public CompanyAuthorizationBean() {
    }

    @Override
    protected boolean checkAuthorization() {
        return getCompanyUserBean()!=null;
    }
    
    public Companyuser getCompanyUserBean(){
        return companyuserFacade.getCompanyuser(getUsername(), getPassword());
    }
    
}
