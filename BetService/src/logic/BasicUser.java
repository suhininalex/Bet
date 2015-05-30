package logic;

import util.Storable;

public abstract class BasicUser implements Storable{
    String logname;
    String password;
    double balance=0;
    
    public void withdraw(double amount){
        if (getBalance()<amount) throw new IllegalArgumentException("Not enought money!");
        balance-=amount;
        save();
    }
    
    public void deposit(double amount){
        balance+=amount;
        save();
    }

    public double getBalance() {
        return balance;
    }
    
    public String getLogname() {
        return logname;
    }

    protected void setLogname(String logname) {
        this.logname = logname;
    }

    public String getPassword() {
        return password;
    }

    protected void setPassword(String password) {
        this.password = password;
    }

}
