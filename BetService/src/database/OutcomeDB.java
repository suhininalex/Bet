package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.LinkedList;
import java.util.List;
import logic.Bet;
import logic.Event;
import logic.Outcome;
import util.MySqlUtil;

public class OutcomeDB extends Outcome{
    Long eventId = null;
    
    public void load(ResultSet resultSet) {
        try {
            this.setId(resultSet.getLong("ID_OUTCOME"));
            this.setCurrentK(resultSet.getDouble("K"));
            eventId = resultSet.getLong("ID_EVENT");
            this.setName(resultSet.getString("NAME"));
        } catch (SQLException ex) {
            throw new IllegalArgumentException("Can not Outcome entity!", ex);
        } 
    }

    private final String preparedSave = "INSERT INTO OUTCOME (ID_OUTCOME, NAME, K, ID_EVENT)" +
            "VALUES (?, ?, ?, ?)" +
            "ON DUPLICATE KEY UPDATE NAME=?, K=?, ID_EVENT=?";
    @Override
    public void save() {
        try {
            PreparedStatement prepared = MySqlUtil.extractConnection(this).prepareStatement(preparedSave, Statement.RETURN_GENERATED_KEYS);
            if (getId()!=null) prepared.setLong(1, getId());
            else prepared.setNull(1, Types.INTEGER);
            prepared.setString(2, getName());
            prepared.setDouble(3, getCurrentK());
            prepared.setLong(4, eventId);
            prepared.setString(5, getName());
            prepared.setDouble(6, getCurrentK());
            prepared.setLong(7, eventId);
            prepared.execute();
            ResultSet rs = prepared.getGeneratedKeys();
            if (rs.next())
                setId(rs.getLong(1));
        } catch (SQLException ex) {
            throw new IllegalStateException("Can not save outcome entity!", ex);
        }
    }

    private final String preparedBets = "SELECT * FROM BET WHERE ID_OUTCOME=?";
    @Override
    public List<Bet> getAllBets() {
        try {
            List<Bet> outcomes = new LinkedList<>();
            PreparedStatement prepared = MySqlUtil.extractConnection(this).prepareStatement(preparedBets);
            prepared.setLong(1, getId());
            ResultSet rs = prepared.executeQuery();
            while (rs.next()) {
                BetDB bet = new BetDB();
                bet.setDataProvider(getDataProvider());
                bet.load(rs);
                outcomes.add(bet);
            }
            return outcomes;
        } catch (SQLException ex) {
            throw new IllegalStateException("Can not load events!", ex);
        }
    }

    
    @Override
    public Event getEvent() {
        return EventDB.getEvent(MySqlUtil.extractConnection(this), eventId);
    }

    @Override
    public void setEvent(Event event) {
        eventId = event.getId();
    }
    
    private static final String preparedOutcome = "SELECT * FROM OUTCOME WHERE ID_OUTCOME=?";
    public static Outcome getOutcome(Connection connection, Long id) {
        try {
            OutcomeDB outcome = new OutcomeDB();
            outcome.setDataProvider(connection);
            PreparedStatement prepared = connection.prepareCall(preparedOutcome);
            prepared.setLong(1, id);
            ResultSet rs = prepared.executeQuery();
            rs.next();
            outcome.load(rs);
            return outcome;
        } catch (SQLException ex) {
            throw new IllegalStateException("Can not load outcome entity!", ex);
        }
    }
    
}
