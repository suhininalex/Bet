package gui.user;

import java.rmi.RemoteException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import service.Session;
import util.RemoteProvider;
import util.Utils;

public class UserProfile extends javax.swing.JFrame {

    private final Session session;
    public UserProfile(Session session) {
        initComponents();
        Utils.centerFrame(this);
        this.session = session;
        update();
    }

    public void update(){
        try {
            Map<String,Object> profile = RemoteProvider.getUserService().getProfile(session);
            idUser.setText(profile.get("id").toString());
            fullname.setText(profile.get("fullname").toString());
            balance.setText(profile.get("balance").toString());
            logname.setText(profile.get("logname").toString());
        } catch (RemoteException ex) {
            throw new IllegalStateException("Can not update profile!",ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        logname = new javax.swing.JTextField();
        fullname = new javax.swing.JTextField();
        balance = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        idUser = new javax.swing.JTextField();
        showOutcomes = new javax.swing.JButton();
        showBets = new javax.swing.JButton();
        withdraw = new javax.swing.JButton();
        deposit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Profile");
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Logname:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Balance:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Full name:");

        logname.setEditable(false);
        logname.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        logname.setAutoscrolls(false);

        fullname.setEditable(false);
        fullname.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        fullname.setAutoscrolls(false);

        balance.setEditable(false);
        balance.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        balance.setAutoscrolls(false);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Id:");

        idUser.setEditable(false);
        idUser.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        idUser.setAutoscrolls(false);

        showOutcomes.setText("Show all outcomes!");
        showOutcomes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showOutcomesActionPerformed(evt);
            }
        });

        showBets.setText("Show all active bets!");
        showBets.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showBetsActionPerformed(evt);
            }
        });

        withdraw.setText("Withdraw (-100)");
        withdraw.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                withdrawActionPerformed(evt);
            }
        });

        deposit.setText("Deposit (+100)");
        deposit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                depositActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(showOutcomes, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3)
                                .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(idUser, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fullname, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(logname, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(balance, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(deposit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGap(18, 18, 18)
                            .addComponent(withdraw, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(showBets, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(idUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(logname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(fullname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(balance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(showOutcomes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(showBets)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(withdraw)
                    .addComponent(deposit))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void showBetsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showBetsActionPerformed
        UserBets betsFrame = new UserBets(session);
        betsFrame.setVisible(true);
    }//GEN-LAST:event_showBetsActionPerformed

    private void showOutcomesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showOutcomesActionPerformed
        OpenEvents eventsFrame = new OpenEvents(session);
        eventsFrame.setVisible(true);
    }//GEN-LAST:event_showOutcomesActionPerformed

    private void depositActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_depositActionPerformed
        try {
            RemoteProvider.getUserService().deposit(session, 100.0);
            this.update();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(rootPane, "Can not add to balance!\n"+ex.getMessage());
        }
    }//GEN-LAST:event_depositActionPerformed

    private void withdrawActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_withdrawActionPerformed
        try {
            RemoteProvider.getUserService().withdraw(session, 100.0);
            this.update();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(rootPane, "Can not sub from balance!\n"+ex.getMessage());
        }
    }//GEN-LAST:event_withdrawActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField balance;
    private javax.swing.JButton deposit;
    private javax.swing.JTextField fullname;
    private javax.swing.JTextField idUser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField logname;
    private javax.swing.JButton showBets;
    private javax.swing.JButton showOutcomes;
    private javax.swing.JButton withdraw;
    // End of variables declaration//GEN-END:variables
}
