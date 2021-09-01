/*
    Classe responsável por modelar os dados de comunicação. Nela está
    encapsulada todos dados necessários para que o cliente e o servidor
    possam estabelecer a comunicação lógica através de um protocolo.
*/
package modelo;

import modelo.Contato;
import java.io.*;

public class ProtocolData implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private Contato contato;
    private String codigo;
    
//****************************************//
//************ Códigos usados ************//
//** arm(azena),  rem(ove), rec(upera), **//
//** fin(aliza),  fal(ha),  suc(esso),  **//
//** sub(stituir)                       **//
//****************************************//
    
    public ProtocolData() {
        codigo = new String();
        contato = new Contato();
    }
     
    public ProtocolData(String cod, Contato cont) {
        codigo = cod;
        contato = cont;
    }
    
    public ProtocolData(String s) {
        int ini, fin;
        String aux;
        Contato c = new Contato();
        try {
            ini = 0;
            fin = 3;
            codigo = s.substring(ini, fin);
            ini = fin + 2;
            fin = s.indexOf(">_<");
            aux = s.substring(ini, fin);
            c.setNome(aux);
            ini = fin + 3;
            fin = s.lastIndexOf('>');
            aux = s.substring(ini, fin);
            c.setTelefone(aux);
            contato = c;
        } catch(Exception e) {
            contato = new Contato();
        }
    }

    @Override
    public String toString() {
	return getCodigo() + "_<" + getContato().getNome() + ">_<"
                      + getContato().getTelefone() + ">";
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }
}
