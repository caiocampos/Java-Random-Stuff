/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author Caio
 */
public class Arq {
    public static <T> boolean gravarArray(ArrayList<T> os,String arq){
        try
        {
            FileOutputStream arquivoGrav = new FileOutputStream(arq);
            ObjectOutputStream objGravar = new ObjectOutputStream(arquivoGrav);
            objGravar.writeObject(os);
            objGravar.flush();
            objGravar.close();
            arquivoGrav.flush();
            arquivoGrav.close();
            return true;
        }
        catch( Exception e ){
                return false;
        }

    }

    public static <T> ArrayList<T> lerArray(String arq){
        ArrayList<T> obj = null;
        FileInputStream arquivoLeitura = null;
        ObjectInputStream objLeitura = null;
        try
        {
            arquivoLeitura = new FileInputStream(arq);
            objLeitura = new ObjectInputStream(arquivoLeitura);
            obj = (ArrayList<T>)objLeitura.readObject();
            objLeitura.close();
            arquivoLeitura.close();
        }
        catch( Exception e ){
            return null;
        }
        return obj;
    }

    public static boolean gravarVal(String os,String arq){
        try
        {
            FileWriter arquivoGrav = new FileWriter(arq);
            BufferedWriter objGravar = new BufferedWriter(arquivoGrav);
            objGravar.write(os);
            objGravar.flush();
            objGravar.close();
            arquivoGrav.close();
            return true;
        }
        catch( Exception e ){
                return false;
        }
    }

    public static String lerVal(String arq){
        String obj = null;
        FileReader arquivoLeitura = null;
        BufferedReader objLeitura = null;
        try
        {
            arquivoLeitura = new FileReader(arq);
            objLeitura = new BufferedReader(arquivoLeitura);
            obj = objLeitura.readLine();
            objLeitura.close();
            arquivoLeitura.close();
        }
        catch( Exception e ){
            return null;
        }
        return obj;
    }
}
