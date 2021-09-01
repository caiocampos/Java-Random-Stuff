package bd2work;

import persistencia.*;
import telas.FrmLogin;

/**
 *
 * @author William
 */
public class BD2Work {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        PGSql.getInstance().exec("SET search_path TO aeroporto;");
        //Abre a tela de Login
        FrmLogin frmLogin = new FrmLogin();
        frmLogin.setLocationRelativeTo(null);
        frmLogin.setVisible(true);
    }
}
