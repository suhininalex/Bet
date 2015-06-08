package betservice;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import service.AdminService;
import service.CompanyService;
import service.UserService;
import service.implementation.AdminServiceImpl;
import service.implementation.CompanyServiceImpl;
import service.implementation.UserServiceImpl;
import util.DBBusinessFactories;
import util.EntityProvider;

public class BetService {

    private final static String USER_BIND = "bet/user";
    private final static String COMPANY_BIND = "bet/company";
    private final static String ADMIN_BIND = "bet/admin";
    
    public static void main(String[] args) throws RemoteException, AlreadyBoundException {
        EntityProvider.setBusinessFactoris(new DBBusinessFactories());
        
        /*RMI connection */
           System.out.print("Starting registry...");
           Registry registry = LocateRegistry.createRegistry(2099);
           System.out.println(" OK");
           
           UserService userService = new UserServiceImpl();
           UserService userStub = (UserService) UnicastRemoteObject.exportObject(userService, 0);
           System.out.print("Binding service "+ USER_BIND + "...");
           registry.bind(USER_BIND, userStub);
           System.out.println(" OK");
           
           CompanyService companyService = new CompanyServiceImpl();
           CompanyService companyStub = (CompanyService) UnicastRemoteObject.exportObject(companyService, 0);
           System.out.print("Binding service "+ COMPANY_BIND + "...");
           registry.bind(COMPANY_BIND, companyStub);
           System.out.println(" OK");
           
           AdminService adminService = new AdminServiceImpl();
           AdminService adminStub = (AdminService) UnicastRemoteObject.exportObject(adminService, 0);
           System.out.print("Binding service "+ ADMIN_BIND + "...");
           registry.bind(ADMIN_BIND, adminStub);
           System.out.println(" OK");
    }
}
