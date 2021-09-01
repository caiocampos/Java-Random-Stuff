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
public class Venda implements Serializable{

    private ArrayList<Produto> listaProdutos = new ArrayList<Produto>();
    private Calendar dataC = new GregorianCalendar();
    private String code = new String();
    private double preco;
    private String c;

    public void setCN(Cliente v){
        if(v == null) return;
        c = v.getNome();
    }

    public void setCN(String s){
        c = s;
    }

    public String getCN(){
        return c;
    }

    public void setData(Calendar d){
        dataC = d;
    }

    public Calendar getData(){
        return dataC;
    }
    
    public void calcPreco(){
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
                return;
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

        s += listaProdutos.toString();
        s += dataC.toString();
        s += code;
        s += ((Double)preco).toString();
        s += c;

        return s;
    }

    public String geraNota() {
        String s = new String();
        Integer val = 0;
        s += "\nCódigo: ";
        s += code;
        s += "\nData: ";
        val = dataC.get(GregorianCalendar.DAY_OF_MONTH);
        s += val.toString() + '/';
        val = dataC.get(GregorianCalendar.MONTH) + 1;
        s += val.toString() + '/';
        val = dataC.get(GregorianCalendar.YEAR);
        s += val.toString();
        s += "\n\n";
        for (Produto p : listaProdutos) {
            s += ((Integer)p.getQuantidade()).toString();
            s += "x";
            s += p.getNome();
            s += "\t";
            s += ((Double)p.getPreco()).toString();
            s += "\n";
        }
        s += "\nTotal: ";
        s += ((Double)preco).toString();
        return s;
    }
}
