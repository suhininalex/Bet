package database;

import java.sql.Connection;
import java.sql.ResultSet;
import util.StorableInDB;

public class OutcomeDB implements StorableInDB{

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
    public void load(ResultSet resultSet) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void save() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
