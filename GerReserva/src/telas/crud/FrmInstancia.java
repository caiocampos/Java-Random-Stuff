/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package telas.crud;

import dal.DisponibilizaDAL;
import dal.InstanciaDAL;
import dal.TrechoDAL;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import model.DisponibilizaTO;
import model.InstanciaTO;
import model.TrechoTO;
import persistencia.PGSql;

/**
 *
 * @author William
 */
public class FrmInstancia extends TrabalhoFrame {

    /**
     * Creates new form FrmDisponibiliza
     */
    MaskFormatter maskDataBr = null;

    private void atualizaTabelaTrechos() {
        DefaultTableModel model = new DefaultTableModel(new String[]{"ID", "Comp.", "N. Voo", "Origem", "Destino", "Horário"}, 0) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        ArrayList<TrechoTO> trechos = TrechoDAL.findAllQuery(txtBuscaTrecho.getText());
        for (TrechoTO t : trechos) {
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

    private void atualizaTabelaInstancias() {
        DefaultTableModel model = new DefaultTableModel(new String[]{"ID", "Origem", "Destino", "Data", "Horário"}, 0) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };

        if (txtIdTrechoSelecionado.getText().trim().length() > 0) {
            ArrayList<InstanciaTO> instancias = InstanciaDAL.getInstance().findByIdTrecho(Integer.parseInt(txtIdTrechoSelecionado.getText().trim()));
            for (InstanciaTO i : instancias) {
                model.addRow(new String[]{
                            String.valueOf(i.getTrecho().getIdTrecho()),
                            i.getTrecho().getOrigem(),
                            i.getTrecho().getDestino(),
                            i.getDate(),
                            i.getTrecho().getHorario()
                        });
            }
        }

        tblInstancias.setModel(model);
    }

    /*private void preencheComboAeronaves(){
     cmbAeronave.removeAllItems();
     ArrayList<AeronaveTO> aeronaves = AeronaveDAL.getInstance().findAll("");
     if(aeronaves != null){
     for(AeronaveTO a : aeronaves){
     cmbAeronave.addItem(a);
     }
     }
     }//*/
    public FrmInstancia() {
        try {
            maskDataBr = new MaskFormatter("##/##/####");
            maskDataBr.setPlaceholder("_");
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        initComponents();

        atualizaTabelaTrechos();
        atualizaTabelaInstancias();
        //preencheComboAeronaves();

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

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTrechos = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        txtBuscaTrecho = new javax.swing.JTextField();
        btnFiltraTrecho = new javax.swing.JButton();
        txtIdTrechoSelecionado = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblInstancias = new javax.swing.JTable();
        btnNovo = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtDataInstancia = new javax.swing.JFormattedTextField();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Selecione um trecho");

        tblTrechos.setModel(new javax.swing.table.DefaultTableModel(
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
        tblTrechos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTrechosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblTrechos);

        jLabel2.setText("Busca");

        btnFiltraTrecho.setText("Filtrar");
        btnFiltraTrecho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltraTrechoActionPerformed(evt);
            }
        });

        txtIdTrechoSelecionado.setEditable(false);

        jLabel3.setText("Instâncias");

        tblInstancias.setModel(new javax.swing.table.DefaultTableModel(
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
        tblInstancias.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblInstanciasMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblInstancias);

        btnNovo.setText("Cadastrar Nova");
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });

        btnSalvar.setText("Salvar Selecionada");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        btnExcluir.setText("Excluir Instância Selecionada");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        jLabel4.setText("Data da Instância");

        txtDataInstancia.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter()));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(73, 73, 73)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(txtBuscaTrecho, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnFiltraTrecho)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtIdTrechoSelecionado, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtDataInstancia, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(btnNovo)
                                    .addComponent(btnSalvar))
                                .addGap(228, 228, 228))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnExcluir)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(txtBuscaTrecho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFiltraTrecho)
                    .addComponent(txtIdTrechoSelecionado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtDataInstancia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(btnNovo)
                        .addGap(18, 18, 18)
                        .addComponent(btnSalvar)
                        .addGap(18, 18, 18)
                        .addComponent(btnExcluir))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnFiltraTrechoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltraTrechoActionPerformed
        atualizaTabelaTrechos();
    }//GEN-LAST:event_btnFiltraTrechoActionPerformed

    private void tblTrechosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTrechosMouseClicked
        int pos = tblTrechos.getSelectedRow();
        String id_tbl = (String) tblTrechos.getValueAt(pos, 0);
        txtIdTrechoSelecionado.setText(id_tbl);
        atualizaTabelaInstancias();
    }//GEN-LAST:event_tblTrechosMouseClicked

    private void tblInstanciasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblInstanciasMouseClicked
        int row = tblInstancias.getSelectedRow();
        String data = (String) tblInstancias.getValueAt(row, 3);
        if (data != null && data.trim().length() > 0) {
            InstanciaTO instanciaTO = InstanciaDAL.getInstance().find(data, Integer.parseInt(txtIdTrechoSelecionado.getText()));
            if (instanciaTO != null) {
                try {
                    String dataBRInstancia = dtfBR.format(dtfEUA.parse(data));
                    txtDataInstancia.setText(dataBRInstancia);
                } catch (ParseException ex) {
                    System.out.println("Deu pau pra converter a data!\n" + ex.toString());
                }
            } else {
                JOptionPane.showMessageDialog(null, "Instância não pôde ser encontrada.");
            }
        }
    }//GEN-LAST:event_tblInstanciasMouseClicked

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        int ret = JOptionPane.showConfirmDialog(null, "Deseja realmente inserir esta instância?");
        if (ret == JOptionPane.OK_OPTION) {
            //Cadastrar
            try {
                String dataInstancia = dtfEUA.format(dtfBR.parse(txtDataInstancia.getText().trim()));
                int id_trecho = Integer.parseInt(txtIdTrechoSelecionado.getText().trim());
                try {
                    InstanciaDAL.getInstance().create(id_trecho, dataInstancia);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.toString());
                }
                txtDataInstancia.setText("");
            } catch (ParseException pex) {
                JOptionPane.showMessageDialog(null, "Data parece estar em formato incorreto!");
                System.out.println("Exception: " + pex.toString());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Pane ao cadastrar instancia. Erro: " + ex.toString());
            }
            atualizaTabelaInstancias();
        } else {
            JOptionPane.showMessageDialog(null, "Operação abortada!");
        }
    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        JOptionPane.showMessageDialog(null, "Sou um botão inútil, data_viagem e id_trecho são chaves e não devem ser editados a fim de manter a integridade do sistema ( :");
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        int row = tblInstancias.getSelectedRow();
        String data = (String) tblInstancias.getValueAt(row, 3);
        int id_trecho = Integer.parseInt(txtIdTrechoSelecionado.getText().trim());
        if (JOptionPane.showConfirmDialog(null, "Deseja realmente excluir esta instância?") == JOptionPane.OK_OPTION) {
            try {
                //Verifico se tem voos disponibilizados e reservados. Caso sim, apenas aborto!
                if (DisponibilizaDAL.getDisponibilizadosReservados(id_trecho, data) > 0) {
                    JOptionPane.showMessageDialog(null, "Existem lugares "
                            + "disponibilizados E RESERVADOS para esta "
                            + "instância.\nPor favor, remova todos antes de "
                            + "continuar!\n\nOperação abortada!");
                } else {
                    //Verifico se tem voos disponibilizados vazios. Se tiver, simplesmente confirmo e deleto!
                    if (DisponibilizaDAL.getDisponibilizados(id_trecho, data) > 0) {
                        if (JOptionPane.showConfirmDialog(null, "Existem lugares "
                                + "disponibilizados para esta instância.\n"
                                + "Para continuar, será necessário excluí-los!\n\n\n"
                                + "Posso continuar com esta ação?") == JOptionPane.OK_OPTION) {
                            //Deleto os disponibilizas e depois a instância

                            //Busco as disponibilizas por data e id
                            ArrayList<DisponibilizaTO> disponibilizados = DisponibilizaDAL.findDisponibilizados(data, id_trecho);
                            for (DisponibilizaTO d : disponibilizados) {
                                try {
                                    DisponibilizaDAL.delete(d);
                                } catch (Exception ex) {
                                    System.out.println(ex.toString());
                                }
                            }

                            //Deleto a instância e atualizo a tela
                            try {
                                InstanciaDAL.getInstance().delete(InstanciaDAL.getInstance().find(data, id_trecho));
                            } catch (Exception ex) {
                                JOptionPane.showMessageDialog(null, ex.toString());
                            }
                            atualizaTabelaInstancias();

                        } else {
                            JOptionPane.showMessageDialog(null, "Operação de exclusão abortada!");
                        }
                    } else {
                        //Deleto a instância e atualizo a tela
                        try {
                            InstanciaDAL.getInstance().delete(InstanciaDAL.getInstance().find(data, id_trecho));
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, ex.toString());
                        }
                        atualizaTabelaInstancias();
                    }
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Pane no sistema alguém me desconfigurou!\n\nErro ao criar statemente!\n\nErro: " + ex.toString());
            }
        }
        txtDataInstancia.setText("");
        txtBuscaTrecho.requestFocus();
    }//GEN-LAST:event_btnExcluirActionPerformed

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
            java.util.logging.Logger.getLogger(FrmInstancia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmInstancia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmInstancia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmInstancia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmInstancia().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnFiltraTrecho;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable tblInstancias;
    private javax.swing.JTable tblTrechos;
    private javax.swing.JTextField txtBuscaTrecho;
    private javax.swing.JFormattedTextField txtDataInstancia;
    private javax.swing.JTextField txtIdTrechoSelecionado;
    // End of variables declaration//GEN-END:variables
}
