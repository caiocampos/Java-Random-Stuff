/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

import java.io.Serializable;

/**
 *
 * @author Caio
 */
public class Produto implements Serializable{

    private String nome;
    private double preco;
    private int quantidade = Integer.MAX_VALUE;
    private boolean tipo;

    public void setNome(String n){
        nome = n;
    }

    public String getNome(){
        return nome;
    }

    public void setPreco(Double p){
        preco = p;
    }

    public double getPreco(){
        return preco;
    }

    public void setQuantidade(int q){
        if(tipo) return;
        quantidade = q;
    }

    public int getQuantidade(){
        return quantidade;
    }

    public void setTipo(boolean t){
        tipo = t;
    }

    public boolean getTipo(){
        return tipo;
    }

    public void addProduto(int val){
        if(tipo) return;
        quantidade += val;
    }

    public void removeProduto(int val){
        if(tipo) return;
        quantidade -= val;
    }

    @Override
    public String toString(){
        String s = new String();

        s += nome;
        s += ((Double)preco).toString();
        s += ((Integer)quantidade).toString();
        s += ((Boolean)tipo).toString();

        return s;
    }
}
