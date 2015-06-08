package gui.company;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;
import javax.swing.table.DefaultTableModel;
import service.Session;
import util.RemoteProvider;
import util.Utils;

/**
 *
 * @author llama
 */
public class CompanyEvents extends javax.swing.JFrame {

    private final Session session;
    private List<Map<String,Object>> events;
    
    private DefaultTableModel tableModel = 
            new javax.swing.table.DefaultTableModel(
                new Object [][] {},
                new String [] {"Id", "Description", "Expires", "STATUS"}
            ) 
            {
                Class[] types = new Class [] {
                    java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
                };
                
                boolean[] canEdit = new boolean [] {
                    false, false, false, false
                };

                public Class getColumnClass(int columnIndex) {
                    return types [columnIndex];
                }
                
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit [columnIndex];
                }
            };
    
    public CompanyEvents(Session session) {
        initComponents();
        this.session = session;
        Utils.centerFrame(this);
        update();
    }

    public void update(){
        try {
            events = RemoteProvider.getCompanyService().getOpenEvents(session);
            while (tableModel.getRowCount()>0) tableModel.removeRow(0);
            for (Map<String, Object> buf : events) {
                Object [] row = {
                    buf.get("id"),
                    buf.get("description"),
                    buf.get("expires"),
                    buf.get("status"),
                };
                tableModel.addRow(row);
            }
        } catch (RemoteException ex) {
            throw new IllegalStateException("Can not update event list!",ex);
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        resultTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Company events");

        resultTable.setModel(tableModel);
        resultTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                resultTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(resultTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 527, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void resultTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_resultTableMouseClicked
        if (evt.getClickCount()<2) return;
        long id = (long)events.get(resultTable.getSelectedRow()).get("id");
        EventOutcomes eventOutcomesFrame = new EventOutcomes(session,id,this);
        eventOutcomesFrame.setVisible(true);
    }//GEN-LAST:event_resultTableMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable resultTable;
    // End of variables declaration//GEN-END:variables
}