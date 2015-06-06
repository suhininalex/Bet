package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import logic.Bet;
import logic.SelfUser;
import util.StorableInDB;

public class SelfUserDB extends SelfUser implements StorableInDB{
    
    private final String preparedSave = "INSERT INTO SELFUSER (LOGNAME, PASSWORD, BALANCE, FULLNAME)" +
            "VALUES (?, ?, ?, ?)" +
            "ON DUPLICATE KEY UPDATE LOGNAME=?, PASSWORD=?, BALANCE=?, FULLNAME=?";
    @Override
    public void save() {
        try {
            PreparedStatement prepared = getConnectionToUse().prepareStatement(preparedSave, Statement.RETURN_GENERATED_KEYS);
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
            this.setId(resultSet.getLong("ID_COMPANY"));
            this.setFullname(resultSet.getString("FULLNAME"));
            this.setLogname(resultSet.getString("LOGNAME"));
            this.setPassword(resultSet.getString("PASSWORD"));
            this.setBalance(resultSet.getDouble("BALANCE"));
        } catch (SQLException ex) {
            throw new IllegalArgumentException("Can not load company entity!", ex);
        }
    }
    
    Connection connection = null;
    
    @Override
    public void setConnectionToUse(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Connection getConnectionToUse() {
        return connection;
    }

    @Override
    public void login(String logname, String password) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Bet> getBets() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
}
