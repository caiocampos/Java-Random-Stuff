/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dal.AeronaveDAL;
import java.util.ArrayList;
import model.AeronaveTO;

/**
 *
 * @author Caio
 */
public class FrmAeronaveController {

    public void salvar(String cod, String fab, String mod, String sub) {
	if (cod.equals("")) {
	    return;
	}
	AeronaveTO aeronave = new AeronaveTO();
	AeronaveTO aeronaveSub = new AeronaveTO();
	aeronave.setCodAeronave(cod);
	aeronave.setFabricante(fab);
	aeronave.setModelo(mod);
	aeronaveSub.setCodAeronave(sub);
	aeronave.setSubstituta(aeronaveSub);
	if (AeronaveDAL.getInstance().findOne(cod) == null) {
	    AeronaveDAL.getInstance().salvar(aeronave);
	    return;
	}
	AeronaveDAL.getInstance().atualizar(aeronave);
    }

    public void deletar(String cod) {
	AeronaveTO aeronave = new AeronaveTO();
	aeronave.setCodAeronave(cod);
	AeronaveDAL.getInstance().deletar(aeronave);
    }

    public ArrayList<AeronaveTO> find(String cod) {
	return AeronaveDAL.getInstance().findAll(cod);
    }
    
    public String[] getSubstitutas() {
	ArrayList<AeronaveTO> comps = AeronaveDAL.getInstance().findAllSubs();
	String[] res = new String[comps.size()+1];
	res[0] = null;
	for(int i = 1; i < comps.size() + 1; i++) {
	    res[i] = comps.get(i-1).getCodAeronave();
	}
	return res;
    }
}
