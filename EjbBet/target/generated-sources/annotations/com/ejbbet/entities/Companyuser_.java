package com.ejbbet.entities;

import com.ejbbet.entities.Event;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-12-22T18:39:05")
@StaticMetamodel(Companyuser.class)
public class Companyuser_ { 

    public static volatile SingularAttribute<Companyuser, String> password;
    public static volatile CollectionAttribute<Companyuser, Event> eventCollection;
    public static volatile SingularAttribute<Companyuser, Integer> idCompany;
    public static volatile SingularAttribute<Companyuser, Float> balance;
    public static volatile SingularAttribute<Companyuser, String> logname;
    public static volatile SingularAttribute<Companyuser, String> fullname;

}