package gui.user;

import java.text.NumberFormat;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import service.Session;
import util.RemoteProvider;
import util.Utils;

public class MakeBet extends javax.swing.JFrame {

    NumberFormat numberFormat;
    private final Session session;
    List<Map<String,Object>> eventInfo;
    /**
     * Creates new form MakeBet
     */
    public MakeBet(Session session, List<Map<String,Object>> eventInfo) {
        numberFormat = NumberFormat.getInstance();
        numberFormat.setMinimumIntegerDigits(1);
        initComponents();
        this.session = session;
        Utils.centerFrame(this);
        this.eventInfo = eventInfo;
        
        update();
    }
    
    public void update(){
        for (Map<String,Object> outcome : eventInfo){
            String item = outcome.get("name") + "(" + outcome.get("k") +")";
            outcomeList.addItem(item);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        outcomeList = new javax.swing.JComboBox();
        createBet = new javax.swing.JButton();
        amount = new javax.swing.JSpinner();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Create bet");
        setResizable(false);

        createBet.setText("OK");
        createBet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createBetActionPerformed(evt);
            }
        });

        amount.setModel(new javax.swing.SpinnerNumberModel(100, 50, 1000000, 50));
        amount.setEditor(new javax.swing.JSpinner.NumberEditor(amount, ""));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(49, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(outcomeList, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(createBet, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                    .addComponent(amount))
                .addGap(45, 45, 45))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(outcomeList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(amount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(createBet)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void createBetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createBetActionPerformed
        try {
            double sum = (int) amount.getValue();
            RemoteProvider.getUserService().createBet(session, (Long)(eventInfo.get(outcomeList.getSelectedIndex()).get("id")) , sum);
            this.dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Can not create bet!\n"+ex.getMessage());
            ex.printStackTrace();
        }
    }//GEN-LAST:event_createBetActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSpinner amount;
    private javax.swing.JButton createBet;
    private javax.swing.JComboBox outcomeList;
    // End of variables declaration//GEN-END:variables
}
