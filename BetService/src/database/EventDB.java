package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
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
            CompanyUserDB companyUser = new CompanyUserDB();
            companyUser.load(prepared.executeQuery());
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

    private final String preparedSave = "INSERT INTO EVENT (DESCRIPTION, EXPIRATIONTIME, STATUS, ID_COMPANY)" +
            "VALUES (?, ?, ?, ?)" +
            "ON DUPLICATE KEY UPDATE DESCRIPTION=?, EXPIRATIONTIME=?, STATUS=?, ID_COMPANY=?";
    @Override
    public void save() {
        try {
            PreparedStatement prepared = MySqlUtil.extractConnection(this).prepareStatement(preparedSave, Statement.RETURN_GENERATED_KEYS);
            prepared.setString(1, getDescription());
            prepared.setTimestamp(2, new Timestamp(getExpirationTime().getTime()));
            prepared.setInt(3, getStatus().getCode());
            prepared.setLong(4, idCompany);
            prepared.setString(5, getDescription());
            prepared.setTimestamp(6, new Timestamp(getExpirationTime().getTime()));
            prepared.setInt(7, getStatus().getCode());
            prepared.setLong(8, idCompany);
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
}
