/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package telas.crud;

import controllers.FrmVooController;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import model.VooTO;

/**
 *
 * @author Templar
 */
public class FrmVoo extends TrabalhoFrame {

    private FrmVooController controller = new FrmVooController();
    private String[] companhias;
    
    /**
     * Creates new form FrmVoo
     */
    public FrmVoo() {
	companhias = controller.getCompanhias();
        initComponents();
	tblVooPopulate();
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrVoo = new javax.swing.JScrollPane();
        tblVoo = new javax.swing.JTable();
        btnDeleteVoo = new javax.swing.JButton();
        btnSaveVoo = new javax.swing.JButton();
        btnNewVoo = new javax.swing.JButton();
        lblSearchVoo = new javax.swing.JLabel();
        txtSearchVoo = new java.awt.TextField();
        lblNewSiglaComp = new javax.swing.JLabel();
        cmbNewSiglaComp = new javax.swing.JComboBox();
        lblNewNumVoo = new javax.swing.JLabel();
        txtNewNumVoo = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tblVoo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Sigla da Companhia", "Número do Vôo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblVoo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblVooMouseClicked(evt);
            }
        });
        tblVoo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tblVooKeyPressed(evt);
            }
        });
        scrVoo.setViewportView(tblVoo);

        btnDeleteVoo.setText("Apagar");
        btnDeleteVoo.setEnabled(false);
        btnDeleteVoo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDeleteVooMouseClicked(evt);
            }
        });

        btnSaveVoo.setText("Salvar");
        btnSaveVoo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSaveVooMouseClicked(evt);
            }
        });

        btnNewVoo.setText("Novo");
        btnNewVoo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnNewVooMouseClicked(evt);
            }
        });

        lblSearchVoo.setText("Pesquise pela Sigla da Companhia");

        txtSearchVoo.setName(""); // NOI18N
        txtSearchVoo.addTextListener(new java.awt.event.TextListener() {
            public void textValueChanged(java.awt.event.TextEvent evt) {
                txtSearchVooTextValueChanged(evt);
            }
        });

        lblNewSiglaComp.setText("Sigla Companhia");

        cmbNewSiglaComp.setModel(new javax.swing.DefaultComboBoxModel<String>(companhias));
        cmbNewSiglaComp.setEnabled(false);

        lblNewNumVoo.setText("Número do Vôo");

        txtNewNumVoo.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrVoo, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblSearchVoo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSearchVoo, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblNewSiglaComp)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbNewSiglaComp, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblNewNumVoo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNewNumVoo, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnDeleteVoo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSaveVoo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNewVoo)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblSearchVoo)
                    .addComponent(txtSearchVoo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrVoo, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNewSiglaComp)
                    .addComponent(cmbNewSiglaComp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNewNumVoo)
                    .addComponent(txtNewNumVoo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNewVoo)
                    .addComponent(btnSaveVoo)
                    .addComponent(btnDeleteVoo))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void tblVooSelect(int val) {
	cmbNewSiglaComp.setEnabled(false);
	txtNewNumVoo.setEnabled(false);
	btnDeleteVoo.setEnabled(true);
	tblVoo.changeSelection(val, 0, false, false);
	String siglaComp, numVoo;
	siglaComp = (String) tblVoo.getValueAt(val, 0);
	numVoo = (String) tblVoo.getValueAt(val, 1);
	cmbNewSiglaComp.setSelectedItem(siglaComp);
	txtNewNumVoo.setText(numVoo);
    }

    private void tblVooPopulate() {
	List<VooTO> res = controller.find(txtSearchVoo.getText());
	DefaultTableModel modelo = (DefaultTableModel) tblVoo.getModel();
	modelo.setRowCount(0);
	if (res == null) {
	    return;
	}
	int i = 0;
	for (VooTO voo : res) {
	    modelo.addRow(new Object[modelo.getColumnCount()]);
	    tblVoo.setValueAt(voo.getCompanhia().getSigla(), i, 0);
	    tblVoo.setValueAt(voo.getNumVoo(), i, 1);
	    i++;
	}
    }
    
    private void txtSearchVooTextValueChanged(java.awt.event.TextEvent evt) {//GEN-FIRST:event_txtSearchVooTextValueChanged
	tblVooPopulate();
    }//GEN-LAST:event_txtSearchVooTextValueChanged

    private void tblVooKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblVooKeyPressed
	int val = tblVoo.getSelectedRow();
	if (evt.getID() == java.awt.event.KeyEvent.VK_DOWN) {
	    if (val <= tblVoo.getRowCount()) {
		val++;
	    }
	} else if (evt.getID() == java.awt.event.KeyEvent.VK_UP) {
	    if (val > 0) {
		val--;
	    }
	} else {
	    return;
	}
	tblVooSelect(val);
    }//GEN-LAST:event_tblVooKeyPressed

    private void tblVooMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblVooMouseClicked
	int val = tblVoo.getSelectedRow();
	tblVooSelect(val);
    }//GEN-LAST:event_tblVooMouseClicked

    private void btnDeleteVooMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteVooMouseClicked
	controller.deletar((String) cmbNewSiglaComp.getSelectedItem(), txtNewNumVoo.getText());
	tblVooPopulate();
    }//GEN-LAST:event_btnDeleteVooMouseClicked

    private void btnSaveVooMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSaveVooMouseClicked
	controller.salvar((String) cmbNewSiglaComp.getSelectedItem(), txtNewNumVoo.getText());
	tblVooPopulate();
    }//GEN-LAST:event_btnSaveVooMouseClicked

    private void btnNewVooMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNewVooMouseClicked
	cmbNewSiglaComp.setSelectedIndex(0);
	txtNewNumVoo.setText("");
	cmbNewSiglaComp.setEnabled(true);
	txtNewNumVoo.setEnabled(true);
	btnDeleteVoo.setEnabled(false);
    }//GEN-LAST:event_btnNewVooMouseClicked
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmVoo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmVoo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmVoo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmVoo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new FrmVoo().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDeleteVoo;
    private javax.swing.JButton btnNewVoo;
    private javax.swing.JButton btnSaveVoo;
    private javax.swing.JComboBox cmbNewSiglaComp;
    private javax.swing.JLabel lblNewNumVoo;
    private javax.swing.JLabel lblNewSiglaComp;
    private javax.swing.JLabel lblSearchVoo;
    private javax.swing.JScrollPane scrVoo;
    private javax.swing.JTable tblVoo;
    private javax.swing.JTextField txtNewNumVoo;
    private java.awt.TextField txtSearchVoo;
    // End of variables declaration//GEN-END:variables
}