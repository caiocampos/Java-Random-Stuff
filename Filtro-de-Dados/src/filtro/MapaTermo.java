/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package filtro;

import java.util.*;

/**
 *
 * @author Caio
 */
public class MapaTermo {
    private HashMap<String, ListDocs> termos;

    public MapaTermo() {
	this.termos = new HashMap<String, ListDocs>();
    }

    public ListDocs get(String termo) {
	return termos.get(termo);
    }

    public void set(String termo, ListDocs map) {
	termos.put(termo, map);
    }

    public void increment(String termo, Integer doc) {
	ListDocs map = this.get(termo);
	if (map == null) {
	    map = new ListDocs();
	}
	Documento aux = map.get(doc);
	if (aux == null) {
	    aux = new Documento(doc);
	}
	Short val = aux.getFrequencia();
	val++;
	aux.setFrequencia(val);
	map.add(aux);
	this.set(termo, map);
    }
    
    public List keys() {
	return Arrays.asList(termos.keySet().toArray());
    }

    public boolean containsKey(String t) {
	return termos.containsKey(t);
    }
}
