/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;
import java.io.Serializable;
import java.util.*;

/**
 *
 * @author Caio
 */
public class Fornecedor implements Serializable{

    private ArrayList<Compra> listaCompras = new ArrayList<Compra>();
    private String nome;
    private String cnpj;
    private String telefone;

    public void setNome(String s){
        nome = s;
    }

    public String getNome(){
        return nome;
    }

    public void setCNPJ(String c){
        cnpj = c;
    }

    public String getCNPJ(){
        return cnpj;
    }

     public void setTelefone(String t){
        telefone = t;
    }

    public String getTelefone(){
        return telefone;
    }

    public void addCompra(Compra c){
        c.setFN(this);
        listaCompras.add(c);
    }

    public void removeCompra(String s){
        Compra obj;
        for(int i = 0; i < listaCompras.size(); i++){
            obj = listaCompras.get(i);
            if(obj.getCode().equals(s)){
                obj.setFN("");
                listaCompras.remove(i);
            }
        }
    }

    public Compra findCompra(String s){
        for(Compra obj : listaCompras){
            if(obj.getCode().equals(s)) return obj;
        }
        return null;
    }

    public ArrayList<Compra> getLC(){
        return listaCompras;
    }

    @Override
    public String toString(){
        String s = new String();

        s += listaCompras.toString();
        s += nome;
        s += cnpj;
        s += telefone;

        return s;
    }
}
