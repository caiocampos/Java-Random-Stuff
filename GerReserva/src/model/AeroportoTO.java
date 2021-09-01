/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Caio
 */
public class AeroportoTO {

    private String codIATA;
    private String codICAO;
    private String nome;
    private String cidade;
    private String pais;

    public String getCidade() {
	return cidade;
    }

    public void setCidade(String cidade) {
	this.cidade = cidade;
    }

    public String getCodIATA() {
	return codIATA;
    }

    public void setCodIATA(String codIATA) {
	this.codIATA = codIATA;
    }

    public String getCodICAO() {
	return codICAO;
    }

    public void setCodICAO(String codICAO) {
	this.codICAO = codICAO;
    }

    public String getNome() {
	return nome;
    }

    public void setNome(String nome) {
	this.nome = nome;
    }

    public String getPais() {
	return pais;
    }

    public void setPais(String pais) {
	this.pais = pais;
    }
    
    @Override
    public String toString(){
        return String.format("%s - %s", this.nome, this.cidade);
    }
    
}
