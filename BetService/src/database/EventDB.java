package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.LinkedList;
import java.util.List;
import logic.CompanyUser;
import logic.Event;
import logic.Outcome;
import util.MySqlUtil;

public class EventDB extends Event {

    Long idCompany;

    final String preparedFind = "SELECT * FROM COMPANYUSER WHERE ID_COMPANY=?";
    @Override
    public CompanyUser getCompanyUser() {
        try {
            PreparedStatement prepared = MySqlUtil.extractConnection(this).prepareStatement(preparedFind);
            prepared.setLong(1, idCompany);
            ResultSet rs = prepared.executeQuery();
            rs.next();
            CompanyUserDB companyUser = new CompanyUserDB();
            companyUser.setDataProvider(getDataProvider());
            companyUser.load(rs);
            return companyUser;
        } catch (SQLException ex) {
            throw new IllegalStateException("Can not load user from event!", ex);
        }
    }

    @Override
    protected void setCompanyUser(CompanyUser companyUser) {
        idCompany = companyUser.getId();
    }

    private final String preparedOutcomes = "SELECT * FROM OUTCOME WHERE ID_EVENT=?";
    @Override
    public List<Outcome> getOutcomes() {
        try {
            List<Outcome> outcomes = new LinkedList<>();
            PreparedStatement prepared = MySqlUtil.extractConnection(this).prepareStatement(preparedOutcomes);
            prepared.setLong(1, getId());
            ResultSet rs = prepared.executeQuery();
            while (rs.next()) {
                OutcomeDB outcome = new OutcomeDB();
                outcome.setDataProvider(getDataProvider());
                outcome.load(rs);
                outcomes.add(outcome);
            }
            return outcomes;
        } catch (SQLException ex) {
            throw new IllegalStateException("Can not load events!", ex);
        }
    }

    private final String preparedSave = "INSERT INTO EVENT (ID_EVENT,DESCRIPTION, EXPIRATIONTIME, STATUS, ID_COMPANY)" +
            "VALUES (?, ?, ?, ?, ?)" +
            "ON DUPLICATE KEY UPDATE DESCRIPTION=?, EXPIRATIONTIME=?, STATUS=?, ID_COMPANY=?";
    @Override
    public void save() {
        try {
            PreparedStatement prepared = MySqlUtil.extractConnection(this).prepareStatement(preparedSave, Statement.RETURN_GENERATED_KEYS);
            if (getId()!=null) prepared.setLong(1, getId());
            else prepared.setNull(1, Types.INTEGER);
            prepared.setString(2, getDescription());
            prepared.setTimestamp(3, new Timestamp(getExpirationTime().getTime()));
            prepared.setInt(4, getStatus().getCode());
            prepared.setLong(5, idCompany);
            prepared.setString(6, getDescription());
            prepared.setTimestamp(7, new Timestamp(getExpirationTime().getTime()));
            prepared.setInt(8, getStatus().getCode());
            prepared.setLong(9, idCompany);
            prepared.execute();
            ResultSet rs = prepared.getGeneratedKeys();
            if (rs.next())
                setId(rs.getLong(1));
        } catch (SQLException ex) {
            throw new IllegalStateException("Can not save SelfUser entity!", ex);
        }
    }

    public void load(ResultSet resultSet) {
        try {
            this.setId(resultSet.getLong("ID_EVENT"));
            this.setStatus(Event.Status.getFromCode(resultSet.getInt("STATUS")));
            this.setExpirationTime(resultSet.getTimestamp("EXPIRATIONTIME"));
            this.idCompany = resultSet.getLong("ID_COMPANY");
            this.setDescription(resultSet.getString("DESCRIPTION"));
        } catch (SQLException ex) {
            throw new IllegalArgumentException("Can not event entity!", ex);
        }    
    } 
    
    private static final String preparedEvent = "SELECT * FROM EVENT WHERE ID_EVENT=?";
    public static Event getEvent(Connection connection, Long id){
        try {
            PreparedStatement prepared = connection.prepareStatement(preparedEvent);
            prepared.setLong(1, id);
            ResultSet rs = prepared.executeQuery();
            rs.next();
            EventDB event = new EventDB();
            event.setDataProvider(connection);
            event.load(rs);
            return event;
        } catch (SQLException ex) {
            throw new IllegalStateException("Can not load event entity!", ex);
        }
    }
    
    public static final String preparedOpenEvents = "SELECT * FROM EVENT WHERE EXPIRATIONTIME>CURRENT_TIMESTAMP AND STATUS="+Event.Status.Open.getCode();
    public static List<Event> getAllOpenEvents(Connection connection){
        try {
            List<Event> events = new LinkedList<>();
            PreparedStatement prepared = connection.prepareStatement(preparedOpenEvents);
            
            ResultSet rs = prepared.executeQuery();
            while (rs.next()) {
                EventDB event = new EventDB();
                event.setDataProvider(connection);
                event.load(rs);
                events.add(event);
            }
            return events;
        } catch (SQLException ex) {
            throw new IllegalStateException("Can not load events!", ex);
        }
    }
}
