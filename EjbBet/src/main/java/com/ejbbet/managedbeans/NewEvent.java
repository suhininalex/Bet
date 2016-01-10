package com.ejbbet.managedbeans;

import com.ejbbet.authorization.CompanyAuthorizationBean;
import com.ejbbet.entities.Outcome;
import com.ejbbet.sessionbeans.EventFacade;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;


@ManagedBean(name = "newEvent")
@ViewScoped

public class NewEvent {

    @ManagedProperty(value="#{companyAuthorizationBean}")
    CompanyAuthorizationBean companyAuthorizationBean;    

    public CompanyAuthorizationBean getCompanyAuthorizationBean() {
        return companyAuthorizationBean;
    }

    public void setCompanyAuthorizationBean(CompanyAuthorizationBean companyAuthorizationBean) {
        this.companyAuthorizationBean = companyAuthorizationBean;
    }
    
    @EJB
    private EventFacade eventFacade;

    String description;
    int expiresAfter;
    List<Outcome> outcomes = new LinkedList<>();
    
    @PostConstruct
    public void initList() {
        outcomes.add(createOutcome("Yes",2f));
        outcomes.add(createOutcome("No",1.5f));
    }

    public List<Outcome> getOutcomes() {
        return outcomes;
    }

    public void setOutcomes(List<Outcome> outcomes) {
        this.outcomes = outcomes;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getExpiresAfter() {
        return expiresAfter;
    }

    public void setExpiresAfter(int expiresAfter) {
        this.expiresAfter = expiresAfter;
    }
    
    public String action(){
        if (eventFacade.createEvent(companyAuthorizationBean.getCompanyUserBean(), description, getTimeAfterHours(expiresAfter), outcomes)) return "index.xhtml";
        else {
            FacesContext.getCurrentInstance().addMessage("event", new FacesMessage("Can't create event. Check fields are correct."));
            return null;
        }
        
    }

    public void newOutcome(){
        outcomes.add(createOutcome("Maybe", 1.1f));
    }
    
    public NewEvent() {
        
    }
    
    private Outcome createOutcome(String name, float rate){
        Outcome outcome = new Outcome();
        outcome.setName(name);
        outcome.setK(rate);
        return outcome;
    }
    
    private Date getTimeAfterHours(int hours){
        return new Date(new Date().getTime()+3600*1000*hours);
    }
}
