import java.rmi.*;
import java.rmi.server.*;

public class MensageiroImpl extends UnicastRemoteObject implements Mensageiro {

    public MensageiroImpl() throws RemoteException {
        super();
    }

    @Override
    public void enviarMensagem(String msg) throws RemoteException {
        System.out.println(msg);
    }
    
    @Override
    public String lerMensagem() throws RemoteException {
        return "This is not a Hello World! message";
    }
}