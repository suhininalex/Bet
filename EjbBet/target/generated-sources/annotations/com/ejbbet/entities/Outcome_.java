package com.ejbbet.entities;

import com.ejbbet.entities.Bet;
import com.ejbbet.entities.Event;
import com.ejbbet.entities.Payment;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-12-22T18:39:05")
@StaticMetamodel(Outcome.class)
public class Outcome_ { 

    public static volatile SingularAttribute<Outcome, String> name;
    public static volatile CollectionAttribute<Outcome, Bet> betCollection;
    public static volatile SingularAttribute<Outcome, Event> idEvent;
    public static volatile SingularAttribute<Outcome, Float> k;
    public static volatile SingularAttribute<Outcome, Integer> idOutcome;
    public static volatile CollectionAttribute<Outcome, Payment> paymentCollection;

}