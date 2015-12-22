package com.ejbbet.managedbeans;

import com.ejbbet.sessionbeans.CompanyuserFacade;
import com.ejbbet.sessionbeans.SelfuserFacade;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named(value = "userSignup")
@RequestScoped
public class UserSignup {

    @EJB
    private CompanyuserFacade companyuserFacade;

    @EJB
    private SelfuserFacade selfuserFacade;

    String username;
    String password;
    String fullname;
    String usertype;

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
    
    public UserSignup() {
    }
    
    public String action(){
        if (signup()) {
            if ("user".equals(usertype)) return "user/login.xhtml";
            else if ("company".equals(usertype)) return "company/login.xhtml";
        } else {
            FacesContext.getCurrentInstance().addMessage("amount", new FacesMessage("Can't create user. Try another username."));
        }
        return null;
    }
    
    private boolean signup(){
        if ("user".equals(usertype)) 
            return selfuserFacade.createUser(username, fullname, password);
        else if ("company".equals(usertype))
            return companyuserFacade.createUser(username, fullname, password);
        else return false;
    }
}
