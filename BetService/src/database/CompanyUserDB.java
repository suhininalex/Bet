package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import logic.CompanyUser;
import logic.Event;
import util.MySqlUtil;

public class CompanyUserDB extends CompanyUser {

    private final String preparedSave = "INSERT INTO COMPANYUSER (LOGNAME, PASSWORD, BALANCE, FULLNAME)" +
            "VALUES (?, ?, ?, ?)" +
            "ON DUPLICATE KEY UPDATE LOGNAME=?, PASSWORD=?, BALANCE=?, FULLNAME=?";
    @Override
    public void save() {
        try {
            PreparedStatement prepared = MySqlUtil.extractConnection(this).prepareStatement(preparedSave, Statement.RETURN_GENERATED_KEYS);
            prepared.setString(1, getLogname());
            prepared.setString(2, getPassword());
            prepared.setDouble(3, getBalance());
            prepared.setString(4, getCompanyName());
            prepared.setString(5, getLogname());
            prepared.setString(6, getPassword());
            prepared.setDouble(7, getBalance());
            prepared.setString(8, getCompanyName());
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
            this.setCompanyName(resultSet.getString("FULLNAME"));
            this.setLogname(resultSet.getString("LOGNAME"));
            this.setPassword(resultSet.getString("PASSWORD"));
            this.setBalance(resultSet.getDouble("BALANCE"));
        } catch (SQLException ex) {
            throw new IllegalArgumentException("Can not load company entity!", ex);
        }
    }
    
    private final String preparedAuth = "SELECT * FROM COMPANYUSER WHERE LOGNAME=? AND PASSWORD=?";
    @Override
    public void login(String logname, String password){
        try {
            PreparedStatement prepared = MySqlUtil.extractConnection(this).prepareStatement(preparedAuth);
            prepared.setString(1, logname);
            prepared.setString(2, password);
            ResultSet rs = prepared.executeQuery();
            rs.next();
            load(rs);
        } catch (SQLException ex) {
            throw new IllegalStateException("Can not login as company user!", ex);
        }
    }

    private final String preparedEvents = "SELECT * FROM EVENT WHERE ID_COMPANY=?";
    @Override
    public List<Event> getEvents() {
        try {
            List<Event> events = new LinkedList<>();
            PreparedStatement prepared = MySqlUtil.extractConnection(this).prepareStatement(preparedEvents);
            prepared.setLong(1, getId());
            ResultSet rs = prepared.executeQuery();
            while (rs.next()) {
                EventDB event = new EventDB();
                event.setDataProvider(getDataProvider());
                event.load(rs);
                events.add(event);
            }
            return events;
        } catch (SQLException ex) {
            throw new IllegalStateException("Can not load events!", ex);
        }
    }    
}
