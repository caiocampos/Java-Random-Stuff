/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package telas.crud;

import controllers.FrmAeroportoController;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import model.AeroportoTO;

/**
 *
 * @author William
 */
public class FrmAeroporto extends TrabalhoFrame {

    private FrmAeroportoController controller = new FrmAeroportoController();

    /**
     * Creates new form FrmAeroporto
     */
    public FrmAeroporto() {
	initComponents();
	tblAeroportoPopulate();
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

        scrAeroporto = new javax.swing.JScrollPane();
        tblAeroporto = new javax.swing.JTable();
        btnDeleteAeroporto = new javax.swing.JButton();
        btnSaveAeroporto = new javax.swing.JButton();
        btnNewAeroporto = new javax.swing.JButton();
        lblSearchAeroporto = new javax.swing.JLabel();
        txtSearchAeroporto = new java.awt.TextField();
        lblNewIATA = new javax.swing.JLabel();
        txtNewIATA = new javax.swing.JTextField();
        lblNewICAO = new javax.swing.JLabel();
        txtNewICAO = new javax.swing.JTextField();
        lblNewNome = new javax.swing.JLabel();
        txtNewNome = new javax.swing.JTextField();
        lblNewCidade = new javax.swing.JLabel();
        txtNewCidade = new javax.swing.JTextField();
        lblNewPais = new javax.swing.JLabel();
        txtNewPais = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tblAeroporto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código IATA", "Código ICAO", "Nome", "Cidade", "País"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblAeroporto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblAeroportoMouseClicked(evt);
            }
        });
        tblAeroporto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tblAeroportoKeyPressed(evt);
            }
        });
        scrAeroporto.setViewportView(tblAeroporto);

        btnDeleteAeroporto.setText("Apagar");
        btnDeleteAeroporto.setEnabled(false);
        btnDeleteAeroporto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDeleteAeroportoMouseClicked(evt);
            }
        });

        btnSaveAeroporto.setText("Salvar");
        btnSaveAeroporto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSaveAeroportoMouseClicked(evt);
            }
        });

        btnNewAeroporto.setText("Novo");
        btnNewAeroporto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnNewAeroportoMouseClicked(evt);
            }
        });

        lblSearchAeroporto.setText("Pesquise pelo código IATA");

        txtSearchAeroporto.addTextListener(new java.awt.event.TextListener() {
            public void textValueChanged(java.awt.event.TextEvent evt) {
                txtSearchAeroportoTextValueChanged(evt);
            }
        });

        lblNewIATA.setText("Código IATA");

        txtNewIATA.setEnabled(false);

        lblNewICAO.setText("Código ICAO");

        lblNewNome.setText("Nome");

        lblNewCidade.setText("Cidade");

        lblNewPais.setText("País");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(scrAeroporto, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 503, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(lblNewCidade)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNewCidade, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblNewPais)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNewPais, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnDeleteAeroporto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSaveAeroporto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNewAeroporto))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(lblSearchAeroporto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSearchAeroporto, javax.swing.GroupLayout.DEFAULT_SIZE, 367, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(lblNewIATA)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNewIATA, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblNewICAO)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNewICAO, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblNewNome)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNewNome, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblSearchAeroporto)
                    .addComponent(txtSearchAeroporto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrAeroporto, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNewIATA)
                    .addComponent(txtNewIATA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNewICAO)
                    .addComponent(txtNewICAO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNewNome)
                    .addComponent(txtNewNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNewPais, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNewCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNewCidade)
                    .addComponent(lblNewPais))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNewAeroporto)
                    .addComponent(btnSaveAeroporto)
                    .addComponent(btnDeleteAeroporto))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblAeroportoSelect(int val) {
	txtNewIATA.setEnabled(false);
	btnDeleteAeroporto.setEnabled(true);
	tblAeroporto.changeSelection(val, 0, false, false);
	String iata, icao, nome, cidade, pais;
	iata = (String) tblAeroporto.getValueAt(val, 0);
	icao = (String) tblAeroporto.getValueAt(val, 1);
	nome = (String) tblAeroporto.getValueAt(val, 2);
	cidade = (String) tblAeroporto.getValueAt(val, 3);
	pais = (String) tblAeroporto.getValueAt(val, 4);
	txtNewIATA.setText(iata);
	txtNewICAO.setText(icao);
	txtNewNome.setText(nome);
	txtNewCidade.setText(cidade);
	txtNewPais.setText(pais);
    }

    private void tblAeroportoPopulate() {
	List<AeroportoTO> res = controller.find(txtSearchAeroporto.getText());
	DefaultTableModel modelo = (DefaultTableModel) tblAeroporto.getModel();
	modelo.setRowCount(0);
	if (res == null) {
	    return;
	}
	int i = 0;
	for (AeroportoTO aeroporto : res) {
	    modelo.addRow(new Object[modelo.getColumnCount()]);
	    tblAeroporto.setValueAt(aeroporto.getCodIATA(), i, 0);
	    tblAeroporto.setValueAt(aeroporto.getCodICAO(), i, 1);
	    tblAeroporto.setValueAt(aeroporto.getNome(), i, 2);
	    tblAeroporto.setValueAt(aeroporto.getCidade(), i, 3);
	    tblAeroporto.setValueAt(aeroporto.getPais(), i, 4);
	    i++;
	}
    }

    private void tblAeroportoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblAeroportoKeyPressed
	int val = tblAeroporto.getSelectedRow();
	if (evt.getID() == java.awt.event.KeyEvent.VK_DOWN) {
	    if (val <= tblAeroporto.getRowCount()) {
		val++;
	    }
	} else if (evt.getID() == java.awt.event.KeyEvent.VK_UP) {
	    if (val > 0) {
		val--;
	    }
	} else {
	    return;
	}
	tblAeroportoSelect(val);
    }//GEN-LAST:event_tblAeroportoKeyPressed

    private void tblAeroportoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblAeroportoMouseClicked
	int val = tblAeroporto.getSelectedRow();
	tblAeroportoSelect(val);
    }//GEN-LAST:event_tblAeroportoMouseClicked

    private void btnNewAeroportoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNewAeroportoMouseClicked
	txtNewIATA.setText("");
	txtNewICAO.setText("");
	txtNewNome.setText("");
	txtNewCidade.setText("");
	txtNewPais.setText("");
	txtNewIATA.setEnabled(true);
	btnDeleteAeroporto.setEnabled(false);
    }//GEN-LAST:event_btnNewAeroportoMouseClicked

    private void btnSaveAeroportoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSaveAeroportoMouseClicked
	controller.salvar(txtNewIATA.getText(), txtNewICAO.getText(), txtNewNome.getText(), txtNewCidade.getText(), txtNewPais.getText());
	tblAeroportoPopulate();
    }//GEN-LAST:event_btnSaveAeroportoMouseClicked

    private void btnDeleteAeroportoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteAeroportoMouseClicked
	controller.deletar(txtNewIATA.getText());
	tblAeroportoPopulate();
    }//GEN-LAST:event_btnDeleteAeroportoMouseClicked

    private void txtSearchAeroportoTextValueChanged(java.awt.event.TextEvent evt) {//GEN-FIRST:event_txtSearchAeroportoTextValueChanged
	tblAeroportoPopulate();
    }//GEN-LAST:event_txtSearchAeroportoTextValueChanged

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
	    java.util.logging.Logger.getLogger(FrmAssento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	} catch (InstantiationException ex) {
	    java.util.logging.Logger.getLogger(FrmAssento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	} catch (IllegalAccessException ex) {
	    java.util.logging.Logger.getLogger(FrmAssento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	} catch (javax.swing.UnsupportedLookAndFeelException ex) {
	    java.util.logging.Logger.getLogger(FrmAssento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	}
	//</editor-fold>

	/*
	 * Create and display the form
	 */
	java.awt.EventQueue.invokeLater(new Runnable() {

	    public void run() {
		new FrmAeroporto().setVisible(true);
	    }
	});
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDeleteAeroporto;
    private javax.swing.JButton btnNewAeroporto;
    private javax.swing.JButton btnSaveAeroporto;
    private javax.swing.JLabel lblNewCidade;
    private javax.swing.JLabel lblNewIATA;
    private javax.swing.JLabel lblNewICAO;
    private javax.swing.JLabel lblNewNome;
    private javax.swing.JLabel lblNewPais;
    private javax.swing.JLabel lblSearchAeroporto;
    private javax.swing.JScrollPane scrAeroporto;
    private javax.swing.JTable tblAeroporto;
    private javax.swing.JTextField txtNewCidade;
    private javax.swing.JTextField txtNewIATA;
    private javax.swing.JTextField txtNewICAO;
    private javax.swing.JTextField txtNewNome;
    private javax.swing.JTextField txtNewPais;
    private java.awt.TextField txtSearchAeroporto;
    // End of variables declaration//GEN-END:variables
}
