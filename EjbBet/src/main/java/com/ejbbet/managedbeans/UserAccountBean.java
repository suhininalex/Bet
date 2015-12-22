package com.ejbbet.managedbeans;

import com.ejbbet.authorization.UserAuthorizationBean;
import com.ejbbet.sessionbeans.SelfuserFacade;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "userAccountBean")
@RequestScoped
public class UserAccountBean {

    @EJB
    private SelfuserFacade selfuserFacade;
    
    @ManagedProperty(value="#{userAuthorizationBean}")
    UserAuthorizationBean userAuthorizationBean;

    public UserAuthorizationBean getUserAuthorizationBean() {
        return userAuthorizationBean;
    }

    public void setUserAuthorizationBean(UserAuthorizationBean userAuthorizationBean) {
        this.userAuthorizationBean = userAuthorizationBean;
    }
    
    private String operation = "deposit";
    private float amount = 100f;

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
    
    public UserAccountBean() {
    }

    public void action(){
        if ("deposit".equals(operation)) selfuserFacade.deposit(userAuthorizationBean.getUserBean(), amount);
        else if ("withdraw".equals(operation)) selfuserFacade.withdraw(userAuthorizationBean.getUserBean(), amount);
    }
}
