/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;
import java.io.Serializable;
import java.util.*;

/**
 *
 * @author Reuel
 */
public class Cliente implements Serializable{
    private ArrayList<Venda> vs = new ArrayList<Venda>();
    private String nome;
    private String cpf;
    private String rg;
    private String telefone;
    private String endereco;

    public void setNome(String s){
        nome = s;
    }

    public String getNome(){
        return nome;
    }

    public void setCPF(String c){
        cpf = c;
    }

    public String getCPF(){
        return cpf;
    }

    public void setRG(String s){
        rg = s;
    }

    public String getRG(){
        return rg;
    }

     public void setTelefone(String t){
        telefone = t;
    }

    public String getTelefone(){
        return telefone;
    }

     public void setEndereco(String e){
        endereco = e;
    }

    public String getEndereco(){
        return endereco;
    }

    public void addVenda(Venda v){
        v.setCN(this);
        vs.add(v);
    }

    public void removeVenda(String s){
        Venda obj;
        for(int i = 0; i < vs.size(); i++){
            obj = vs.get(i);
            if(obj.getCode().equals(s)){
                obj.setCN("");
                vs.remove(i);
                return;
            }
        }
    }

    public Venda findVenda(String s){
        for(Venda obj : vs){
            if(obj.getCode().equals(s)) return obj;
        }
        return null;
    }

    public void setLV(ArrayList<Venda> v){
        vs = v;
    }

    public ArrayList<Venda> getLV(){
        return vs;
    }

    @Override
    public String toString(){
        String s = new String();

        s += vs.toString();
        s += nome;
        s += cpf;
        s += rg;
        s += telefone;
        s += endereco;

        return s;
    }
}


