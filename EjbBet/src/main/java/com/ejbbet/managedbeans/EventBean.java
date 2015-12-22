package com.ejbbet.managedbeans;

import com.ejbbet.entities.Event;
import com.ejbbet.sessionbeans.EventFacade;
import java.util.Collection;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "eventBean")
@ViewScoped
public class EventBean {

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
   
    @EJB
    private EventFacade eventFacade;
    
    public EventBean() {
    }
    
    public Event getEventBean(){
        return eventFacade.find(id);
    }
    
    public Collection<Event> getAllEvents(){
        return eventFacade.findAll();
    }
    
    public Collection<Event> getOpenEvents(){
        return eventFacade.getAllAccessableEvents();
    }
}
