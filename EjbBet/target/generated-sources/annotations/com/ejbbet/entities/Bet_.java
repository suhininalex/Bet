package com.ejbbet.entities;

import com.ejbbet.entities.Outcome;
import com.ejbbet.entities.Selfuser;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-12-22T18:39:05")
@StaticMetamodel(Bet.class)
public class Bet_ { 

    public static volatile SingularAttribute<Bet, Integer> idBet;
    public static volatile SingularAttribute<Bet, Float> amount;
    public static volatile SingularAttribute<Bet, Float> k;
    public static volatile SingularAttribute<Bet, Outcome> idOutcome;
    public static volatile SingularAttribute<Bet, Selfuser> user;
    public static volatile SingularAttribute<Bet, Integer> status;

}