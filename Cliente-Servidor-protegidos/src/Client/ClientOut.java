package Client;

import Protocol.ProtocolData;
import java.io.*;
import java.security.*;
import javax.crypto.*;

public class ClientOut extends Thread {

    private ObjectInputStream in;
    private Key k;

    public ClientOut(ObjectInputStream clin, Key key) {
	in = clin;
	k = key;
    }

    @Override
    public void run() {
	String data = "";
	ProtocolData dataFromServer;
	byte[] barray;
	System.out.println("Entre com uma mensagem: [@exit para sair.]");
	while (true) {
	    try {
		dataFromServer = (ProtocolData) in.readObject();
		if (dataFromServer.isFinished()) {
		    break;
		}
		if (dataFromServer.isEncripted()) {
		    Cipher c = Cipher.getInstance("AES");
		    c.init(Cipher.DECRYPT_MODE, k);
		    barray = c.doFinal(dataFromServer.getBytes());
		} else {
		    barray = dataFromServer.getBytes();
		}
		data += "\n" + new String(barray);
		System.out.println(data);
		if (dataFromServer.isFinished()) {
		    break;
		}
		System.out.println("Entre com uma mensagem: [@exit para sair.]");
	    } catch (Exception e) {
		System.out.println("Erro: " + e.getMessage());
		break;
	    }
	}
    }
}
