package com.ejbbet.managedbeans;

import com.ejbbet.authorization.CompanyAuthorizationBean;
import com.ejbbet.entities.Event;
import com.ejbbet.entities.Outcome;
import com.ejbbet.sessionbeans.EventFacade;
import com.ejbbet.sessionbeans.PaymentFacade;
import java.util.Collection;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "companyEventBean")
@ViewScoped
public class CompanyEventBean {

    @EJB
    private PaymentFacade paymentFacade;

    @EJB
    private EventFacade eventFacade;
    
    @ManagedProperty(value="#{companyAuthorizationBean}")
    CompanyAuthorizationBean companyAuthorizationBean;    

    public CompanyAuthorizationBean getCompanyAuthorizationBean() {
        return companyAuthorizationBean;
    }

    public void setCompanyAuthorizationBean(CompanyAuthorizationBean companyAuthorizationBean) {
        this.companyAuthorizationBean = companyAuthorizationBean;
    }
    String filter = "None";

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public CompanyEventBean() {
    }
    
    public void action(){ }
    
    public Collection<Event> getEvents(){
        if (getEventStatus()!=null) return companyAuthorizationBean.getCompanyUserBean().getEventCollection().stream()
                .filter(it -> it.getStatus()==getEventStatus()).collect(Collectors.toList());
        else return companyAuthorizationBean.getCompanyUserBean().getEventCollection();
    }
    
    private Event.Status getEventStatus(){
        switch (filter) {
            case "Closed": return Event.Status.Closed;
            case "Open": return Event.Status.Open;
            case "Processing": return Event.Status.Processing;
            default: return null;
        }
    }
    
    int idEvent;

    public int getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(int idEvent) {
        this.idEvent = idEvent;
    }
    
    public Event getEvent(){
        return eventFacade.find(idEvent);
    }
    
    public float getRisk(Outcome outcome){
        return outcome.getBetCollection().stream()
                .map(it -> it.getAmount()*it.getK()).reduce(0f, (a,b) -> a+b);
    }
    
    public boolean getIsOpen(){
        return (getEvent().getStatus() == Event.Status.Open) && 
               (companyAuthorizationBean.getCompanyUserBean().equals(getEvent().getIdCompany()));
    }
    
    public String winner(Outcome outcome){
        if (getIsOpen()) paymentFacade.createPayment(outcome);
        return "index.xhtml";
    }
}
