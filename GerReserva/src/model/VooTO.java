/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Caio
 */
public class VooTO {

    private CompanhiaTO companhia;
    private String numVoo;

    public CompanhiaTO getCompanhia() {
        return companhia;
    }

    public void setCompanhia(CompanhiaTO companhia) {
        this.companhia = companhia;
    }

    public String getNumVoo() {
        return numVoo;
    }

    public void setNumVoo(String numVoo) {
        this.numVoo = numVoo;
    }

    @Override
    public String toString() {
        return this.numVoo + " " + this.companhia.getNome();
    }
}
