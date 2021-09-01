/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package filtro;

/**
 *
 * @author Caio
 */
public class TermoComparable implements Comparable<TermoComparable> {

    private String termo;
    private byte[] docs;
    
    public byte[] getDocs() {
	return docs;
    }

    public void setDocs(byte[] docs) {
	this.docs = docs;
    }

    public String getTermo() {
	return termo;
    }

    public void setTermo(String termo) {
	this.termo = termo;
    }

    @Override
    public int compareTo(TermoComparable t) {
	return termo.compareTo(t.getTermo());
    }
    
}
