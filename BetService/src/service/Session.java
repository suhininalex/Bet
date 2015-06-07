package service;

import java.io.Serializable;

public class Session implements Serializable {
    public final String logname;
    public final String password;

    public Session(String logname, String password) {
        this.logname = logname;
        this.password = password;
    }
    
    
}
