/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dal.AeroportoDAL;
import java.util.ArrayList;
import model.AeroportoTO;

/**
 *
 * @author Caio
 */
public class FrmAeroportoController {

    public void salvar(String iata, String icao, String nome, String cidade, String pais) {
	if (iata.length() != 3) {
	    return;
	}
	AeroportoTO aeroporto = new AeroportoTO();
	aeroporto.setCodIATA(iata);
	aeroporto.setCodICAO(icao);
	aeroporto.setNome(nome);
	aeroporto.setCidade(cidade);
	aeroporto.setPais(pais);
	if (AeroportoDAL.getInstance().findOne(iata) == null) {
	    AeroportoDAL.getInstance().salvar(aeroporto);
	    return;
	}
	AeroportoDAL.getInstance().atualizar(aeroporto);
    }

    public void deletar(String iata) {
	AeroportoTO aeroporto = new AeroportoTO();
	aeroporto.setCodIATA(iata);
	AeroportoDAL.getInstance().deletar(aeroporto);
    }

    public ArrayList<AeroportoTO> find(String iata) {
	return AeroportoDAL.getInstance().findAll(iata);
    }
}
