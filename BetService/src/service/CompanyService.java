package service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

public interface CompanyService extends Remote{
    Map<String, Object> getProfile(Session session) throws RemoteException;
    
    void deposit(Session session, double amount) throws RemoteException;
    
    void withdraw(Session session, double amount) throws RemoteException;
    
    void createNew(String name, String fullname, String password) throws RemoteException;
       
    List<Map<String, Object>> getOpenEvents(Session session) throws RemoteException;
    
    void createEvent(Session session, String description, long expiresAfterHours, OutcomeDef [] outcomes)  throws RemoteException;
   
    List<Map<String, Object>> getEventInfo(Session session, long id) throws RemoteException;
    
    void setWinner(Session session, long idOutcome) throws RemoteException;
}
