/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rdfsystem;

import java.util.Map;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Wizard
 */
public class AccountFrame extends javax.swing.JDialog {

    /**
     * Creates new form AccountFrame
     */
    public AccountFrame() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setModal(true);
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
        yearTable = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        auTable = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        journalTable = new javax.swing.JTable();

        yearTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "年份", "数量"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        yearTable.setColumnSelectionAllowed(true);
        yearTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(yearTable);
        yearTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (yearTable.getColumnModel().getColumnCount() > 0) {
            yearTable.getColumnModel().getColumn(0).setResizable(false);
            yearTable.getColumnModel().getColumn(1).setResizable(false);
        }

        auTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "作者", "数量"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        auTable.setColumnSelectionAllowed(true);
        auTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(auTable);
        auTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (auTable.getColumnModel().getColumnCount() > 0) {
            auTable.getColumnModel().getColumn(0).setResizable(false);
            auTable.getColumnModel().getColumn(1).setResizable(false);
        }

        journalTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "期刊", "数量"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        journalTable.setColumnSelectionAllowed(true);
        journalTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(journalTable);
        journalTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (journalTable.getColumnModel().getColumnCount() > 0) {
            journalTable.getColumnModel().getColumn(0).setResizable(false);
            journalTable.getColumnModel().getColumn(1).setResizable(false);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 418, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 418, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 418, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(AccountFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AccountFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AccountFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AccountFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AccountFrame().setVisible(true);
            }
        });
    }

    public void setAuthorData(Map<String, Integer> data)
    {
        DefaultTableModel model = (DefaultTableModel)auTable.getModel();
        
        for(int i = model.getRowCount() - 1; i >= 0; i--)
            model.removeRow(i);
        
        for(Map.Entry<String, Integer> elem : data.entrySet())
        {
            model.addRow(new Object[] 
            {
                elem.getKey(), 
                String.valueOf(elem.getValue())
            });
        }
    }
    
    public void setYearData(Map<Integer, Integer> data)
    {
        DefaultTableModel model = (DefaultTableModel)yearTable.getModel();
        
        for(int i = model.getRowCount() - 1; i >= 0; i--)
            model.removeRow(i);
        
        for(Map.Entry<Integer, Integer> elem : data.entrySet())
        {
            model.addRow(new Object[] 
            {
                String.valueOf(elem.getKey()), 
                String.valueOf(elem.getValue())
            });
        }
    }
    
    public void setJournalData(Map<String, Integer> data)
    {
        DefaultTableModel model = (DefaultTableModel)journalTable.getModel();
        
        for(int i = model.getRowCount() - 1; i >= 0; i--)
            model.removeRow(i);
        
        for(Map.Entry<String, Integer> elem : data.entrySet())
        {
            model.addRow(new Object[] 
            {
                elem.getKey(), 
                String.valueOf(elem.getValue())
            });
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable auTable;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable journalTable;
    private javax.swing.JTable yearTable;
    // End of variables declaration//GEN-END:variables
}
