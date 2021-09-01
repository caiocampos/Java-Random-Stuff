/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dal.ReservaDAL;
import model.ReservaTO;

/**
 *
 * @author William
 */
public class FrmReservaController {

    public static String getNomeReservaByCod(int cod_res) {
        ReservaTO r = ReservaDAL.findByCod(cod_res);
        if (r != null) {
            return r.getNome();
        }
        return "";
    }
}
