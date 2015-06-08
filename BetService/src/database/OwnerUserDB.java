package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import logic.Outcome;
import logic.OwnerUser;
import logic.Payments;
import util.MySqlUtil;

public class OwnerUserDB extends OwnerUser{

    private final String loginPrepared = "SELECT * FROM OWNERUSER WHERE LOGNAME=? AND PASSWORD=?";
    @Override
    public void login(String logname, String password) {
        try {
            PreparedStatement prepared = MySqlUtil.extractConnection(this).prepareStatement(loginPrepared);
            prepared.setString(1, logname);
            prepared.setString(2, password);
            ResultSet rs = prepared.executeQuery();
            rs.next();
            this.setLogname(rs.getString("LOGNAME"));
            this.setBalance(rs.getDouble("BALANCE"));
            this.setId(rs.getLong("ID_OWNER"));
            this.setPassword(rs.getString("PASSWORD"));
        } catch (SQLException ex) {
            throw new IllegalArgumentException("Can not login as admin "+logname+":"+password, ex);
        }
    }

    @Override
    public void save() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    private final String paymentsPrepared = "SELECT * FROM PAYMENT WHERE STATUS="+Payments.Status.Waiting.getCode();
    @Override
    public List<Payments> getOpenPayments() {
        try {
            List<Payments> payments = new LinkedList<>();
            PreparedStatement prepared = MySqlUtil.extractConnection(this).prepareStatement(paymentsPrepared);
            ResultSet rs = prepared.executeQuery();
            while (rs.next()) {
                PaymentsDB payment = new PaymentsDB();
                payment.setDataProvider(getDataProvider());
                payment.load(rs);
                payments.add(payment);
            }
            return payments;
        } catch (SQLException ex) {
            throw new IllegalStateException("Can not load payments!", ex);
        }
    }
    
}
