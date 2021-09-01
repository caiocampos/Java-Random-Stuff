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
public class ListDocs {

    private HashMap<Integer, Documento> docs;

    public ListDocs() {
	docs = new HashMap<Integer, Documento>();
    }

    public void add(Documento doc) {
	docs.put(doc.getId(), doc);
    }

    public Documento get(Integer doc) {
	return docs.get(doc);
    }

    public int size() {
	return docs.size();
    }

    public List list() {
	List<Documento> al = new ArrayList<Documento>();
	Object[] docv = docs.values().toArray();
	for (int i = 0; i < docv.length; i++) {
	    if (docv[i] != null) {
		al.add((Documento) docv[i]);
	    }
	}
	Collections.sort(al);
	return al;
    }

    public List keys() {
	return Arrays.asList(docs.keySet().toArray());
    }

    public boolean containsKey(Integer id) {
	return docs.containsKey(id);
    }
}
