import java.rmi.*;
import java.net.*;

public class MensageiroClient {
    
    public static void main(String args[]) {
        try {
            Mensageiro m = (Mensageiro) Naming.lookup("rmi://localhost/MensageiroService");
            System.out.println(m.lerMensagem());
            m.enviarMensagem("Hello World!");
        } catch(MalformedURLException | RemoteException | NotBoundException e) {
            System.out.println(e.toString());
        }
    }
}