package com.ejbbet.authorization;

import java.io.Serializable;


public abstract class AuthorizationBean implements Serializable {
   
    private String username = "";
    private String password = "";
    
    private boolean isSignIn = false;

    public AuthorizationBean() {
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
    
    public String action(){
        isSignIn = checkAuthorization();
        return (isSignIn) ? "index.xhtml" : "login.xhtml";
    }

    public boolean isIsSignIn() {
        return isSignIn;
    }
    
    protected abstract boolean checkAuthorization();
}
