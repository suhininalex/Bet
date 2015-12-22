package com.ejbbet.entities;

import com.ejbbet.entities.Bet;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-12-22T18:39:05")
@StaticMetamodel(Selfuser.class)
public class Selfuser_ { 

    public static volatile SingularAttribute<Selfuser, Integer> idUser;
    public static volatile SingularAttribute<Selfuser, String> password;
    public static volatile SingularAttribute<Selfuser, Float> balance;
    public static volatile SingularAttribute<Selfuser, String> logname;
    public static volatile CollectionAttribute<Selfuser, Bet> betCollection;
    public static volatile SingularAttribute<Selfuser, String> fullname;

}