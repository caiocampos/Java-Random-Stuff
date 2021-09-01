import java.net.*;
import java.rmi.*;
import java.rmi.registry.*;

public class MensageiroServer {

    public MensageiroServer() {
        try {
            Registry reg = LocateRegistry.createRegistry(1099);
            Mensageiro m = new MensageiroImpl();
            Naming.rebind("rmi://localhost:1099/MensageiroService", m);
        } catch(RemoteException | MalformedURLException e) {
            System.out.println("Trouble: " + e);
        }
    }

    public static void main(String[] args) {
        MensageiroServer mensageiroServer = new MensageiroServer();
    }
}