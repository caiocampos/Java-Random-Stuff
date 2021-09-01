/*
    Classe que fornece toda a lógica para a utilização dos menus.
*/
package visao;

import modelo.ProtocolData;
import conexao.*;
import java.util.Scanner;
import modelo.Contato;

public class Cliente {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String server;
        int port;
        Contato cont;
        ConexaoClient c;
        ProtocolData pd;
        String nome, aux, tel;
        int opt = 0;
        boolean espera;
        if(args.length == 0) {
            System.out.println("Sem argumentos");
            return;
        }
        server = args[0];
        port = new Integer(args[1]).intValue();
        c = new ConexaoClient(server, port);
        while(opt != 4) {
            System.out.println("1 - Armazena/Atualiza um Registro;");
            System.out.println("2 - Remove um Registro;");
            System.out.println("3 - Recupera um Registro;");
            System.out.println("4 - Finaliza a Aplicação");
            opt = s.nextInt();
            s.nextLine();
            espera = true;
            switch(opt) {
                case 1:
                    System.out.println("Digite o nome do Contato:");
                    nome = s.nextLine();
                    System.out.println("Digite o telefone do Contato:");
                    tel = s.nextLine();
                    cont = new Contato(nome, tel);
                    pd = new ProtocolData("rec", cont);
                    c.enviar(pd);
                    pd = c.receber();
                    if(pd.getCodigo().equals("suc")) {
                        System.out.println("Contado já é conhecido e possui o número:");
                        System.out.println(cont.getTelefone());
                        System.out.println("Deseja atualizar o contato? "
                                + "(escreva Sim para atualizar)");
                        aux = s.nextLine();
                        if(aux.equalsIgnoreCase("sim")) {
                            System.out.println("Digite o telefone do Contato:");
                            tel = s.nextLine();
                            cont.setTelefone(tel);
                            pd = new ProtocolData("sub", cont);
                            c.enviar(pd);
                        } else {
                            espera = false;
                        }
                    } else {
                        pd = new ProtocolData("arm", cont);
                        c.enviar(pd);
                    }
                    break;
                case 2:
                    System.out.println("Digite o nome do Contato:");
                    nome = s.nextLine();
                    cont = new Contato(nome, null);
                    pd = new ProtocolData("rem", cont);
                    c.enviar(pd);
                    break;
                case 3:
                    System.out.println("Digite o nome do Contato:");
                    nome = s.nextLine();
                    cont = new Contato(nome, null);
                    pd = new ProtocolData("rec", cont);
                    c.enviar(pd);
                    break;
                case 4:
                    cont = new Contato();
                    pd = new ProtocolData("fin", cont);
                    c.enviar(pd);
                    break;
                default:
                    System.out.println("Opção não reconhecida");
                    espera = false;
            }
            if(espera && opt != 4) {
                pd = c.receber();
                switch (pd.getCodigo()) {
                    case "suc":
                        System.out.println("A operação foi efetuada com sucesso");
                        if(opt == 3) {
                            System.out.println("O nome do Contato é:     " + pd.getContato().getNome());
                            System.out.println("O telefone do Contato é: " + pd.getContato().getTelefone());
                        }
                        break;
                    case "fal":
                        System.out.println("A operação falhou");
                        break;
                    default:
                        System.out.println("Retorno não reconhecido");
                        break;
                }
            }
        }
    }
}
