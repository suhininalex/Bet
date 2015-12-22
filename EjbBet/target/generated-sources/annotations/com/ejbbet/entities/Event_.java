package com.ejbbet.entities;

import com.ejbbet.entities.Companyuser;
import com.ejbbet.entities.Outcome;
import com.ejbbet.entities.Payment;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-12-22T18:39:05")
@StaticMetamodel(Event.class)
public class Event_ { 

    public static volatile SingularAttribute<Event, Date> expirationtime;
    public static volatile SingularAttribute<Event, Companyuser> idCompany;
    public static volatile CollectionAttribute<Event, Outcome> outcomeCollection;
    public static volatile SingularAttribute<Event, Integer> idEvent;
    public static volatile SingularAttribute<Event, String> description;
    public static volatile SingularAttribute<Event, Payment> payment;
    public static volatile SingularAttribute<Event, Integer> status;

}