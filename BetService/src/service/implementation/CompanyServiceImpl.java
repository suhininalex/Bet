package service.implementation;

import database.EventDB;
import database.OutcomeDB;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import logic.CompanyUser;
import logic.Event;
import logic.Outcome;
import service.CompanyService;
import service.OutcomeDef;
import service.Session;
import util.EntityProvider;
import util.MySqlUtil;

public class CompanyServiceImpl implements CompanyService{

    @Override
    public Map<String, Object> getProfile(Session session) throws RemoteException {
        try (Connection connection = MySqlUtil.getConnection()) {
            CompanyUser companyUser = EntityProvider.getBusinessFactories().getCompanyUserInstance(connection);
            companyUser.login(session.logname, session.password);
            Map<String,Object> profile = new HashMap<>();
            profile.put("logname", companyUser.getLogname());
            profile.put("id", companyUser.getId());
            profile.put("companyName", companyUser.getCompanyName());
            profile.put("balance", companyUser.getBalance());
            return profile;
        } catch (SQLException ex) {
            throw new IllegalStateException("Something wrong with database!",ex);
        }
    }
    
    @Override
    public void deposit(Session session, double amount) throws RemoteException {
        try (Connection connection = MySqlUtil.getConnection()) {
            CompanyUser companyUser = EntityProvider.getBusinessFactories().getCompanyUserInstance(connection);
            companyUser.login(session.logname, session.password);
            companyUser.deposit(amount);
        } catch (SQLException ex) {
            throw new IllegalStateException("Something wrong with database!",ex);
        }
     }

    @Override
    public void withdraw(Session session, double amount) throws RemoteException {
        try (Connection connection = MySqlUtil.getConnection()) {
            CompanyUser companyUser = EntityProvider.getBusinessFactories().getCompanyUserInstance(connection);
            companyUser.login(session.logname, session.password);
            companyUser.withdraw(amount);
        } catch (SQLException ex) {
            throw new IllegalStateException("Something wrong with database!",ex);
        }
     }

    @Override
    public void createNew(String logname, String fullname, String password) throws RemoteException {
        try (Connection connection = MySqlUtil.getConnection()) {
            CompanyUser companyUser = EntityProvider.getBusinessFactories().getCompanyUserInstance(connection);
            companyUser.setLogname(logname);
            companyUser.setPassword(password);
            companyUser.setCompanyName(fullname);
            companyUser.save();
        } catch (SQLException ex) {
            throw new IllegalStateException("Something wrong with database!",ex);
        }
    }

    @Override
    public List<Map<String, Object>> getOpenEvents(Session session) throws RemoteException {
        try (Connection connection = MySqlUtil.getConnection()) {
            CompanyUser companyUser = EntityProvider.getBusinessFactories().getCompanyUserInstance(connection);
            companyUser.login(session.logname, session.password);
            List<Event> events = companyUser.getOpenEvents();
            List<Map<String, Object>> result = new LinkedList<>();
            for (Event event : events){
                Map<String, Object> buf = new HashMap<>();
                buf.put("id", event.getId());
                buf.put("expires", event.getExpirationTime());
                buf.put("description", event.getDescription());
                buf.put("status", event.getStatus().toString());
                result.add(buf);
            }
            return result;  
        } catch (SQLException ex) {
            throw new IllegalStateException("Something wrong with database!",ex);
        }
    }

    @Override
    public void createEvent(Session session, String description, long expiresAfterHours, OutcomeDef[] outcomes) throws RemoteException {
        try (Connection connection = MySqlUtil.getConnection()) {
            connection.setAutoCommit(false);
            CompanyUser companyUser = EntityProvider.getBusinessFactories().getCompanyUserInstance(connection);
            companyUser.login(session.logname, session.password);
            Event event = companyUser.createEvent(description, new Date(System.currentTimeMillis()+1000*3600*expiresAfterHours));
            for (OutcomeDef def : outcomes){
                event.addOutcome(def.name, def.k);
            }
            connection.commit();
        } catch (SQLException ex) {
            throw new IllegalStateException("Something wrong with database!",ex);
        }
    }

    @Override
    public List<Map<String, Object>> getEventInfo(Session session, long id) throws RemoteException {
        try (Connection connection = MySqlUtil.getConnection()) {
            CompanyUser companyUser = EntityProvider.getBusinessFactories().getCompanyUserInstance(connection);
            companyUser.login(session.logname, session.password);

            Event event = EventDB.getEvent(connection, id);
            if (event.getCompanyUser().getId()!=companyUser.getId()) 
                throw new IllegalAccessError("Is this your event?");

            List<Map<String, Object>> outcomes = new LinkedList<>();
            for (Outcome outcome : event.getOutcomes()){
                Map<String, Object> outcomeMap = new HashMap<>();
                outcomeMap.put("name", outcome.getName());
                outcomeMap.put("k", outcome.getCurrentK());
                outcomeMap.put("id", outcome.getId());
                outcomeMap.put("risk", outcome.riskAssesment());
                outcomes.add(outcomeMap);
            }
            return outcomes;
        } catch (SQLException ex) {
            throw new IllegalStateException("Something wrong with database!",ex);
        }
    }

    @Override
    public void setWinner(Session session, long idOutcome) throws RemoteException {
        try (Connection connection = MySqlUtil.getConnection()) {
            connection.setAutoCommit(false);
            Outcome outcome = OutcomeDB.getOutcome(connection, idOutcome);
            CompanyUser companyUser = EntityProvider.getBusinessFactories().getCompanyUserInstance(connection);
            companyUser.login(session.logname, session.password);
            Event event = outcome.getEvent();
            if (event.getCompanyUser().getId()!=companyUser.getId()) 
                throw new IllegalAccessError("Is this your outcome?");
            event.setWinner(outcome);
            connection.commit();
        } catch (SQLException ex) {
            throw new IllegalStateException("Something wrong with database!",ex);
        }
    }
}
