package br.ufu.facom.tesi.Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import br.ufu.facom.tesi.Protocol.ProtocolData;
import br.ufu.facom.tesi.Protocol.KeyServiceProtocol;

public class KeyServerConnectionThread extends Thread {

    private Socket socket = null;

    public KeyServerConnectionThread(Socket socket) {
	super("KeyServerConnectionThread");
	this.socket = socket;
    }

    @Override
    public void run() {
	try {
	    ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
	    ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
	    ProtocolData input, output;
	    KeyServiceProtocol service = new KeyServiceProtocol();
	    while (true) {
		input = (ProtocolData) in.readObject();
		output = service.processInput(input);
		out.writeObject(output);
		if (output.isFinished()) {
		    break;
		}
	    }
	    out.close();
	    in.close();
	    socket.close();
	} catch (IOException e) {
	    System.out.println(e.getMessage());
	} catch (ClassNotFoundException e) {
	    System.out.println(e.getMessage());
	}
    }
}
