/*
    Classe que encaminha o serviço requisitado pelo cliente para o DAO
    processar.
*/
package controle;

import dao.*;
import java.rmi.*;
import java.rmi.server.*;
import modelo.*;

public class ProcessoServidorImpl extends UnicastRemoteObject implements ProcessoServidor{
    
    public ProcessoServidorImpl() throws RemoteException {
        super();
    }
    
    @Override
    public boolean armazenar(Contato c) throws RemoteException {
        return ContatoDAO.getInstance().salvar(c);
    }

    @Override
    public boolean atualizar(Contato c) throws RemoteException {
        return ContatoDAO.getInstance().atualizar(c);
    }

    @Override
    public boolean remover(Contato c) throws RemoteException {
        return ContatoDAO.getInstance().deletar(c.getNome());
    }

    @Override
    public Contato recuperar(Contato c) throws RemoteException {
        return ContatoDAO.getInstance().buscar(c.getNome());
    }
}
