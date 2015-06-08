package service.implementation;

import database.EventDB;
import database.OutcomeDB;
import database.PaymentsDB;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import logic.Event;
import logic.Outcome;
import logic.OwnerUser;
import logic.Payments;
import service.AdminService;
import service.Session;
import util.EntityProvider;
import util.MySqlUtil;

public class AdminServiceImpl implements AdminService{
    @Override
    public boolean authorize(Session session) throws RemoteException {
        try (Connection connection = MySqlUtil.getConnection()) {
            OwnerUser ownerUser = EntityProvider.getBusinessFactories().getOwnerUserInstance(connection);
            ownerUser.login(session.logname, session.password);
            return true;
        } catch (SQLException ex) {
            throw new IllegalStateException("Something wrong with database!",ex);
        }
    }

    @Override
    public List<Map<String, Object>> getOpenPayments(Session session) throws RemoteException {
        try (Connection connection = MySqlUtil.getConnection()) {
            List<Map<String, Object>> result = new LinkedList<>();
            OwnerUser ownerUser = EntityProvider.getBusinessFactories().getOwnerUserInstance(connection);
            ownerUser.login(session.logname, session.password);
            List<Payments> payments = ownerUser.getOpenPayments();
            for (Payments payment : payments){
                Map<String,Object> paymentMap = new HashMap<>();
                Outcome winner = payment.getWinnerOutcome();
                Event event = payment.getEvent();
                paymentMap.put("id", payment.getId());
                paymentMap.put("status", payment.getStatus().toString());
                paymentMap.put("winner", winner.getName());
                paymentMap.put("winnerId", winner.getId());
                paymentMap.put("event", payment.getEvent().getDescription());
                paymentMap.put("eventId", event.getId());
                result.add(paymentMap);
            }
            return result;
        } catch (SQLException ex) {
            throw new IllegalStateException("Something wrong with database!",ex);
        }
    }

    @Override
    public List<Map<String, Object>> getEventInfo(Session session, long id) throws RemoteException {
        try (Connection connection = MySqlUtil.getConnection()) {
             OwnerUser ownerUser = EntityProvider.getBusinessFactories().getOwnerUserInstance(connection);
            ownerUser.login(session.logname, session.password);
            
            Event event = EventDB.getEvent(connection, id);
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
    public void changeWinner(Session session, long outcomeId) throws RemoteException {
        try (Connection connection = MySqlUtil.getConnection()) {
            OwnerUser ownerUser = EntityProvider.getBusinessFactories().getOwnerUserInstance(connection);
            ownerUser.login(session.logname, session.password);
            Outcome outcome = OutcomeDB.getOutcome(connection, outcomeId);
            Payments payment = PaymentsDB.getPaymentByEventID(connection, outcome.getEvent().getId());
            payment.setWinnerOutcome(outcome);
            payment.save();
        } catch (SQLException ex) {
            throw new IllegalStateException("Something wrong with database!",ex);
        }
    }

    @Override
    public void payAll(Session session, long paymentId) throws RemoteException {
        try (Connection connection = MySqlUtil.getConnection()) {
            connection.setAutoCommit(false);
            OwnerUser ownerUser = EntityProvider.getBusinessFactories().getOwnerUserInstance(connection);
            ownerUser.login(session.logname, session.password);
            Payments payment = PaymentsDB.getPayment(connection, paymentId);
            payment.payAll();
            connection.commit();
        } catch (SQLException ex) {
            throw new IllegalStateException("Something wrong with database!",ex);
        }    
    }
    
    
}
