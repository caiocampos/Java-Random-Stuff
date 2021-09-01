/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package filtro;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.*;
import java.util.regex.Pattern;

/**
 *
 * @author a11011BSI208
 */
public final class ORIFunctions {

    private MapaTermo termos;

    public ORIFunctions() {
	termos = new MapaTermo();
	if (!(new File("dicionario\\vocabulario.dat")).exists()) {
	    filtrarMapaTermo();
	    salvarMapaTermo();
	} else {
	    carregarMapaTermo();
	}
    }

    public MapaTermo getTermos() {
	return termos;
    }

    public void setTermos(MapaTermo termos) {
	this.termos = termos;
    }

    public List encontrar(String termo) {
	int i;
	String[] palavras = getTermArray(termo);
	termo = "";
	for (i = 0; i < palavras.length; i++) {
	    termo = termo.concat(palavras[i]);
	}
	ListDocs ld = termos.get(termo);
	if (ld == null) {
	    return null;
	}
	return ld.list();
    }

    public List encontrarAND(String termo) {
	String[] palavras = getTermArray(termo);
	int i;
	ListDocs result = new ListDocs();
	ArrayList<ListDocs> termMaps = new ArrayList<ListDocs>();
	for (i = 0; i < palavras.length; i++) {
	    ListDocs map;
	    map = termos.get(palavras[i]);
	    if (map != null) {
		termMaps.add(map);
	    } else {
		return null;
	    }
	}
	if (termMaps.isEmpty()) {
	    return null;
	}
	List keys = termMaps.get(0).keys();
	for (i = 0; i < keys.size(); i++) {
	    Documento doc = new Documento(i + 1);
	    boolean add = true;
	    short val = 0;
	    Integer id = (Integer) keys.get(i);
	    for (ListDocs hm : termMaps) {
		if (hm.containsKey(id)) {
		    val += hm.get(id).getFrequencia();
		} else {
		    add = false;
		    break;
		}
	    }
	    doc.setFrequencia(val);
	    if (add) {
		result.add(doc);
	    }
	}
	if (result.size() == 0) {
	    return null;
	}
	return result.list();
    }

    public List encontrarOR(String termo) {
	String[] palavras = getTermArray(termo);
	int i;
	ListDocs result = new ListDocs();
	ArrayList<ListDocs> termMaps = new ArrayList<ListDocs>();
	for (i = 0; i < palavras.length; i++) {
	    ListDocs map;
	    map = termos.get(palavras[i]);
	    if (map != null) {
		termMaps.add(map);
	    }
	}
	if (termMaps.isEmpty()) {
	    return null;
	}
	for (ListDocs hm : termMaps) {
	    List keys = hm.keys();
	    for (i = 0; i < keys.size(); i++) {
		Integer id = (Integer) keys.get(i);
		Documento doc = new Documento(id);
		if (result.containsKey(id)) {
		    short val = result.get(id).getFrequencia();
		    val += hm.get(id).getFrequencia();
		    doc.setFrequencia(val);
		    result.add(doc);
		} else {
		    doc.setFrequencia(hm.get(id).getFrequencia());
		    result.add(doc);
		}
	    }
	}
	if (result.size() == 0) {
	    return null;
	}
	return result.list();
    }

    private void filtrarMapaTermo() {
	int i;
	String dir = "arquivos\\";
	File pasta = new File(dir);
	String[] arquivos = pasta.list();
	for (i = 0; i < arquivos.length; i++) {
	    if (arquivos[i].endsWith(".html") || arquivos[i].endsWith(".htm")) {
		String aux = null;
		try {
		    aux = this.ler(dir.concat(arquivos[i]));
		} catch (Exception e) {
		    System.out.println(e.getMessage());
		    System.out.println("Problema ao ler o arquivo: \"" + dir.concat(arquivos[i]) + "\"");
		}
		this.filtrar(aux, new Integer(i + 1));
	    }
	}
    }

    private void carregarMapaTermo() {
	try {
	    FileInputStream vocabulario = new FileInputStream(new File("dicionario\\vocabulario.dat"));
	    FileInputStream vocabularioOffset = new FileInputStream(new File("dicionario\\vocabularioOffset.dat"));
	    FileInputStream arquivoInv = new FileInputStream(new File("dicionario\\arquivoInv.dat"));
	    FileInputStream arquivoInvOffset = new FileInputStream(new File("dicionario\\arquivoInvOffset.dat"));
	    int i, aux;
	    DataInputStream dis;
	    List<Integer> vocPos = new ArrayList<Integer>();
	    List<Integer> aivPos = new ArrayList<Integer>();
	    dis = new DataInputStream(vocabularioOffset);
	    while(dis.available() >= 4) {
		aux = dis.readInt();
		vocPos.add(aux);
	    }
	    dis = new DataInputStream(arquivoInvOffset);
	    while(dis.available() >= 4) {
		aux = dis.readInt();
		aivPos.add(aux);
	    }
	} catch (Exception e) {
	    System.out.println("Um erro ocorreu ao salvar");
	    System.out.println("Descrição: " + e.getMessage());
	}
    }

    private void salvarMapaTermo() {
	try {
	    FileOutputStream vocabulario = new FileOutputStream(new File("dicionario\\vocabulario.dat"));
	    FileOutputStream vocabularioOffset = new FileOutputStream(new File("dicionario\\vocabularioOffset.dat"));
	    FileOutputStream arquivoInv = new FileOutputStream(new File("dicionario\\arquivoInv.dat"));
	    FileOutputStream arquivoInvOffset = new FileOutputStream(new File("dicionario\\arquivoInvOffset.dat"));
	    int vocabularioPos = 0;
	    int arquivoInvPos = 0;
	    List t = termos.keys();
	    byte[] b;
	    for (Object s : t) {
		b = ((String) s).getBytes();
		vocabulario.write(b);
		vocabulario.write('\0');
		vocabularioOffset.write(vocabularioPos);
		vocabularioPos += b.length;
		ListDocs listd = termos.get((String) s);
		ByteBuffer buffer = ByteBuffer.allocate(6 * listd.size());
		for (Object id : listd.keys()) {
		    Documento doc = listd.get((Integer) id);
		    buffer.put(doc.getId().byteValue());
		    buffer.put(doc.getFrequencia().byteValue());
		}
		b = buffer.array();
		arquivoInv.write(b);
		arquivoInvOffset.write(arquivoInvPos);
		arquivoInvPos += b.length;
	    }
	    vocabulario.close();
	    vocabularioOffset.close();
	    arquivoInv.close();
	    arquivoInvOffset.close();
	} catch (Exception e) {
	    System.out.println("Um erro ocorreu ao salvar");
	    System.out.println("Descrição: " + e.getMessage());
	}
    }

    private String[] getTermArray(String termo) {
	termo = limpar(termo);
	termo = termo.replaceAll(Pattern.quote("<"), "");
	termo = termo.replaceAll(Pattern.quote(">"), "");
	return termo.split("\\s+");
    }

    private String ler(String paginaHTML) throws Exception {
	File arquivo = new File(paginaHTML);
	if (!arquivo.exists()) {
	    return null;
	}
	BufferedReader buffer = new BufferedReader(new FileReader(arquivo));
	StringBuilder saida = new StringBuilder();
	String linha;
	while ((linha = buffer.readLine()) != null) {
	    saida.append(linha.concat("\n"));
	}
	buffer.close();
	return saida.toString();
    }

    private void filtrar(String pagina, Integer idArquivo) {
	pagina = this.limpar(pagina.toLowerCase());
	String[] palavras = pagina.split("\\s+");
	int i;
	boolean comecou = false;
	for (i = 0; i < palavras.length; i++) {
	    if (!comecou) {
		while (!(palavras[i].equals("body") || palavras[i].equals(">"))) {
		    i++;
		}
		comecou = true;
	    } else {
		if (!palavras[i].equals("<")) {
		    termos.increment(palavras[i], idArquivo);
		} else {
		    while (!palavras[i].equals(">")) {
			i++;
		    }
		}
	    }
	}
    }

    private String limpar(String texto) {
	//a
	texto = texto.replaceAll(Pattern.quote("&atilde;"), "a");
	texto = texto.replaceAll(Pattern.quote("&aacute;"), "a");
	texto = texto.replaceAll(Pattern.quote("&agrave;"), "a");
	texto = texto.replaceAll(Pattern.quote("&acirc;"), "a");
	texto = texto.replaceAll(Pattern.quote("&auml;"), "a");
	//e
	texto = texto.replaceAll(Pattern.quote("&eacute;"), "e");
	texto = texto.replaceAll(Pattern.quote("&egrave;"), "e");
	texto = texto.replaceAll(Pattern.quote("&ecirc;"), "e");
	texto = texto.replaceAll(Pattern.quote("&euml;"), "e");
	//i
	texto = texto.replaceAll(Pattern.quote("&iacute;"), "i");
	texto = texto.replaceAll(Pattern.quote("&igrave;"), "i");
	texto = texto.replaceAll(Pattern.quote("&icirc;"), "i");
	texto = texto.replaceAll(Pattern.quote("&iuml;"), "i");
	//o
	texto = texto.replaceAll(Pattern.quote("&otilde;"), "o");
	texto = texto.replaceAll(Pattern.quote("&oacute;"), "o");
	texto = texto.replaceAll(Pattern.quote("&ograve;"), "o");
	texto = texto.replaceAll(Pattern.quote("&ocirc;"), "o");
	texto = texto.replaceAll(Pattern.quote("&ouml;"), "o");
	//u
	texto = texto.replaceAll(Pattern.quote("&uacute;"), "u");
	texto = texto.replaceAll(Pattern.quote("&ugrave;"), "u");
	texto = texto.replaceAll(Pattern.quote("&ucirc;"), "u");
	texto = texto.replaceAll(Pattern.quote("&uuml;"), "u");
	//y
	texto = texto.replaceAll(Pattern.quote("&yacute;"), "y");
	texto = texto.replaceAll(Pattern.quote("&yuml;"), "y");
	//outros
	texto = texto.replaceAll(Pattern.quote("&ntilde;"), "n");
	texto = texto.replaceAll(Pattern.quote("&ccedil;"), "c");
	//a
	texto = texto.replaceAll(Pattern.quote("ã"), "a");
	texto = texto.replaceAll(Pattern.quote("á"), "a");
	texto = texto.replaceAll(Pattern.quote("à"), "a");
	texto = texto.replaceAll(Pattern.quote("â"), "a");
	texto = texto.replaceAll(Pattern.quote("ä"), "a");
	//e
	texto = texto.replaceAll(Pattern.quote("é"), "e");
	texto = texto.replaceAll(Pattern.quote("è"), "e");
	texto = texto.replaceAll(Pattern.quote("ê"), "e");
	texto = texto.replaceAll(Pattern.quote("ë"), "e");
	//i
	texto = texto.replaceAll(Pattern.quote("í"), "i");
	texto = texto.replaceAll(Pattern.quote("ì"), "i");
	texto = texto.replaceAll(Pattern.quote("î"), "i");
	texto = texto.replaceAll(Pattern.quote("ï"), "i");
	//o
	texto = texto.replaceAll(Pattern.quote("õ"), "o");
	texto = texto.replaceAll(Pattern.quote("ó"), "o");
	texto = texto.replaceAll(Pattern.quote("ò"), "o");
	texto = texto.replaceAll(Pattern.quote("ô"), "o");
	texto = texto.replaceAll(Pattern.quote("ö"), "o");
	//u
	texto = texto.replaceAll(Pattern.quote("ú"), "u");
	texto = texto.replaceAll(Pattern.quote("ù"), "u");
	texto = texto.replaceAll(Pattern.quote("û"), "u");
	texto = texto.replaceAll(Pattern.quote("ü"), "u");
	//y
	texto = texto.replaceAll(Pattern.quote("ý"), "y");
	texto = texto.replaceAll(Pattern.quote("ÿ"), "y");
	//outros
	texto = texto.replaceAll(Pattern.quote("ñ"), "n");
	texto = texto.replaceAll(Pattern.quote("ç"), "c");
	//tags
	texto = texto.replaceAll(Pattern.quote("<"), " < ");
	texto = texto.replaceAll(Pattern.quote(">"), " > ");
	//pontuação
	texto = texto.replaceAll(Pattern.quote("."), " ");
	texto = texto.replaceAll(Pattern.quote(","), " ");
	texto = texto.replaceAll(Pattern.quote(";"), " ");
	texto = texto.replaceAll(Pattern.quote(":"), " ");
	texto = texto.replaceAll(Pattern.quote("\\"), " ");
	texto = texto.replaceAll(Pattern.quote("/"), " ");
	texto = texto.replaceAll(Pattern.quote("|"), " ");
	texto = texto.replaceAll(Pattern.quote("{"), " ");
	texto = texto.replaceAll(Pattern.quote("}"), " ");
	texto = texto.replaceAll(Pattern.quote("["), " ");
	texto = texto.replaceAll(Pattern.quote("]"), " ");
	texto = texto.replaceAll(Pattern.quote("("), " ");
	texto = texto.replaceAll(Pattern.quote(")"), " ");
	texto = texto.replaceAll(Pattern.quote("#"), " ");
	texto = texto.replaceAll(Pattern.quote("$"), " ");
	texto = texto.replaceAll(Pattern.quote("%"), " ");
	texto = texto.replaceAll(Pattern.quote("*"), " ");
	texto = texto.replaceAll(Pattern.quote("&"), " ");
	texto = texto.replaceAll(Pattern.quote("-"), " ");
	texto = texto.replaceAll(Pattern.quote("_"), " ");
	texto = texto.replaceAll(Pattern.quote("+"), " ");
	texto = texto.replaceAll(Pattern.quote("="), " ");
	texto = texto.replaceAll(Pattern.quote("?"), " ");
	texto = texto.replaceAll(Pattern.quote("!"), " ");
	texto = texto.replaceAll(Pattern.quote("\'"), " ");
	texto = texto.replaceAll(Pattern.quote("\""), " ");
	texto = texto.replaceAll(Pattern.quote("£"), " ");
	texto = texto.replaceAll(Pattern.quote("¢"), " ");
	texto = texto.replaceAll(Pattern.quote("¬"), " ");
	texto = texto.replaceAll(Pattern.quote("°"), " ");
	texto = texto.replaceAll(Pattern.quote("ª"), " ");
	texto = texto.replaceAll(Pattern.quote("¨"), " ");
	texto = texto.replaceAll(Pattern.quote("´"), " ");
	texto = texto.replaceAll(Pattern.quote("`"), " ");
	texto = texto.replaceAll(Pattern.quote("~"), " ");
	texto = texto.replaceAll(Pattern.quote("^"), " ");
	texto = texto.replaceAll(Pattern.quote("¹"), " ");
	texto = texto.replaceAll(Pattern.quote("²"), " ");
	texto = texto.replaceAll(Pattern.quote("³"), " ");
	texto = texto.replaceAll(Pattern.quote("§"), " ");

	return texto;
    }
}
