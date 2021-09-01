/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author William
 */
public class DisponibilizaTO {
    private AssentoTO assento;
    private InstanciaTO instancia;
    private int codReserva;

    public AssentoTO getAssento() {
        return assento;
    }

    public void setAssento(AssentoTO assento) {
        this.assento = assento;
    }

    public InstanciaTO getInstancia() {
        return instancia;
    }

    public void setInstancia(InstanciaTO instancia) {
        this.instancia = instancia;
    }

    public int getCodReserva() {
        return codReserva;
    }

    public void setCodReserva(int codReserva) {
        this.codReserva = codReserva;
    }
    
}
