/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * CInE.java
 *
 * Created on 24/06/2011, 14:28:46
 */

package Visual;

/**
 *
 * @author Caio
 */
public class Erro extends javax.swing.JFrame {

    private String codErro;
    private Integer linhas = 1;
    private Integer colunas;

    /** Creates new form CInE */
    public Erro() {
        codErro = "Erro";
	colunas = codErro.length();
        setVisible(true);
        initComponents();
    }

    public Erro(String s) {
        codErro = s;
	calcSize();
        setVisible(true);
        initComponents();
    }
    
    private void calcSize(){
        int i, count;
        for(i = count = 0; i < codErro.length(); i++, count++) {
            if(codErro.charAt(i) == '\n'){
		linhas++;
                if(colunas < count) colunas = count;
                count = 0;
            }
        }
        if(colunas < count) colunas = count;

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnClose = new javax.swing.JButton();
        txtErro = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        setName("Form"); // NOI18N

        btnClose.setName("btnClose"); // NOI18N
        btnClose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCloseMouseClicked(evt);
            }
        });

        txtErro.setColumns(colunas);
        txtErro.setEditable(false);
        txtErro.setRows(linhas);
        txtErro.setText(codErro);
        txtErro.setAutoscrolls(false);
        txtErro.setName("txtErro"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnClose, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtErro))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtErro)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnClose)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCloseMouseClicked
        dispose();
    }//GEN-LAST:event_btnCloseMouseClicked

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Erro().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClose;
    private javax.swing.JTextArea txtErro;
    // End of variables declaration//GEN-END:variables

}