/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Caio
 */
public class AssentoTO {

    private Character fila;
    private Integer numero;
    private AeronaveTO aeronave;
    private String classe;

    public AeronaveTO getAeronave() {
	return aeronave;
    }

    public void setAeronave(AeronaveTO aeronave) {
	this.aeronave = aeronave;
    }

    public String getClasse() {
	return classe;
    }

    public void setClasse(String classe) {
	this.classe = classe;
    }

    public Character getFila() {
	return fila;
    }

    public void setFila(Character fila) {
	this.fila = fila;
    }

    public Integer getNumero() {
	return numero;
    }

    public void setNumero(Integer numero) {
	this.numero = numero;
    }
}
