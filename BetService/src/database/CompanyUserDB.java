//package database;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import logic.CompanyUser;
//import util.StorableInDB;
//
//public class CompanyUserDB extends CompanyUser implements StorableInDB {
//    Connection connection = null;
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
//    private final String preparedSave = "INSERT INTO COMPANYUSER (LOGNAME, PASSWORD, BALANCE, FULLNAME)" +
//            "VALUES (?, ?, ?, ?)" +
//            "ON DUPLICATE KEY UPDATE LOGNAME=?, PASSWORD=?, BALANCE=?, FULLNAME=?";
//    @Override
//    public void save() {
//        try {
//            PreparedStatement prepared = getConnectionToUse().prepareStatement(preparedSave, Statement.RETURN_GENERATED_KEYS);
//            prepared.setString(1, getLogname());
//            prepared.setString(2, getPassword());
//            prepared.setDouble(3, getBalance());
//            prepared.setString(4, getCompanyName());
//            prepared.setString(5, getLogname());
//            prepared.setString(6, getPassword());
//            prepared.setDouble(7, getBalance());
//            prepared.setString(8, getCompanyName());
//            prepared.execute();
//            ResultSet rs = prepared.getGeneratedKeys();
//            if (rs.next())
//                setId(rs.getLong(1));
//        } catch (SQLException ex) {
//            throw new IllegalStateException("Can not save SelfUser entity!", ex);
//        }
//    }
//    
//    public void load(ResultSet resultSet) {
//        try {
//            this.setId(resultSet.getLong("ID_COMPANY"));
//            this.setCompanyName(resultSet.getString("FULLNAME"));
//            this.setLogname(resultSet.getString("LOGNAME"));
//            this.setPassword(resultSet.getString("PASSWORD"));
//            this.setBalance(resultSet.getDouble("BALANCE"));
//        } catch (SQLException ex) {
//            throw new IllegalArgumentException("Can not load company entity!", ex);
//        }
//    }
//    
//    private final String preparedAuth = "SELECT * FROM COMPANYUSER WHERE LOGNAME=? AND PASSWORD=?";
//    @Override
//    public void login(String logname, String password){
//        try {
//            PreparedStatement prepared = getConnectionToUse().prepareStatement(preparedAuth);
//            prepared.setString(1, logname);
//            prepared.setString(2, password);
//            ResultSet rs = prepared.executeQuery();
//            rs.next();
//            load(rs);
//        } catch (SQLException ex) {
//            throw new IllegalStateException("Can not login as company user!", ex);
//        }
//    }
//    
//}
