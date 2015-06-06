package logic;

import util.Storable;

public abstract class BasicUser extends WithDataProvider implements Storable{
    String logname;
    String password;
    double balance=0;
    Long id = null;

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
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

    public void setLogname(String logname) {
        this.logname = logname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public abstract void login(String logname, String password);

}
