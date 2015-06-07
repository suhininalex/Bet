package service.implementation;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;
import logic.CompanyUser;
import service.CompanyService;
import service.Session;
import util.EntityProvider;
import util.MySqlUtil;

public class CompanyServiceImpl implements CompanyService{

    @Override
    public Map<String, Object> getProfile(Session session) throws RemoteException {
        CompanyUser companyUser = EntityProvider.getBusinessFactories().getCompanyUserInstance(MySqlUtil.getConnection());
        companyUser.login(session.logname, session.password);
        Map<String,Object> profile = new HashMap<>();
        profile.put("logname", companyUser.getLogname());
        profile.put("id", companyUser.getId());
        profile.put("companyName", companyUser.getCompanyName());
        profile.put("balance", companyUser.getBalance());
        return profile;
    }
    
    @Override
    public void deposit(Session session, double amount) throws RemoteException {
        CompanyUser companyUser = EntityProvider.getBusinessFactories().getCompanyUserInstance(MySqlUtil.getConnection());
        companyUser.login(session.logname, session.password);
        companyUser.deposit(amount);
     }

    @Override
    public void withdraw(Session session, double amount) throws RemoteException {
        CompanyUser companyUser = EntityProvider.getBusinessFactories().getCompanyUserInstance(MySqlUtil.getConnection());
        companyUser.login(session.logname, session.password);
        companyUser.withdraw(amount);
     }

    @Override
    public void createNew(String logname, String fullname, String password) throws RemoteException {
        CompanyUser companyUser = EntityProvider.getBusinessFactories().getCompanyUserInstance(MySqlUtil.getConnection());
        companyUser.setLogname(logname);
        companyUser.setPassword(password);
        companyUser.setCompanyName(fullname);
        companyUser.save();
    }

}
