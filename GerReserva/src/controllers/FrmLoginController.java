/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

/**
 *
 * @author William
 */
public class FrmLoginController {
    public boolean fazerLogin(String login, String senha){
        model.LoginTO loginTO;
        try {
            loginTO = dal.LoginDAL.getInstance().findOneByUsername(login);
            return loginTO.getUsername().equals(login) && loginTO.getSenha().equals(senha);
        }catch(Exception ex){
            return false;
        }
    }
}
