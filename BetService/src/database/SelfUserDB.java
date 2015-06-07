package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import logic.Bet;
import logic.SelfUser;
import util.MySqlUtil;

public class SelfUserDB extends SelfUser{
    
    private final String preparedSave = "INSERT INTO SELFUSER (LOGNAME, PASSWORD, BALANCE, FULLNAME)" +
            "VALUES (?, ?, ?, ?)" +
            "ON DUPLICATE KEY UPDATE LOGNAME=?, PASSWORD=?, BALANCE=?, FULLNAME=?";
    @Override
    public void save() {
        try {
            PreparedStatement prepared = MySqlUtil.extractConnection(this).prepareStatement(preparedSave, Statement.RETURN_GENERATED_KEYS);
            prepared.setString(1, getLogname());
            prepared.setString(2, getPassword());
            prepared.setDouble(3, getBalance());
            prepared.setString(4, getFullname());
            prepared.setString(5, getLogname());
            prepared.setString(6, getPassword());
            prepared.setDouble(7, getBalance());
            prepared.setString(8, getFullname());
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
            this.setId(resultSet.getLong("ID_USER"));
            this.setFullname(resultSet.getString("FULLNAME"));
            this.setLogname(resultSet.getString("LOGNAME"));
            this.setPassword(resultSet.getString("PASSWORD"));
            this.setBalance(resultSet.getDouble("BALANCE"));
        } catch (SQLException ex) {
            throw new IllegalArgumentException("Can not load selfuser entity!", ex);
        }
    }

    private final String preparedLogin = "SELECT * FROM SELFUSER WHERE LOGNAME=? AND PASSWORD=?";
    @Override
    public void login(String logname, String password) {
        try {
            PreparedStatement prepared = MySqlUtil.extractConnection(this).prepareStatement(preparedLogin);
            prepared.setString(1, logname);
            prepared.setString(2, password);
            ResultSet rs = prepared.executeQuery();
            rs.next();
            load(rs);
        } catch (SQLException ex) {
            throw new IllegalArgumentException("Can not login as "+logname+":"+password, ex);
        }
    }

    private final String preparedBets = "SELECT * FROM BET WHERE USER=?";
    @Override
    public List<Bet> getBets() {
        try {
            List<Bet> bets = new LinkedList<>();
            PreparedStatement prepared = MySqlUtil.extractConnection(this).prepareStatement(preparedBets);
            prepared.setLong(1, getId());
            ResultSet rs = prepared.executeQuery();
            while (rs.next()) {
                BetDB bet = new BetDB();
                bet.setDataProvider(getDataProvider());
                bet.load(rs);
                bets.add(bet);
            }
            return bets;
        } catch (SQLException ex) {
            throw new IllegalStateException("Can not load events!", ex);
        }
    }
   
}
