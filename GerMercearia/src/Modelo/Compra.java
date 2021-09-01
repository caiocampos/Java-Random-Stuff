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
public class Compra implements Serializable{

    private Calendar data;
    private String code;
    private double preco;
    private ArrayList<Produto> listaProdutos = new ArrayList<Produto>();
    private String f;

    public void setFN(Fornecedor v){
        if(v == null) return;
        f = v.getNome();
    }

    public void setFN(String s){
        f = s;
    }

    public String getFN(){
        return f;
    }

    public void setData(Calendar d){
        data = d;
    }

    public Calendar getData(){
        return data;
    }

    public void CalcPreco(){
        preco = 0;
        for(Produto p : listaProdutos){
            preco += p.getQuantidade() * p.getPreco();
        }
    }

    public double getPreco(){
        return preco;
    }

    public void addProduto(Produto p){
        listaProdutos.add(p);
    }

    public void removeProduto(String s){
        Produto obj;
        for(int i = 0; i < listaProdutos.size(); i++){
            obj = listaProdutos.get(i);
            if(obj.getNome().equals(s)){
                listaProdutos.remove(i);
            }
        }
    }

    public Produto findProduto(String s){
        for(Produto obj : listaProdutos){
            if(obj.getNome().equals(s)) return obj;
        }
        return null;
    }

    public void setLP(ArrayList<Produto> lp){
        listaProdutos = lp;
    }

    public ArrayList<Produto> getLP(){
        return listaProdutos;
    }

    public void setCode(String s){
        code = s;
    }

    public String getCode(){
        return code;
    }

    @Override
    public String toString(){
        String s = new String();

        s += data.toString();
        s += code;
        s += ((Double)preco).toString();
        s += listaProdutos.toString();
        s += f;

        return s;
    }
}
