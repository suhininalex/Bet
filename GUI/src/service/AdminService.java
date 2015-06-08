package service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

public interface AdminService extends Remote{
    boolean authorize(Session session) throws RemoteException;
    
    List<Map<String,Object>> getOpenPayments(Session session) throws RemoteException;
    
    List<Map<String,Object>> getEventInfo(Session session, long id) throws RemoteException;
    
    void changeWinner(Session session, long outcomeId) throws RemoteException;
    
    void payAll(Session session, long paymentId) throws RemoteException;
}
