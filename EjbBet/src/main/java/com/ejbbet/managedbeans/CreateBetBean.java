package com.ejbbet.managedbeans;

import com.ejbbet.authorization.UserAuthorizationBean;
import com.ejbbet.entities.Outcome;
import com.ejbbet.sessionbeans.BetFacade;
import com.ejbbet.sessionbeans.OutcomeFacade;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "createBetBean")
@ViewScoped
public class CreateBetBean {
    
    @ManagedProperty(value="#{userAuthorizationBean}")
    UserAuthorizationBean userAuthorizationBean;

    public UserAuthorizationBean getUserAuthorizationBean() {
        return userAuthorizationBean;
    }

    public void setUserAuthorizationBean(UserAuthorizationBean userAuthorizationBean) {
        this.userAuthorizationBean = userAuthorizationBean;
    }

    @EJB
    private BetFacade betFacade;

    @EJB
    private OutcomeFacade outcomeFacade;

    private int idOutcome;
    private float amount=100f;

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
    
    public int getIdOutcome() {
        return idOutcome;
    }

    public void setIdOutcome(int idOutcome) {
        this.idOutcome = idOutcome;
    }
    
    public CreateBetBean() {
    }
    
    public Outcome getOutcome(){
        return outcomeFacade.find(idOutcome);
    }
    
    public String createBet(){
        if (betFacade.createBet(userAuthorizationBean.getUserBean(), getOutcome(), getAmount()))
            return "index.xhtml";
        else {
            FacesContext.getCurrentInstance().addMessage("amount", new FacesMessage("Can't create bet. Check your balance."));
            return null;
        }
    }
}
