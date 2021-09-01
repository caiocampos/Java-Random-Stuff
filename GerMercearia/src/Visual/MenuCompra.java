/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * MenuVenda.java
 *
 * Created on 25/06/2011, 10:16:39
 */

package Visual;

import Modelo.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Caio
 */
public class MenuCompra extends javax.swing.JFrame {

    private Compra c;
    private Double valTotal;
    private ArrayList<Produto> aux = new ArrayList<Produto>();
    private Produto sel;
    
    private void copiaLP(Compra compra){
        Produto cp;
        for(Produto p : compra.getLP()){
            cp = new Produto();
            cp.setNome(p.getNome());
            cp.setPreco(p.getPreco());
            cp.setTipo(p.getTipo());
            cp.setQuantidade(p.getQuantidade());
            aux.add(cp);
        }
        cp = null;
    }

    private Produto findProduto(String s){
        for(Produto obj : aux){
            if(obj.getNome().equals(s)) return obj;
        }
        return null;
    }

    private void removeProduto(String s){
        int i = 0;
        for(Produto obj : aux){
            if(obj.getNome().equals(s)){
                aux.remove(i);
                plUpdate();
                return;
            }
            i++;
        }
    }

    private void calcPreco(){
        valTotal = 0.0;
        for(Produto p : aux){
            valTotal += p.getQuantidade() * p.getPreco();
        }
    }

    private void elUpdate(){
        DefaultTableModel modelo = (DefaultTableModel)listE.getModel();
        boolean teste;
        String a, t;
        int j;
        a = findTE.getText();

        for(j = modelo.getRowCount() - 1; j >= 0; j--) modelo.removeRow(j);

        j = 0;
        for(Produto obj : POOView.getEstoque()){
            t = obj.getNome();
            teste = false;
            for(int i = 0; i < a.length(); i++){
                if(a.charAt(i) != t.charAt(i) || a.length() > t.length()){
                    teste = true;
                    break;
                }
            }
            if(!teste){
                modelo.addRow(new Object[modelo.getColumnCount()]);
                listE.setValueAt(t,j,0);
                listE.setValueAt(obj.getQuantidade(),j,1);
            }
            j++;
        }
    }

    private void plUpdate(){
        DefaultTableModel modelo = (DefaultTableModel)listP.getModel();
        boolean teste;
        String a, t;
        int j;
        a = findTP.getText();

        for(j = modelo.getRowCount() - 1; j >= 0; j--) modelo.removeRow(j);

        j = 0;
        for(Produto obj : aux){
            t = obj.getNome();
            teste = false;
            for(int i = 0; i < a.length(); i++){
                if(a.charAt(i) != t.charAt(i) || a.length() > t.length()){
                    teste = true;
                    break;
                }
            }
            if(!teste){
                modelo.addRow(new Object[modelo.getColumnCount()]);
                listP.setValueAt(t,j,0);
                listP.setValueAt(obj.getQuantidade(),j,1);
            }
            j++;
        }
    }

    /** Creates new form MenuVenda */
    public MenuCompra() {
        initComponents();
        c = new Compra();
    }

    MenuCompra(Compra compra) {
        copiaLP(compra);
        c = compra;
        calcPreco();
        
        initComponents();
        
        elUpdate();
        plUpdate();

        setVisible(true);
    }

    public String getDataC(){
        String s;
        Integer val;
        val = c.getData().get(GregorianCalendar.DAY_OF_MONTH);
        s = val.toString() + '/';
        val = c.getData().get(GregorianCalendar.MONTH) + 1;
        s += val.toString() + '/';
        val = c.getData().get(GregorianCalendar.YEAR);
        s += val.toString();
        return s;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        precoC = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listP = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        listE = new javax.swing.JTable();
        findTE = new java.awt.TextField();
        findTP = new java.awt.TextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        cadC = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        remC = new javax.swing.JButton();
        qtdP = new javax.swing.JSpinner();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setName("Form"); // NOI18N

        precoC.setEditable(false);
        precoC.setText(valTotal.toString());
        precoC.setName("precoC"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(Modelo.POOApp.class).getContext().getResourceMap(MenuCompra.class);
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        listP.setAutoCreateRowSorter(true);
        listP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome", "Qtd"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class
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
        listP.setName("listP"); // NOI18N
        listP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listPMouseClicked(evt);
            }
        });
        listP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                listPKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(listP);

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        listE.setAutoCreateRowSorter(true);
        listE.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome", "Qtd"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class
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
        listE.setName("listE"); // NOI18N
        listE.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listEMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(listE);

        findTE.setName("findTE"); // NOI18N
        findTE.setText(resourceMap.getString("findTE.text")); // NOI18N
        findTE.addTextListener(new java.awt.event.TextListener() {
            public void textValueChanged(java.awt.event.TextEvent evt) {
                findTETextValueChanged(evt);
            }
        });

        findTP.setName("findTP"); // NOI18N
        findTP.setText(resourceMap.getString("findTP.text")); // NOI18N
        findTP.addTextListener(new java.awt.event.TextListener() {
            public void textValueChanged(java.awt.event.TextEvent evt) {
                findTPTextValueChanged(evt);
            }
        });

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        cadC.setText(resourceMap.getString("cadC.text")); // NOI18N
        cadC.setName("cadC"); // NOI18N
        cadC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cadCMouseClicked(evt);
            }
        });

        jTextField1.setEditable(false);
        jTextField1.setText(c.getCode());
        jTextField1.setName("jTextField1"); // NOI18N

        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N

        jLabel7.setText(resourceMap.getString("jLabel7.text")); // NOI18N
        jLabel7.setName("jLabel7"); // NOI18N

        jTextField2.setEditable(false);
        jTextField2.setText(getDataC());
        jTextField2.setName("jTextField2"); // NOI18N

        remC.setText(resourceMap.getString("remC.text")); // NOI18N
        remC.setName("remC"); // NOI18N
        remC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                remCMouseClicked(evt);
            }
        });

        qtdP.setModel(new javax.swing.SpinnerNumberModel());
        qtdP.setName("qtdP"); // NOI18N
        qtdP.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                qtdPStateChanged(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(findTP, javax.swing.GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE)
                            .addComponent(jLabel3)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 327, Short.MAX_VALUE)
                            .addComponent(findTE, javax.swing.GroupLayout.DEFAULT_SIZE, 327, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(cadC)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(remC)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(qtdP, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(precoC, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(findTP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(findTE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(qtdP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(precoC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(remC)
                            .addComponent(cadC)
                            .addComponent(jButton1))))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void findTPTextValueChanged(java.awt.event.TextEvent evt) {//GEN-FIRST:event_findTPTextValueChanged
        plUpdate();
    }//GEN-LAST:event_findTPTextValueChanged

    private void findTETextValueChanged(java.awt.event.TextEvent evt) {//GEN-FIRST:event_findTETextValueChanged
        elUpdate();
    }//GEN-LAST:event_findTETextValueChanged

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        dispose();
    }//GEN-LAST:event_jButton1MouseClicked

    private void remCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_remCMouseClicked
        if(c != null){
            Fornecedor f = POOView.findFornecedor(c.getFN());
            if(c != null){
                if(f.findCompra(c.getCode()) != null) f.removeCompra(c.getCode());
            }
            POOView.gravarFornecedor();
            dispose();
            POOView.flUpdate();
            return;
        }
    }//GEN-LAST:event_remCMouseClicked

    private void cadCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cadCMouseClicked
        for(Produto p : aux){
            if(p.getQuantidade() <= 0){
                showErro("Alguns campos de produto estão negativos ou zerados");
                dispose();
                return;
            }
        }

        if(c != null){
            for(Produto p : c.getLP()){
                if(findProduto(p.getNome()) == null){
                    POOView.findProduto(p.getNome()).removeProduto(p.getQuantidade());
                }
            }
            int val = 0;
            for(Produto p : aux){
                Produto pa = c.findProduto(p.getNome());
                if(pa == null) val = 0;
                if(pa != null) val = pa.getQuantidade();

                POOView.findProduto(p.getNome()).addProduto(p.getQuantidade()- val);
            }
            c.setLP(aux);
            POOView.gravarFornecedor();
            dispose();
            POOView.flUpdate();
            return;
        }
    }//GEN-LAST:event_cadCMouseClicked

    private void listEMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listEMouseClicked
        String s = (String)listE.getValueAt(listE.getSelectedRow(), 0);
        if(evt.getClickCount() == 2 && s != null){
            Produto a = POOView.findProduto(s);
            if(findProduto(a.getNome()) != null) return;
            Produto b = new Produto();

            b.setNome(a.getNome());
            b.setPreco(a.getPreco());
            b.setTipo(false);
            b.setQuantidade(0);

            aux.add(b);
            plUpdate();
        }
    }//GEN-LAST:event_listEMouseClicked

    private void listPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listPMouseClicked
        String s = (String)listP.getValueAt(listP.getSelectedRow(), 0);
        if(s != null){
            sel = findProduto(s);
            int click = evt.getClickCount();
            if(click == 2){
                removeProduto(sel.getNome());
                sel = null;
                return;
            }
            if(click == 1){
                qtdP.setValue((Integer)sel.getQuantidade());
            }
        }
    }//GEN-LAST:event_listPMouseClicked

    private void qtdPStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_qtdPStateChanged
        if(sel != null){
            sel.setQuantidade(((Integer)qtdP.getValue()).intValue());
            calcPreco();
            precoC.setText(((Double)valTotal).toString());
            plUpdate();
        }
    }//GEN-LAST:event_qtdPStateChanged

    private void listPKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_listPKeyPressed
        int val = listP.getSelectedRow();
        int kId = evt.getID();
        String s = "";
        if(kId == java.awt.event.KeyEvent.VK_DOWN){
            if(val <= listP.getRowCount()) val += 1;
        }
        if(kId == java.awt.event.KeyEvent.VK_UP){
            if(val > 0) val -= 1;
        }
        listP.changeSelection(val, 0, false, false);
        s = (String)listP.getValueAt(val, 0);
        sel = findProduto(s);
        if(sel != null) qtdP.setValue((Integer)sel.getQuantidade());
    }//GEN-LAST:event_listPKeyPressed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuCompra().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cadC;
    private java.awt.TextField findTE;
    private java.awt.TextField findTP;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTable listE;
    private javax.swing.JTable listP;
    private javax.swing.JTextField precoC;
    private javax.swing.JSpinner qtdP;
    private javax.swing.JButton remC;
    // End of variables declaration//GEN-END:variables

    private Erro erro;

    public void showErro(String s){
        erro = new Erro(s);
        erro.setLocationRelativeTo(this);
    }
}
