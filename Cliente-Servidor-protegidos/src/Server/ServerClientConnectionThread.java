package Server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import Protocol.ProtocolData;
import Protocol.ServiceProtocol;

public class ServerClientConnectionThread extends Thread {

    private Socket socket = null;
    private ObjectOutputStream out = null;
    private ObjectInputStream in = null;

    public ServerClientConnectionThread(Socket socket) {
	super("ServerClientConnectionThread");
	this.socket = socket;
	try {
	    out = new ObjectOutputStream(socket.getOutputStream());
	    in = new ObjectInputStream(socket.getInputStream());
	} catch (Exception e) {
	    System.out.println("Erro: " + e.getMessage());
	}
    }

    @Override
    public void run() {
	try {
	    boolean b = true;
	    ProtocolData input, output;
	    ServiceProtocol service = new ServiceProtocol();
	    while (b) {
		input = (ProtocolData) in.readObject();
		output = service.processInput(input);
		if (output.isFinished()) {
		    b = false;
		    out.writeObject(output);
		    output.setFinished(false);
		    ServerMultiThreaded.remThread(this);
		}
		if (output.getStatus() == 0) {
		    out.writeObject(output);
		} else {
		    ServerMultiThreaded.writeOut(output);
		}
	    }
	    out.close();
	    in.close();
	    socket.close();
	    System.out.println("Finish connection with this client. Server is still running!");
	} catch (Exception e) {
	    System.out.println("Erro: " + e.getMessage());
	    ServerMultiThreaded.remThread(this);
	}
    }

    public ObjectInputStream getIn() {
	return in;
    }

    public ObjectOutputStream getOut() {
	return out;
    }
}
