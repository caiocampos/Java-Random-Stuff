/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;
import java.util.*;

/**
 *
 * @author Caio
 */
public class Encomenda extends Venda{

    private Calendar dataE;
    private boolean isPago;

    public void setPago(boolean v){
        isPago = v;
    }

    public boolean getPago(){
        return isPago;
    }

    public void setDataE(Calendar d){
        dataE = d;
    }

    public Calendar getDataE(){
        return dataE;
    }

    @Override
    public String toString(){
        String s = super.toString();

        s += dataE.toString();
        s += ((Boolean)isPago).toString();

        return s;
    }

    @Override
    public String geraNota() {
        String s = super.geraNota();
        Integer val = 0;
        s += "\n\n";
        s += "\nData de Entrega: ";
        val = dataE.get(GregorianCalendar.DAY_OF_MONTH);
        s += val.toString() + '/';
        val = dataE.get(GregorianCalendar.MONTH) + 1;
        s += val.toString() + '/';
        val = dataE.get(GregorianCalendar.YEAR);
        return s;
    }
}
