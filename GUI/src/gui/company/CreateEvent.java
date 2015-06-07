package gui.company;

import javax.swing.JOptionPane;
import service.OutcomeDef;
import service.Session;
import util.RemoteProvider;
import util.Utils;

public class CreateEvent extends javax.swing.JFrame {

    private final Session session;
    
    /**
     * Creates new form NewJFrame
     */
    public CreateEvent(Session session) {
        initComponents();
        this.session = session;
        Utils.centerFrame(this);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        description = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        afterHours = new javax.swing.JTextField();
        firstName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        firstK = new javax.swing.JTextField();
        secondName = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        secondK = new javax.swing.JTextField();
        createEvent = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Create event");

        description.setText("Default");
        description.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                descriptionActionPerformed(evt);
            }
        });

        jLabel1.setText("Description");

        afterHours.setText("500");

        firstName.setText("first");
        firstName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                firstNameActionPerformed(evt);
            }
        });

        jLabel2.setText("Expires after hours:");

        jLabel3.setText("First outcome name:");

        jLabel4.setText("First outcome K:");

        firstK.setText("2.0");

        secondName.setText("second");

        jLabel5.setText("Second outcome K:");

        jLabel6.setText("Second outcome name:");

        secondK.setText("0.5");

        createEvent.setText("OK");
        createEvent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createEventActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(56, 56, 56)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(firstK)
                            .addComponent(afterHours)
                            .addComponent(description, javax.swing.GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
                            .addComponent(firstName)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5))
                        .addGap(42, 42, 42)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(createEvent, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(secondName, javax.swing.GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
                                .addComponent(secondK)))))
                .addContainerGap(48, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(description, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(afterHours, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(firstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(firstK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(secondName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(secondK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(createEvent)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void descriptionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_descriptionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_descriptionActionPerformed

    private void firstNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_firstNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_firstNameActionPerformed

    private void createEventActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createEventActionPerformed
        try {
            long expiresAfter = Long.parseLong(afterHours.getText());
            OutcomeDef [] outcomes = {
                new OutcomeDef(Double.parseDouble(firstK.getText()), firstName.getText()),
                new OutcomeDef(Double.parseDouble(secondK.getText()), secondName.getText())
            };
            RemoteProvider.getCompanyService().createEvent(session, description.getText(), expiresAfter, outcomes);
            this.dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Can not create event!\n"+ex.getMessage());
        }
    }//GEN-LAST:event_createEventActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField afterHours;
    private javax.swing.JButton createEvent;
    private javax.swing.JTextField description;
    private javax.swing.JTextField firstK;
    private javax.swing.JTextField firstName;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JTextField secondK;
    private javax.swing.JTextField secondName;
    // End of variables declaration//GEN-END:variables
}
