/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dal.VooDAL;
import java.util.ArrayList;
import model.CompanhiaTO;
import model.VooTO;

/**
 *
 * @author Caio
 */
public class FrmVooController {
    
    public void salvar(String siglaComp, String numVoo) {
	if (numVoo.equals("")) {
	    return;
	}
	VooTO voo = new VooTO();
	CompanhiaTO companhia = new CompanhiaTO();
	companhia.setSigla(siglaComp);
	voo.setCompanhia(companhia);
	voo.setNumVoo(numVoo);
	VooDAL.getInstance().salvar(voo);
    }

    public void deletar(String siglaComp, String numVoo) {
	VooTO voo = new VooTO();
	CompanhiaTO companhia = new CompanhiaTO();
	companhia.setSigla(siglaComp);
	voo.setCompanhia(companhia);
	voo.setNumVoo(numVoo);
	VooDAL.getInstance().deletar(voo);
    }

    public ArrayList<VooTO> find(String siglaComp) {
	return VooDAL.getInstance().findAll(siglaComp);
    }
    
    public String[] getCompanhias() {
	ArrayList<CompanhiaTO> comps = new FrmCompanhiaController().find("");
	String[] res = new String[comps.size()];
	for(int i = 0; i < comps.size(); i++) {
	    res[i] = comps.get(i).getSigla();
	}
	return res;
    }
}
