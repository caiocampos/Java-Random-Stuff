/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package telas.crud;

import java.awt.HeadlessException;
import java.text.SimpleDateFormat;
import javax.swing.JFrame;

/**
 *
 * @author william.okano
 */
public class TrabalhoFrame extends JFrame {
    SimpleDateFormat dtfBR = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat dtfEUA = new SimpleDateFormat("yyyy-MM-dd");

    public TrabalhoFrame() throws HeadlessException {
        dtfBR.setLenient(true);
        dtfEUA.setLenient(true);
    }
    
}
