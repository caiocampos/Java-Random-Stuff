/*
    Classe que modela o Contato.
*/
package modelo;

import controle.Filtro;
import java.io.Serializable;
import java.math.BigDecimal;

public class Contato implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private String nome;
    private BigDecimal telefone;
    
    public Contato() {
        this.nome     = new String();
        this.telefone = new BigDecimal(0);
    }

    public Contato(String nome, String telefone) {
        this.nome     = Filtro.filtroNome(nome);
        this.telefone = Filtro.filtroFone(telefone);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = Filtro.filtroNome(nome);
    }

    public BigDecimal getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = Filtro.filtroFone(telefone);
    }
}
