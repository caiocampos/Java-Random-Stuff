/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package filtro;

/**
 *
 * @author Caio
 */
public class Documento implements Comparable<Documento> {

    private Integer id;
    private Short frequencia;

    public Documento(Integer id, Short frequencia) {
	this.id = id;
	this.frequencia = frequencia;
    }
    
    public Documento(Integer id) {
	this.id = id;
	this.frequencia = 0;
    }

    public Short getFrequencia() {
	return frequencia;
    }

    public void setFrequencia(Short frequencia) {
	this.frequencia = frequencia;
    }

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    @Override
    public int compareTo(Documento t) {
	return -frequencia.compareTo(t.getFrequencia());
    }

    @Override
    public String toString() {
	return id.toString() + " " + frequencia.toString();
    }
}
