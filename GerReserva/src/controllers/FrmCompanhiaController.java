/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dal.CompanhiaDAL;
import java.util.ArrayList;
import model.CompanhiaTO;

/**
 *
 * @author Caio
 */
public class FrmCompanhiaController {
    public void salvar(String sigla, String nome, String nome_fantasia, String pais) {
	if (sigla.equals("")) {
	    return;
	}
	CompanhiaTO companhia = new CompanhiaTO();
	companhia.setSigla(sigla);
	companhia.setNome(nome);
	companhia.setNomeFantasia(nome_fantasia);
	companhia.setPais(pais);
	if (CompanhiaDAL.getInstance().findOne(sigla) == null) {
	    CompanhiaDAL.getInstance().salvar(companhia);
	    return;
	}
	CompanhiaDAL.getInstance().atualizar(companhia);
    }

    public void deletar(String sigla) {
	CompanhiaTO companhia = new CompanhiaTO();
	companhia.setSigla(sigla);
	CompanhiaDAL.getInstance().deletar(companhia);
    }

    public ArrayList<CompanhiaTO> find(String sigla) {
	return CompanhiaDAL.getInstance().findAll(sigla);
    }
}
