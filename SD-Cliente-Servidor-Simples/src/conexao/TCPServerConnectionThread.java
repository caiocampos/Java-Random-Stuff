/*
    Thread responsável por receber e desempacotar as requisições de serviços
    TCP e os enviar para que controle possa processar. Por fim esta classe
    envia a resposta processada para o cliente.
*/
package conexao;

import modelo.ProtocolData;
import controle.ProcessoServidor;
import java.io.*;
import java.net.Socket;
import java.util.logging.*;

public class TCPServerConnectionThread extends Thread {

    private Socket socket = null;

    public TCPServerConnectionThread(Socket socket) {
	super("TCPServerConnectionThread");
	this.socket = socket;
    }

    @Override
    public void run() {
	ObjectInputStream in;
        try(ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream())) {
            in = new ObjectInputStream(socket.getInputStream());
            ProtocolData input, output;
            out.writeObject(new ProtocolData());
            while(true) {
                input = (ProtocolData)in.readObject();
                output = ProcessoServidor.run(input);
                out.writeObject(output);
                if(input.getCodigo().equals("fin")) {
                    break;
                }
            }
	    in.close();
            out.close();
	    socket.close();
	} catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(TCPServerConnectionThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
