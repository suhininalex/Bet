package com.ejbbet.managedbeans;

import com.ejbbet.entities.Payment;
import com.ejbbet.sessionbeans.PaymentFacade;
import java.util.Collection;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "paymentBean")
@ViewScoped
public class PaymentBean {

    @EJB
    private PaymentFacade paymentFacade;

    public PaymentBean() {
    }
    
    public Collection<Payment> getAllPayments(){
        return paymentFacade.findAll();
    }
    
    public Collection<Payment> getPayments(){
        if (getFilterStatus()!=null) 
            return getAllPayments().stream().filter(it -> it.getStatus()==getFilterStatus()).collect(Collectors.toList());
        else return getAllPayments();
    }
    
    private Payment.Status getFilterStatus(){
        switch (filter){
            case "Paid" : return Payment.Status.Paid;
            case "Waiting": return Payment.Status.Waiting;
            default: return null;
        }
    }
    
    public void action(){}
    
    String filter = "Waiting";

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }
    
    public boolean getIsWaitingFilter(){
        return getFilterStatus()==Payment.Status.Waiting;
    }
    
    public void approve(Payment payment){
        paymentFacade.approve(payment);
    }
    
    public void reject(Payment payment){
        paymentFacade.reject(payment);
    }
}
