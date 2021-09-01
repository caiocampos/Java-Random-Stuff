/*
    Interface de acesso para a classe ProcessoServidor
 */
package controle;

import java.rmi.*;
import modelo.*;

public interface ProcessoServidor extends Remote{
    public boolean armazenar(Contato c) throws RemoteException;
    public boolean atualizar(Contato c) throws RemoteException;
    public boolean remover(Contato c)   throws RemoteException;
    public Contato recuperar(Contato c) throws RemoteException;
}
