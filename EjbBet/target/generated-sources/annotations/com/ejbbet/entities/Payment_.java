package com.ejbbet.entities;

import com.ejbbet.entities.Event;
import com.ejbbet.entities.Outcome;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-12-22T18:39:05")
@StaticMetamodel(Payment.class)
public class Payment_ { 

    public static volatile SingularAttribute<Payment, Integer> idPayment;
    public static volatile SingularAttribute<Payment, Outcome> idWinneroutcome;
    public static volatile SingularAttribute<Payment, Event> idEvent;
    public static volatile SingularAttribute<Payment, Integer> status;

}