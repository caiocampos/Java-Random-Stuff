/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dal.AssentoDAL;
import java.util.ArrayList;
import model.AeronaveTO;
import model.AssentoTO;

/**
 *
 * @author Caio
 */
public class FrmAssentoController {

    public void salvar(Character fila, Integer num, String codAN, String classe) {
	if (codAN.equals("") || classe.length() > 10) {
	    return;
	}
	AssentoTO assento = new AssentoTO();
	AeronaveTO aeronave = new AeronaveTO();
	assento.setFila(fila);
	assento.setNumero(num);
	aeronave.setCodAeronave(codAN);
	assento.setAeronave(aeronave);
	assento.setClasse(classe);
	if (AssentoDAL.getInstance().findOne(fila, num, codAN) == null) {
	    AssentoDAL.getInstance().salvar(assento);
	    return;
	}
	AssentoDAL.getInstance().atualizar(assento);
    }

    public void deletar(Character fila, Integer num, String codAN) {
	AssentoTO assento = new AssentoTO();
	AeronaveTO aeronave = new AeronaveTO();
	assento.setFila(fila);
	assento.setNumero(num);
	if (codAN.equals("")) {
	    codAN = "     ";
	}
	aeronave.setCodAeronave(codAN);
	assento.setAeronave(aeronave);
	AssentoDAL.getInstance().deletar(assento);
    }

    public ArrayList<AssentoTO> find(String codAN) {
	return AssentoDAL.getInstance().findAll(codAN);
    }
    
    public String[] getAeronaves() {
	ArrayList<AeronaveTO> comps = new FrmAeronaveController().find("");
	String[] res = new String[comps.size()];
	for(int i = 0; i < comps.size(); i++) {
	    res[i] = comps.get(i).getCodAeronave();
	}
	return res;
    }
}
