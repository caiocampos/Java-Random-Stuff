/*
    Classe que fornece toda a lógica para a utilização dos menus e pede
    serviços ao servidor.
*/
package controle;

import java.net.*;
import java.rmi.*;
import java.util.*;
import java.util.logging.*;
import modelo.*;

public class Cliente {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String server;
        int port;
        Contato cont = null;
        String nome, aux, tel;
        int opt = 0;
        boolean espera, estado = true;
        if(args.length == 0) {
            System.out.println("Sem argumentos");
            return;
        }
        server = args[0];
        port = new Integer(args[1]).intValue();
        ProcessoServidor ps;
        try {
            ps = (ProcessoServidor) Naming.lookup("rmi://" + server + ":" +
                    port + "/Agenda");
        } catch (NotBoundException | MalformedURLException | RemoteException e) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, e);
            return;
        }
        while(opt != 4) {
            System.out.println("1 - Armazena/Atualiza um Registro;");
            System.out.println("2 - Remove um Registro;");
            System.out.println("3 - Recupera um Registro;");
            System.out.println("4 - Finaliza a Aplicação");
            opt = s.nextInt();
            s.nextLine();
            espera = true;
            try {
                switch(opt) {
                    case 1:
                        System.out.println("Digite o nome do Contato:");
                        nome = s.nextLine();
                        System.out.println("Digite o telefone do Contato:");
                        tel = s.nextLine();
                        cont = new Contato(nome, tel);
                        if(ps.recuperar(cont) != null) {
                            System.out.println("Contado já é conhecido e possui o número:");
                            System.out.println(cont.getTelefone());
                            System.out.println("Deseja atualizar o contato? "
                                    + "(escreva Sim para atualizar)");
                            aux = s.nextLine();
                            if(aux.equalsIgnoreCase("sim")) {
                                System.out.println("Digite o telefone do Contato:");
                                tel = s.nextLine();
                                cont.setTelefone(tel);
                                estado = ps.atualizar(cont);
                            } else {
                                espera = false;
                            }
                        } else {
                            estado = ps.armazenar(cont);
                        }
                        break;
                    case 2:
                        System.out.println("Digite o nome do Contato:");
                        nome = s.nextLine();
                        cont = new Contato(nome, null);
                        estado = ps.remover(cont);
                        break;
                    case 3:
                        System.out.println("Digite o nome do Contato:");
                        nome = s.nextLine();
                        cont = ps.recuperar(new Contato(nome, null));
                        estado = cont != null;
                        break;
                    case 4:
                        System.out.println("Saindo...");
                        break;
                    default:
                        System.out.println("Opção não reconhecida");
                        espera = false;
                }
                if(espera && opt != 4) {
                    if(estado) {
                        System.out.println("A operação foi efetuada com sucesso");
                        if(opt == 3 && cont != null) {
                            System.out.println("O nome do Contato é:     " + cont.getNome());
                            System.out.println("O telefone do Contato é: " + cont.getTelefone());
                        }
                    } else {
                        System.out.println("A operação falhou");
                    }
                }
            } catch(RemoteException e) {
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, e);
                System.out.println("Problema ao efetuar a operação");
            }
        }
    }
}
