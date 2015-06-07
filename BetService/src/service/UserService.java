package service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

public interface UserService extends Remote{
    
    Map<String,Object> getProfile(Session session) throws RemoteException;
    
    List<Map<String, Object>> getBets(Session session) throws RemoteException;
    
    List<Map<String, Object>> getOpenEvents() throws RemoteException;
    
    void createBet(Session session, long idOutcome, double amount) throws RemoteException;
    
    void deposit(Session session, double amount) throws RemoteException;
    
    void withdraw(Session session, double amount) throws RemoteException;
    
    void createNew(String name, String fullname, String password) throws RemoteException;
        
}
