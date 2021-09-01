/*

*/
package controle;

import java.math.BigDecimal;


public class Filtro {
    private static final int tamNome = 60;
    private static final int tamTelefone = 16;
    
    public static String filtroNome(String nome) {
        if(nome == null) {
            nome = new String();
        } else if(nome.length() > tamNome) {
            nome = nome.substring(0, tamNome);
        } 
        return nome;
    }
    
    public static BigDecimal filtroFone(String fone) {
        BigDecimal telefone;
        if(fone == null) {
            telefone = new BigDecimal(0);
        } else {
            fone = fone.substring(0, fone.length());
            fone = fone.replaceAll("\\D", "");
            if(fone.length() > tamTelefone) {
                fone = fone.substring(0, tamTelefone);
                telefone = new BigDecimal(fone);
            } else if(fone.length() < 1)
                telefone = new BigDecimal(0);
            else
                telefone = new BigDecimal(fone);
        }
        return telefone;
    }
}
