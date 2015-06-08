package util;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import service.AdminService;
import service.CompanyService;
import service.UserService;

public class RemoteProvider {
    public static UserService getUserService(){
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 2099);
            return (UserService) registry.lookup("bet/user");
        } catch (RemoteException | NotBoundException ex) {
            throw new IllegalStateException("Can not get remote registry!", ex);
        }
    }
    
    public static CompanyService getCompanyService(){
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 2099);
            return (CompanyService) registry.lookup("bet/company");
        } catch (RemoteException | NotBoundException ex) {
            throw new IllegalStateException("Can not get remote registry!", ex);
        }
    }
    
    public static AdminService getAdminService(){
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 2099);
            return (AdminService) registry.lookup("bet/admin");
        } catch (RemoteException | NotBoundException ex) {
            throw new IllegalStateException("Can not get remote registry!", ex);
        }
    }
}
