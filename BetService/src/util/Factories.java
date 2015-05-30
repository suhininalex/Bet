
package util;

import logic.Bet;
import logic.Outcome;
import logic.Payments;

public class Factories {
    public static Factory<Bet> getBetFactory(){
        return () ->  {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        };
    }
    
    public static Factory<Outcome> getOutcomeFactory(){
        return () ->  {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        };
    }
    
    public static Factory<Payments> getPaymentsFactory(){
        return () ->  {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        };
    }
}
