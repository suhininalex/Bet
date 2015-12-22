package com.ejbbet.managedbeans;

import com.ejbbet.authorization.UserAuthorizationBean;
import com.ejbbet.entities.Bet;
import java.util.Collection;
import java.util.stream.Collectors;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class BetBean {
    
    @ManagedProperty(value="#{userAuthorizationBean}")
    UserAuthorizationBean userAuthorizationBean;

    public UserAuthorizationBean getUserAuthorizationBean() {
        return userAuthorizationBean;
    }

    public void setUserAuthorizationBean(UserAuthorizationBean userAuthorizationBean) {
        this.userAuthorizationBean = userAuthorizationBean;
    }
    
    private boolean filter = false;

    public boolean isFilter() {
        return filter;
    }

    public void setFilter(boolean filter) {
        this.filter = filter;
    }
    
    public BetBean() {} 
    
    public Collection<Bet> getBets(){
        if (filter) 
            return userAuthorizationBean.getUserBean().getBetCollection().stream().filter(bet -> bet.getStatus()==Bet.Status.Open).collect(Collectors.toList());
        else return userAuthorizationBean.getUserBean().getBetCollection();
    }

    public void action(){}
}
