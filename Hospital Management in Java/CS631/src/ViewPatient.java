
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author agrimasthana
 */
public class ViewPatient extends javax.swing.JFrame {
Connection connex;
PreparedStatement state;
ResultSet rs;
    /**
     * Creates new form ViewPatient
     */
    public ViewPatient() {
        initComponents();
        connex = connect.accessdb();
        updatetable();
        this.getContentPane().setBackground(Color.WHITE);
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
        PatientTable = new javax.swing.JTable();
        bUpdate = new javax.swing.JButton();
        bDelete = new javax.swing.JButton();
        bBack = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        PatientTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Patient Number", "Name", "DOB", "SSN", "Blood Type", "Gender", "Address"
            }
        ));
        jScrollPane1.setViewportView(PatientTable);

        bUpdate.setText("Update");
        bUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bUpdateActionPerformed(evt);
            }
        });

        bDelete.setText("Delete");
        bDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bDeleteActionPerformed(evt);
            }
        });

        bBack.setText("Back");
        bBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bBackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 638, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(109, 109, 109)
                .addComponent(bUpdate)
                .addGap(40, 40, 40)
                .addComponent(bDelete)
                .addGap(47, 47, 47)
                .addComponent(bBack)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bUpdate)
                    .addComponent(bDelete)
                    .addComponent(bBack))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bUpdateActionPerformed
        // TODO add your handling code here:
        try
       {
        int row= PatientTable.getSelectedRow();
        String value1=(PatientTable.getModel().getValueAt(row,0).toString());
        String value2=(PatientTable.getModel().getValueAt(row,1).toString());
        String value3=(PatientTable.getModel().getValueAt(row,2).toString());
        String value4=(PatientTable.getModel().getValueAt(row,3).toString());
        String value5=(PatientTable.getModel().getValueAt(row,4).toString());
        String value6=(PatientTable.getModel().getValueAt(row,5).toString());
        String value7=(PatientTable.getModel().getValueAt(row,6).toString());
        String sql="update Patient set Name='"+value2+"',DOB='"+value3+"',SSN='"+value4+"',Bloodtype='"+value5+"',Gender='"+value6+"',Address='"+value7+"'where Pnum='"+value1+"'";
        state=connex.prepareStatement(sql);
        boolean test = state.execute();
        if(test==false)
        {
            JOptionPane.showMessageDialog(null, "value updated");
            updatetable();
        }
        else
        {
            JOptionPane.showMessageDialog(null, "unable to update");
        }
       }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }//GEN-LAST:event_bUpdateActionPerformed

    private void bDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bDeleteActionPerformed
        // TODO add your handling code here:
        String sql="delete from Patient where Pnum=?";
        try
        {
            int row= PatientTable.getSelectedRow();
            String value=(PatientTable.getModel().getValueAt(row, 0).toString());
            state=connex.prepareStatement(sql);
            state.setString(1, value);
            state.execute();
            JOptionPane.showMessageDialog(null, "deleted");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        updatetable();
    }//GEN-LAST:event_bDeleteActionPerformed

    private void bBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bBackActionPerformed
        // TODO add your handling code here:
        PatientManagement pm = new PatientManagement();
        pm.setVisible(true);
        pm.pack();
    }//GEN-LAST:event_bBackActionPerformed
public void updatetable()
{
    try{
        String sql="select * from Patient";
        state=connex.prepareStatement(sql);
        rs = state.executeQuery();
        PatientTable.setModel(DbUtils.resultSetToTableModel(rs));
    }
    catch(Exception e)
    {
        e.printStackTrace();
    }
}
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ViewPatient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewPatient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewPatient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewPatient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewPatient().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable PatientTable;
    private javax.swing.JButton bBack;
    private javax.swing.JButton bDelete;
    private javax.swing.JButton bUpdate;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
