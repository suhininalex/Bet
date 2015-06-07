package service;

import java.io.Serializable;

public class OutcomeDef implements Serializable{
    public final double k;
    public final String name;

    public OutcomeDef(double k, String name) {
        this.k = k;
        this.name = name;
    }
  
}
