//package database;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.sql.Timestamp;
//import java.util.Date;
//import java.util.List;
//import logic.CompanyUser;
//import logic.Event;
//import logic.Outcome;
//import util.StorableInDB;
//
//public class EventDB extends Event implements StorableInDB {
//    Connection connection = null;
//    
//    Long idCompany;
//        
//    @Override
//    public void setConnectionToUse(Connection connection) {
//        this.connection = connection;
//    }
//
//    @Override
//    public Connection getConnectionToUse() {
//        return connection;
//    } 
//
//    final String preparedFind = "SELECT * FROM COMPANYUSER WHERE ID_COMPANY=?";
//    @Override
//    public CompanyUser getCompanyUser() {
//        try {
//            PreparedStatement prepared = getConnectionToUse().prepareStatement(preparedFind);
//            prepared.setLong(1, idCompany);
//            CompanyUserDB companyUser = new CompanyUserDB();
//            companyUser.load(prepared.executeQuery());
//            return companyUser;
//        } catch (SQLException ex) {
//            throw new IllegalStateException("Can not load user from event!", ex);
//        }
//    }
//
//    @Override
//    protected void setCompanyUser(CompanyUser companyUser) {
//        idCompany = companyUser.getId();
//    }
//
//    @Override
//    public List<Outcome> getOutcomes() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    private final String preparedSave = "INSERT INTO EVENT (DESCRIPTION, EXPIRATIONTIME, STATUS, ID_COMPANY)" +
//            "VALUES (?, ?, ?, ?)" +
//            "ON DUPLICATE KEY UPDATE DESCRIPTION=?, EXPIRATIONTIME=?, STATUS=?, ID_COMPANY=?";
//    @Override
//    public void save() {
//        try {
//            PreparedStatement prepared = getConnectionToUse().prepareStatement(preparedSave, Statement.RETURN_GENERATED_KEYS);
//            prepared.setString(1, getDescription());
//            prepared.setTimestamp(2, new Timestamp(getExpirationTime().getTime()));
//            prepared.setInt(3, getStatus().getCode());
//            prepared.setLong(4, idCompany);
//            prepared.setString(5, getDescription());
//            prepared.setTimestamp(6, new Timestamp(getExpirationTime().getTime()));
//            prepared.setInt(7, getStatus().getCode());
//            prepared.setLong(8, idCompany);
//            prepared.execute();
//            ResultSet rs = prepared.getGeneratedKeys();
//            if (rs.next())
//                setId(rs.getLong(1));
//        } catch (SQLException ex) {
//            throw new IllegalStateException("Can not save SelfUser entity!", ex);
//        }
//    }
//
//    @Override
//    public void load(ResultSet resultSet) {
//        try {
//            this.setId(resultSet.getLong("ID_EVENT"));
//            this.setStatus(Event.Status.getFromCode(resultSet.getInt("STATUS")));
//            this.setExpirationTime(resultSet.getTimestamp("EXPIRATIONTIME"));
//            this.idCompany = resultSet.getLong("ID_COMPANY");
//            this.setDescription(resultSet.getString("DESCRIPTION"));
//        } catch (SQLException ex) {
//            throw new IllegalArgumentException("Can not event entity!", ex);
//        }    
//    }
//
//    @Override
//    public void assignEvent(CompanyUser companyUser, String description, Date expires) {
//        setConnectionToUse(connection);
//        super.assignEvent(companyUser, description, expires); 
//    }
//    
//    
//}
