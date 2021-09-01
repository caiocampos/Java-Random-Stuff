/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package telas.crud;

import controllers.FrmReservaController;
import dal.AeronaveDAL;
import dal.AeroportoDAL;
import dal.DisponibilizaDAL;
import dal.ReservaDAL;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.AeroportoTO;
import model.AssentoTO;
import model.DisponibilizaTO;
import model.InstanciaTO;
import model.ReservaTO;
import model.TrechoTO;

/**
 *
 * @author William
 */
class Item {

    private String id;
    private String description;

    public Item(String id, String description) {
        this.id = id;
        this.description = description;
    }

    public String getId() {
        return this.id;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return this.description;
    }
}

public class FrmReserva extends TrabalhoFrame {

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
            java.util.logging.Logger.getLogger(FrmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmReserva().setVisible(true);
            }
        });
    }

    private void preencheComboTrechos() {
        try {
            cmbOrigem.removeAllItems();
            cmbDestino.removeAllItems();
        } catch (Exception ex) {
        }
        ArrayList<AeroportoTO> aeroportos = AeroportoDAL.getInstance().findAll("");
        for (AeroportoTO a : aeroportos) {
            cmbOrigem.addItem(a.getCodIATA());
            cmbDestino.addItem(a.getCodIATA());
        }
        cmbOrigem.setSelectedIndex(0);
        cmbDestino.setSelectedIndex(0);
    }

    public FrmReserva() {


        initComponents();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        preencheComboTrechos();
        atualizaTabela();

        btnReservar.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int val = tblTrechos.getSelectedRow();

                if (tblTrechos.getModel().getRowCount() == 0) {
                    JOptionPane.showMessageDialog(null, "Você deve selecionar pelo menos um trecho", "Atenção", JOptionPane.WARNING_MESSAGE);
                } else {
                    String nome = txtNomeReserva.getText().trim();
                    if (nome.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Preencha o nome", "Atenção", JOptionPane.WARNING_MESSAGE);
                    } else {
                        String fila, numero, aeronave, data_viagem, id_trecho;
                        int pos = tblTrechos.getSelectedRow();
                        fila = tblTrechos.getValueAt(pos, 7).toString();
                        numero = tblTrechos.getValueAt(pos, 8).toString();
                        aeronave = tblTrechos.getValueAt(pos, 9).toString();
                        data_viagem = tblTrechos.getValueAt(pos, 6).toString();
                        id_trecho = tblTrechos.getValueAt(pos, 0).toString();

                        //Verifico se já não existe alguém reservado para este voo!)
                        if (tblTrechos.getValueAt(pos, 10).toString().trim().length() != 0) {
                            JOptionPane.showMessageDialog(null, "Reserva não está disponível!", "Atenção", JOptionPane.WARNING_MESSAGE);
                            return;
                        }

                        try {
                            ReservaTO reservaTO = ReservaDAL.createReserva(nome);
                            DisponibilizaTO d = new DisponibilizaTO();
                            TrechoTO t = new TrechoTO();
                            t.setIdTrecho(Integer.parseInt(id_trecho));
                            AssentoTO a = new AssentoTO();
                            a.setFila(fila.toCharArray()[0]);
                            a.setNumero(Integer.parseInt(numero));
                            a.setAeronave(AeronaveDAL.getInstance().findOne(aeronave));
                            d.setAssento(a);
                            InstanciaTO ins = new InstanciaTO();
                            ins.setDate(data_viagem);
                            ins.setTrecho(t);
                            d.setInstancia(ins);
                            d.setCodReserva(reservaTO.getCod_reserva());
                            DisponibilizaDAL.update(d);
                            atualizaTabela();
                            txtNomeReserva.setText("");
                        } catch (SQLException ex) {
                            System.out.println(ex.getMessage());
                        }
                    }
                }

            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnReservar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        cmbOrigem = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        cmbDestino = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTrechos = new javax.swing.JTable();
        btnFiltrar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtNomeReserva = new javax.swing.JTextField();
        btnDeletarReserva = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnReservar.setText("Reservar");
        btnReservar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReservarActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel3.setText("Reserva");

        jLabel1.setText("Origem:");

        cmbOrigem.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbOrigem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbOrigemActionPerformed(evt);
            }
        });

        jLabel2.setText("Destino");

        cmbDestino.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbDestino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbDestinoActionPerformed(evt);
            }
        });

        tblTrechos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblTrechos);

        btnFiltrar.setText("Filtrar");
        btnFiltrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltrarActionPerformed(evt);
            }
        });

        jLabel4.setText("Nome Reserva:");

        btnDeletarReserva.setText("Deletar");
        btnDeletarReserva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeletarReservaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 876, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 530, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNomeReserva, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnReservar))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cmbOrigem, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cmbDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnFiltrar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnDeletarReserva)))
                        .addGap(0, 248, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cmbOrigem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(cmbDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFiltrar)
                    .addComponent(btnDeletarReserva))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnReservar)
                    .addComponent(jLabel4)
                    .addComponent(txtNomeReserva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jLabel3.getAccessibleContext().setAccessibleName("Trecho");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbOrigemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbOrigemActionPerformed
    }//GEN-LAST:event_cmbOrigemActionPerformed

    private void cmbDestinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbDestinoActionPerformed
    }//GEN-LAST:event_cmbDestinoActionPerformed

    private void btnFiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltrarActionPerformed
        atualizaTabela();
    }//GEN-LAST:event_btnFiltrarActionPerformed

    private void btnReservarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReservarActionPerformed
    }//GEN-LAST:event_btnReservarActionPerformed

    private void btnDeletarReservaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeletarReservaActionPerformed
        if (tblTrechos.getModel().getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Você deve selecionar alguma reserva.", "Atenção", JOptionPane.WARNING_MESSAGE);
        } else {
            String fila, numero, aeronave, data_viagem, id_trecho;
            int pos = tblTrechos.getSelectedRow();
            fila = tblTrechos.getValueAt(pos, 7).toString();
            numero = tblTrechos.getValueAt(pos, 8).toString();
            aeronave = tblTrechos.getValueAt(pos, 9).toString();
            data_viagem = tblTrechos.getValueAt(pos, 6).toString();
            id_trecho = tblTrechos.getValueAt(pos, 0).toString();
            DisponibilizaTO reserva = DisponibilizaDAL.find(fila.toCharArray()[0], Integer.parseInt(numero), aeronave, data_viagem, Integer.parseInt(id_trecho));
            if (reserva.getCodReserva() > 0) {
                DisponibilizaDAL.removeReserva(reserva);
                atualizaTabela();
                JOptionPane.showMessageDialog(null, "Reserva removida com sucesso!", "Atenção", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Não existe reserva para este voo!", "Atenção", JOptionPane.WARNING_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnDeletarReservaActionPerformed

    private void atualizaTabela() {
        DefaultTableModel model = new DefaultTableModel(new String[]{"ID",
                    "Cia", "N. Voo", "Orig.", "Dest.", "Hora", "Data", "Fila",
                    "Cadeira", "Aeronave", "Nome Reserva"}, 0) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };

        String origem = (String) cmbOrigem.getSelectedItem();
        String destino = (String) cmbDestino.getSelectedItem();

        //Preenche os dados
        ArrayList<DisponibilizaTO> trechos = DisponibilizaDAL.find(origem, destino);
        for (DisponibilizaTO d : trechos) {
            try {
                model.addRow(new String[]{
                            String.valueOf(d.getInstancia().getTrecho().getIdTrecho()),
                            d.getInstancia().getTrecho().getSiglaComp(),
                            d.getInstancia().getTrecho().getNumVoo(),
                            d.getInstancia().getTrecho().getOrigem(),
                            d.getInstancia().getTrecho().getDestino(),
                            d.getInstancia().getTrecho().getHorario(),
                            d.getInstancia().getDate(),
                            String.valueOf(d.getAssento().getFila()),
                            String.valueOf(d.getAssento().getNumero()),
                            d.getAssento().getAeronave().getCodAeronave(),
                            FrmReservaController.getNomeReservaByCod(d.getCodReserva())
                        });
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }

        tblTrechos.setModel(model);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDeletarReserva;
    private javax.swing.JButton btnFiltrar;
    private javax.swing.JButton btnReservar;
    private javax.swing.JComboBox cmbDestino;
    private javax.swing.JComboBox cmbOrigem;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblTrechos;
    private javax.swing.JTextField txtNomeReserva;
    // End of variables declaration//GEN-END:variables
}
