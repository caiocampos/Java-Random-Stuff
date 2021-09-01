/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package telas.crud;

import dal.AeroportoDAL;
import dal.TrechoDAL;
import dal.VooDAL;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import model.AeroportoTO;
import model.TrechoTO;
import model.VooTO;

/**
 *
 * @author Templar
 */
public class FrmTrecho extends TrabalhoFrame {

    /**
     * Creates new form FrmTrecho
     */
    private void atualizaTabelaTrechos() {
        DefaultTableModel model = new DefaultTableModel(new String[]{"ID", "Comp", "Num voo", "Origem", "Destino", "Horario"}, 0) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        String busca = txtBusca.getText().trim();
        //Preencho com os trechos
        for (TrechoTO t : TrechoDAL.findAllQuery(busca)) {
            model.addRow(new String[]{
                        String.valueOf(t.getIdTrecho()),
                        t.getSiglaComp(),
                        t.getNumVoo(),
                        t.getOrigem(),
                        t.getDestino(),
                        t.getHorario()
                    });
        }

        tblTrechos.setModel(model);
    }

    private void preencheCombos() {

        //Limpa os 2 combos de aeroportos
        cmbOrigem.removeAllItems();
        cmbDestino.removeAllItems();
        cmbVoo.removeAllItems();

        ArrayList<AeroportoTO> aeroportos = (ArrayList<AeroportoTO>) AeroportoDAL.getInstance().findAll("");
        if (aeroportos != null) {
            for (AeroportoTO a : aeroportos) {
                cmbOrigem.addItem(a);
                cmbDestino.addItem(a);
            }
        }
        try {
            for (VooTO v : VooDAL.getInstance().getAll()) {
                cmbVoo.addItem(v);
            }
        } catch (SQLException sex) {
            System.out.println("SQLE " + sex.toString());
        }

    }

    public FrmTrecho() {
        initComponents();
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        txtBusca.setText("");
        atualizaTabelaTrechos();
        preencheCombos();
        txtIdTrecho.setEnabled(false);

        //Onclick no JTable
        tblTrechos.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                atualizaDadosAreaInferior();
            }
        });

        //Change no JTable
        tblTrechos.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                atualizaDadosAreaInferior();
            }
        });

    }

    private void atualizaDadosAreaInferior() {
        txtIdTrecho.setEnabled(false);
        int row = tblTrechos.getSelectedRow();
 
        if (row >= 0) {
            Integer id_trecho = Integer.parseInt(tblTrechos.getValueAt(row, 0).toString());
            TrechoTO trechoTemp = TrechoDAL.findById(id_trecho.intValue());
            if (trechoTemp != null) {
                txtIdTrecho.setText(String.valueOf(trechoTemp.getIdTrecho()));
                chkSeg.setSelected(trechoTemp.isSeg());
                chkTer.setSelected(trechoTemp.isTer());
                chkQua.setSelected(trechoTemp.isQua());
                chkQui.setSelected(trechoTemp.isQui());
                chkSex.setSelected(trechoTemp.isSex());
                chkSab.setSelected(trechoTemp.isSab());
                chkDom.setSelected(trechoTemp.isDom());
                txtHorario.setText(trechoTemp.getHorario());
            }

            for (int i = 0; i < cmbOrigem.getItemCount(); i++) {
                if (((AeroportoTO) cmbOrigem.getItemAt(i)).getCodIATA().trim().equals(trechoTemp.getOrigem().trim())) {
                    cmbOrigem.setSelectedIndex(i);
                }
            }

            for (int i = 0; i < cmbDestino.getItemCount(); i++) {
                if (((AeroportoTO) cmbDestino.getItemAt(i)).getCodIATA().trim().equals(trechoTemp.getDestino().trim())) {
                    cmbDestino.setSelectedIndex(i);
                }
            }

            for (int i = 0; i < cmbVoo.getItemCount(); i++) {
                VooTO v = (VooTO) cmbVoo.getItemAt(i);
                if (v.getNumVoo().trim().equals(trechoTemp.getNumVoo().trim()) && v.getCompanhia().getSigla().trim().equals(trechoTemp.getSiglaComp().trim())) {
                    cmbVoo.setSelectedIndex(i);
                }
            }
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

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTrechos = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        txtBusca = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtIdTrecho = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        cmbVoo = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        cmbOrigem = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        cmbDestino = new javax.swing.JComboBox();
        chkSeg = new javax.swing.JCheckBox();
        chkTer = new javax.swing.JCheckBox();
        chkQua = new javax.swing.JCheckBox();
        chkQui = new javax.swing.JCheckBox();
        chkSex = new javax.swing.JCheckBox();
        chkSab = new javax.swing.JCheckBox();
        chkDom = new javax.swing.JCheckBox();
        btnCadastrarInstancia = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txtHorario = new javax.swing.JFormattedTextField();
        btnSalvar = new javax.swing.JButton();
        btnNovo = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Trecho");

        tblTrechos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID Trecho", "Sigla da Companhia", "Número do Vôo", "Origem", "Destino", "Horário", "Dia"
            }
        ));
        jScrollPane1.setViewportView(tblTrechos);

        jLabel2.setText(" Busca");

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        jLabel3.setText("ID");

        jLabel4.setText("Vôo");

        cmbVoo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel5.setText("Origem");

        cmbOrigem.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel6.setText("Destino");

        cmbDestino.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        chkSeg.setText("Seg");

        chkTer.setText("Ter");

        chkQua.setText("Qua");

        chkQui.setText("Qui");

        chkSex.setText("Sex");

        chkSab.setText("Sab");

        chkDom.setText("Dom");

        btnCadastrarInstancia.setText("Cadastrar Instância");
        btnCadastrarInstancia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarInstanciaActionPerformed(evt);
            }
        });

        jLabel7.setText("Horário");

        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        btnNovo.setText("Novo");
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(205, 205, 205)
                                .addComponent(jLabel1))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(22, 22, 22)
                                .addComponent(txtBusca, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBuscar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnCadastrarInstancia)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(txtHorario, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(chkSeg)
                                        .addComponent(chkQui))
                                    .addGap(18, 18, 18)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(chkSex)
                                            .addGap(18, 18, 18)
                                            .addComponent(chkSab))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(chkTer)
                                            .addGap(18, 18, 18)
                                            .addComponent(chkQua)))
                                    .addGap(22, 22, 22))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel4)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(cmbVoo, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(chkDom)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel7)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtIdTrecho, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnNovo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSalvar))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cmbDestino, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cmbOrigem, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBusca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar)
                    .addComponent(jLabel2)
                    .addComponent(btnCadastrarInstancia))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cmbVoo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(cmbOrigem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkSeg)
                    .addComponent(chkTer)
                    .addComponent(jLabel6)
                    .addComponent(cmbDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkQua))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chkQui)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(chkSab)
                        .addComponent(chkSex)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkDom)
                    .addComponent(jLabel7)
                    .addComponent(txtHorario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSalvar)
                    .addComponent(btnNovo)
                    .addComponent(txtIdTrecho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        atualizaTabelaTrechos();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnCadastrarInstanciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarInstanciaActionPerformed
        if (tblTrechos.getRowCount() > 0) {
            int pos = tblTrechos.getSelectedRow();
            Integer id_trecho = Integer.parseInt(tblTrechos.getValueAt(pos, 0).toString());
            FrmCadInstancia frmCadInstancia = new telas.crud.FrmCadInstancia();
            frmCadInstancia.setLocationRelativeTo(null);
            frmCadInstancia.setVisible(true);
            frmCadInstancia.txtIdTrecho.setText(String.valueOf(id_trecho));
        } else {
            JOptionPane.showMessageDialog(null, "Oreia, precisa selecionar pelo menos 1 item!");
        }
    }//GEN-LAST:event_btnCadastrarInstanciaActionPerformed

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        //txtIdTrecho.setEnabled(true);
        clearAllFields();
    }//GEN-LAST:event_btnNovoActionPerformed

    private void clearAllFields() {
        txtIdTrecho.setText("");
        chkSeg.setSelected(false);
        chkTer.setSelected(false);
        chkQua.setSelected(false);
        chkQui.setSelected(false);
        chkSex.setSelected(false);
        chkSab.setSelected(false);
        chkDom.setSelected(false);
        cmbVoo.setSelectedIndex(0);
        cmbOrigem.setSelectedIndex(0);
        cmbDestino.setSelectedIndex(0);
    }

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        //Verifico se é novo ou é se UPDATE
        TrechoTO t = new TrechoTO();
        t.setSiglaComp(((VooTO) cmbVoo.getSelectedItem()).getCompanhia().getSigla());
        t.setNumVoo(((VooTO) cmbVoo.getSelectedItem()).getNumVoo());
        t.setOrigem(((AeroportoTO) cmbOrigem.getSelectedItem()).getCodIATA());
        t.setDestino(((AeroportoTO) cmbDestino.getSelectedItem()).getCodIATA());
        t.setHorario(txtHorario.getText());
        t.setSeg(chkSeg.isSelected());
        t.setTer(chkTer.isSelected());
        t.setQua(chkQua.isSelected());
        t.setQui(chkQui.isSelected());
        t.setSex(chkSex.isSelected());
        t.setSab(chkSab.isSelected());
        t.setDom(chkDom.isSelected());
        if (txtIdTrecho.getText().trim().length() == 0) {
            try {
                TrechoDAL.salvar(t);
                txtIdTrecho.setText(String.valueOf(t.getIdTrecho()));
                JOptionPane.showMessageDialog(null, "Trecho inserido com sucesso.");
            } catch (Exception ex) {
                System.out.println(ex.toString());
                JOptionPane.showMessageDialog(null, "Erro ao inserir. Verificar Log");
            }
        } else {
            t.setIdTrecho(Integer.parseInt(txtIdTrecho.getText()));
            TrechoDAL.update(t);
            JOptionPane.showMessageDialog(null, "Trecho atualizado com sucesso.");
        }
        atualizaTabelaTrechos();
    }//GEN-LAST:event_btnSalvarActionPerformed

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
            java.util.logging.Logger.getLogger(FrmTrecho.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmTrecho.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmTrecho.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmTrecho.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JFrame f = new FrmTrecho();
                f.setLocationRelativeTo(null);
                f.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCadastrarInstancia;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JCheckBox chkDom;
    private javax.swing.JCheckBox chkQua;
    private javax.swing.JCheckBox chkQui;
    private javax.swing.JCheckBox chkSab;
    private javax.swing.JCheckBox chkSeg;
    private javax.swing.JCheckBox chkSex;
    private javax.swing.JCheckBox chkTer;
    private javax.swing.JComboBox cmbDestino;
    private javax.swing.JComboBox cmbOrigem;
    private javax.swing.JComboBox cmbVoo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblTrechos;
    private javax.swing.JTextField txtBusca;
    private javax.swing.JFormattedTextField txtHorario;
    private javax.swing.JTextField txtIdTrecho;
    // End of variables declaration//GEN-END:variables
}
