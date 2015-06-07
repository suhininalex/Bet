package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import logic.Bet;
import logic.Outcome;
import logic.SelfUser;
import util.MySqlUtil;

public class BetDB extends Bet{
           
    long userId;
    long outcomeId;

    private final String preparedUser = "SELECT * FROM SELFUSER WHERE ID_USER=?";
    @Override
    public SelfUser getUser() {
        try {
            SelfUserDB selfUser = new SelfUserDB();
            selfUser.setDataProvider(getDataProvider());
            PreparedStatement prepared = MySqlUtil.extractConnection(this).prepareCall(preparedUser);
            prepared.setLong(1, userId);
            ResultSet rs = prepared.executeQuery();
            rs.next();
            selfUser.load(rs);
            return selfUser;
        } catch (SQLException ex) {
            throw new IllegalStateException("Can not load selfuser entity!", ex);
        }
    }

    @Override
    public void setUser(SelfUser user) {
        userId = user.getId();
    }

    @Override
    public Outcome getOutcome() {
        return OutcomeDB.getOutcome(MySqlUtil.extractConnection(this), outcomeId);
    }

    @Override
    public void setOutcome(Outcome outcome) {
        outcomeId = outcome.getId();
    }

    private final String preparedSave = "INSERT INTO BET (ID_BET, AMOUNT, STATUS, K, USER, ID_OUTCOME)" +
            "VALUES (?, ?, ?, ?, ?, ?)" +
            "ON DUPLICATE KEY UPDATE AMOUNT=?, STATUS=?, K=?, USER=?, ID_OUTCOME=?";
    @Override
    public void save() {
        try {
            PreparedStatement prepared = MySqlUtil.extractConnection(this).prepareStatement(preparedSave, Statement.RETURN_GENERATED_KEYS);
            if (getId()!=null) prepared.setLong(1, getId());
            else prepared.setNull(1, Types.INTEGER);
            prepared.setDouble(2, getAmount());
            prepared.setInt(3, getStatus().getCode());
            prepared.setDouble(4, getK());
            prepared.setLong(5, userId);
            prepared.setLong(6, outcomeId);
            prepared.setDouble(7, getAmount());
            prepared.setInt(8, getStatus().getCode());
            prepared.setDouble(9, getK());
            prepared.setLong(10, userId);
            prepared.setLong(11, outcomeId);
            prepared.execute();
            ResultSet rs = prepared.getGeneratedKeys();
            if (rs.next())
                setId(rs.getLong(1));
        } catch (SQLException ex) {
            throw new IllegalStateException("Can not save bet entity!", ex);
        }
    }

    public void load(ResultSet resultSet) {
        try {
            this.setId(resultSet.getLong("ID_BET"));
            this.setK(resultSet.getDouble("K"));
            outcomeId = resultSet.getLong("ID_OUTCOME");
            userId =resultSet.getLong("USER");
            this.setAmount(resultSet.getDouble("amount"));
            this.setStatus(Bet.Status.getFromCode(resultSet.getInt("STATUS")));
        } catch (SQLException ex) {
            throw new IllegalArgumentException("Can not load bet entity!", ex);
        }
    }
}
