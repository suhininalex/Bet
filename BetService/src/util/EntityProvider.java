package util;

import logic.BusinessFactories;

/**
 * Is not thread-safe!
 * @author llama
 */
public class EntityProvider {
    private static BusinessFactories businessFactories = null;
    
    public static void setBusinessFactoris(BusinessFactories factories){
        businessFactories = factories;
    }
    
    public static synchronized BusinessFactories getBusinessFactories(){
        if (businessFactories==null) throw new IllegalStateException("Provider is not initialized!");
        return businessFactories;
    }
}
