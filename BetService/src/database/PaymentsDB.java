package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import logic.Event;
import logic.Outcome;
import logic.Payments;
import util.MySqlUtil;

public class PaymentsDB extends Payments{

    Long idEvent;
    Long idWinnerOutcome;
    
    @Override
    protected void setEvent(Event event) {
        idEvent = event.getId();
    }

    @Override
    public Event getEvent() {
        return EventDB.getEvent(MySqlUtil.extractConnection(this), idEvent);
    }

    @Override
    protected void setWinnerOutcome(Outcome outcome) {
        idWinnerOutcome = outcome.getId();
    }

    @Override
    protected Outcome getWinnerOutcome() {
        return OutcomeDB.getOutcome(MySqlUtil.extractConnection(this), idWinnerOutcome);
    }

    private final String preparedSave = "INSERT INTO PAYMENT (ID_PAYMENT, STATUS, ID_WINNEROUTCOME, ID_EVENT)" +
            "VALUES (?, ?, ?, ?)" +
            "ON DUPLICATE KEY UPDATE STATUS=?, ID_WINNEROUTCOME=?, ID_EVENT=?";
    @Override
    public void save() {
        try {
            PreparedStatement prepared = MySqlUtil.extractConnection(this).prepareStatement(preparedSave, Statement.RETURN_GENERATED_KEYS);
            if (getId()!=null) prepared.setLong(1, getId());
            else prepared.setNull(1, Types.INTEGER);
            prepared.setInt(2, getStatus().getCode());
            prepared.setLong(3, idWinnerOutcome);
            prepared.setLong(4, idEvent);
            prepared.setInt(5, getStatus().getCode());
            prepared.setLong(6, idWinnerOutcome);
            prepared.setLong(7, idEvent);
            prepared.execute();
            ResultSet rs = prepared.getGeneratedKeys();
            if (rs.next())
                setId(rs.getLong(1));
        } catch (SQLException ex) {
            throw new IllegalStateException("Can not save payment entity!", ex);
        }
    }
    
    void load(ResultSet resultSet){
        try {
            this.setId(resultSet.getLong("ID_PAYMENT"));
            this.setStatus(Payments.Status.getFromCode(resultSet.getInt("STATUS")));
            idWinnerOutcome = resultSet.getLong("ID_WINNEROUTCOME");
            idEvent = resultSet.getLong("ID_EVENT");
        } catch (SQLException ex) {
            throw new IllegalArgumentException("Can not payment entity!", ex);
        } 
    }
}
