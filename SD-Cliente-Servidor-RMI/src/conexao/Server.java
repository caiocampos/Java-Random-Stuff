/*
    Classe que ativa o RMI Registry e o configura para usar a classe
    ProcessoServidor.
*/
package conexao;

import controle.*;
import java.io.*;
import java.net.*;
import java.rmi.*;
import java.rmi.registry.*;
import java.util.logging.*;

public class Server {
    private final static String SERVERNAME = "localhost";
    private final static int SERVERPORT = 7000;

    public static void main(String[] args) throws IOException {
        try {
            Registry reg = LocateRegistry.createRegistry(SERVERPORT);
            ProcessoServidor ps = new ProcessoServidorImpl();
            Naming.rebind("rmi://" + SERVERNAME + ":" + SERVERPORT + "/Agenda", ps);
            System.out.println("Servidor ativo em: " + SERVERNAME + " Port: " + SERVERPORT);
        } catch(RemoteException | MalformedURLException e) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
