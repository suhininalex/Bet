package logic;

import java.util.List;

public abstract class OwnerUser extends BasicUser {
    public boolean checkEvent(Event event){
        /* loong operation */
        return true;
    }
    
    public abstract List<Payments> getOpenPayments();
}
