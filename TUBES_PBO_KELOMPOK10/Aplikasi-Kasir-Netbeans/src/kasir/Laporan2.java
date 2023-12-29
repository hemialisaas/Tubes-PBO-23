/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kasir;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Kelompok10
 */
public class Laporan2 extends javax.swing.JFrame {
    Connection koneksi = database.koneksiDB();
    PreparedStatement pst;
    ResultSet rst;
    DefaultTableModel tableModel1;
    DefaultTableModel tableModel2;
    /**
     * Creates new form popup
     */
    public Laporan2(String input) {
        initComponents();
        koneksi = database.koneksiDB();
        //tableModel1 = (DefaultTableModel) jTable1.getModel();
        tableModel2 = (DefaultTableModel) jTable2.getModel();
        update_tabel_popup(input);
    }

    public void update_tabel_popup(String sql) {
    try {
        pst = koneksi.prepareStatement(sql);
        rst = pst.executeQuery();

        //tableModel1.setRowCount(0);
        tableModel2.setRowCount(0);

        //String[] columnNames1 = {"Kode Transaksi", "Kode Detail", "Tanggal", "Jam", "Total"};
        //tableModel1.setColumnIdentifiers(columnNames1);

        while (rst.next()) {
            Object[] row = new Object[5]; 

            for (int i = 1; i <= 5; i++) {
                row[i - 1] = rst.getObject(i);
            }

            //tableModel1.addRow(row);

            String kodeDetail = rst.getString("Kode_Detail");
            String detailSql = "SELECT db.Kode_Detail, db.Kode_Makanan, b.Nama_Makanan, db.Harga, db.Jumlah, db.Discount, db.Subtotal " +
                    "FROM detail_barang db " +
                    "INNER JOIN barang b ON db.Kode_Makanan = b.Kode_Makanan " +
                    "WHERE db.Kode_Detail=?";
            pst = koneksi.prepareStatement(detailSql);
            pst.setString(1, kodeDetail);
            ResultSet detailRst = pst.executeQuery();

            String[] columnNames2 = {"Kode Detail", "Kode Barang", "Nama Barang", "Harga", "Jumlah", "Discount", "Subtotal"};
            tableModel2.setColumnIdentifiers(columnNames2);

            while (detailRst.next()) {
                Object[] detailRow = new Object[7]; 

                for (int i = 1; i <= 7; i++) {
                    detailRow[i - 1] = detailRst.getObject(i);
                }

                tableModel2.addRow(detailRow);
            }
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e);
    }
}


    
    public void updateDetailTable(String kodeDetail) {
    try {
        String sql = "SELECT * FROM detail_barang WHERE Kode_Detail=?";
        pst = koneksi.prepareStatement(sql);
        pst.setString(1, kodeDetail);
        rst = pst.executeQuery();
        jTable2.setModel(DbUtils.resultSetToTableModel(rst));
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e);
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

        jLabel7 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel7.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        jLabel7.setText("Data Transaksi");

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 528, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(227, 227, 227)
                        .addComponent(jLabel7)))
                .addGap(0, 17, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel7)
                .addGap(63, 63, 63)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(95, Short.MAX_VALUE))
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
            java.util.logging.Logger.getLogger(Laporan2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Laporan2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Laporan2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Laporan2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                //new Laporan2().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable2;
    // End of variables declaration//GEN-END:variables
}
