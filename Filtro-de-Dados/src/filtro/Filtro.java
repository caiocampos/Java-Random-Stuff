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
public class Filtro {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
	Scanner s = new Scanner(System.in);
	ORIFunctions func = new ORIFunctions();
	int i;
	String term, opt;
	ArrayList<Documento> termList = null;
	while (true) {
	    System.out.println("\nEscolha a operação:");
	    System.out.println("Sair (escreva \"sair\"):");
	    System.out.println("Busca Conjuntiva (escreva \"e\"):");
	    System.out.println("Busca Dijuntiva (escreva \"ou\"):");
	    System.out.println("Busca de um Termo (escreva outra coisa):");
	    opt = s.nextLine();
	    if (opt.equalsIgnoreCase("sair")) {
		return;
	    }
	    System.out.println("\nEscreva os termos:");
	    term = s.nextLine();
	    if (opt.equalsIgnoreCase("ou")) {
		termList = (ArrayList<Documento>) func.encontrarOR(term);
	    } else if (opt.equalsIgnoreCase("e")) {
		termList = (ArrayList<Documento>) func.encontrarAND(term);
	    } else {
		termList = (ArrayList<Documento>) func.encontrar(term);
	    }
	    if (termList != null) {
		for (Documento d : termList) {
		    System.out.println(d);
		}
	    } else {
		System.out.println("\nOs termos: \"" + term + "\" não foram encontrados");
	    }
	}
    }
}
