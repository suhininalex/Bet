package database;

import java.sql.Connection;
import java.sql.ResultSet;
import logic.Bet;
import logic.Outcome;
import logic.SelfUser;
import util.StorableInDB;

public class BetDB extends Bet implements StorableInDB{
    
    Connection connection = null;
    
    @Override
    public void setConnectionToUse(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Connection getConnectionToUse() {
        return connection;
    }
    
    long userId;
    long outcomeId;

    @Override
    public SelfUser getUser() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setUser(SelfUser user) {
        userId = user.getId();
    }

    @Override
    public Outcome getOutcome() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setOutcome(Outcome outcome) {
        outcomeId = outcome.getId();
    }

    @Override
    public void save() {
        
    }

    @Override
    public void load(ResultSet resultSet) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
