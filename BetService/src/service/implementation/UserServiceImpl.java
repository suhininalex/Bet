package service.implementation;

import database.EventDB;
import database.OutcomeDB;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import logic.Bet;
import logic.Event;
import logic.Outcome;
import logic.SelfUser;
import service.Session;
import util.EntityProvider;
import util.MySqlUtil;

public class UserServiceImpl implements service.UserService{

    @Override
    public Map<String,Object> getProfile(Session session) throws RemoteException {
        SelfUser selfUser = EntityProvider.getBusinessFactories().getSelfUserInstance(MySqlUtil.getConnection());
        selfUser.login(session.logname, session.password);
        Map<String,Object> profile = new HashMap<>();
        profile.put("logname", selfUser.getLogname());
        profile.put("id", selfUser.getId());
        profile.put("fullname", selfUser.getFullname());
        profile.put("balance", selfUser.getBalance());
        return profile;
    }

    @Override
    public List<Map<String, Object>> getBets(Session session) throws RemoteException {
        List<Map<String, Object>> result = new LinkedList<>();
        SelfUser selfUser = EntityProvider.getBusinessFactories().getSelfUserInstance(MySqlUtil.getConnection());
        selfUser.login(session.logname, session.password);
        List<Bet> bets = selfUser.getBets();
        for (Bet bet : bets) {
            Map<String, Object> resultBet = new HashMap<>();
            resultBet.put("id", bet.getId());
            resultBet.put("status", bet.getStatus().toString());
            resultBet.put("amount", bet.getAmount());
            resultBet.put("k", bet.getK());
            Outcome outcome = bet.getOutcome();
            resultBet.put("event", outcome.getEvent().getDescription());
            resultBet.put("outcome", outcome.getName());
            result.add(resultBet);
        }
        return result;
    }

    @Override
    public List<Map<String, Object>> getOpenEvents() throws RemoteException {
        List<Map<String, Object>> result = new LinkedList<>();
        List<Event> events = EventDB.getAllOpenEvents(MySqlUtil.getConnection());
        for (Event event : events){
            Map<String, Object> buf = new HashMap<>();
            buf.put("id", event.getId());
            buf.put("expires", event.getExpirationTime());
            buf.put("description", event.getDescription());
            buf.put("company", event.getCompanyUser().getCompanyName());
            
            List<Map<String, Object>> outcomes = new LinkedList<>();
            buf.put("outcomes", outcomes);
            for (Outcome outcome : event.getOutcomes()){
                Map<String, Object> outcomeMap = new HashMap<>();
                outcomeMap.put("name", outcome.getName());
                outcomeMap.put("k", outcome.getCurrentK());
                outcomeMap.put("id", outcome.getId());
                outcomes.add(outcomeMap);
            }
            result.add(buf);
        }
        return result;
    }

    @Override
    public void createBet(Session session, long idOutcome, double amount) throws RemoteException {
        Connection connection = MySqlUtil.getConnection();
        try {
            connection.setAutoCommit(false);
        } catch (SQLException ex) {
            throw new IllegalStateException("Something wrong with database!",ex);
        }
        SelfUser selfUser = EntityProvider.getBusinessFactories().getSelfUserInstance(connection);
        selfUser.login(session.logname, session.password);
        Outcome outcome = OutcomeDB.getOutcome(MySqlUtil.getConnection(), idOutcome);
        selfUser.createBet(outcome, amount);
        try {
            connection.commit();
        } catch (SQLException ex) {
            throw new IllegalStateException("Something wrong with database!",ex);
        }
    }

    @Override
    public void deposit(Session session, double amount) throws RemoteException {
        SelfUser selfUser = EntityProvider.getBusinessFactories().getSelfUserInstance(MySqlUtil.getConnection());
        selfUser.login(session.logname, session.password);
        selfUser.deposit(amount);
     }

    @Override
    public void withdraw(Session session, double amount) throws RemoteException {
        SelfUser selfUser = EntityProvider.getBusinessFactories().getSelfUserInstance(MySqlUtil.getConnection());
        selfUser.login(session.logname, session.password);
        selfUser.withdraw(amount);
     }

    @Override
    public void createNew(String logname, String fullname, String password) throws RemoteException {
        SelfUser selfUser = EntityProvider.getBusinessFactories().getSelfUserInstance(MySqlUtil.getConnection());
        selfUser.setLogname(logname);
        selfUser.setPassword(password);
        selfUser.setFullname(fullname);
        selfUser.save();
    }
}
