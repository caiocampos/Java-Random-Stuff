package Client;

import java.io.*;
import java.net.*;
import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.*;

import Protocol.ProtocolData;

public class Client {

    private final static String SERVERNAME = "localhost";
    private final static int SERVERPORT = 7000;

    public static void main(String[] args) throws IOException {
	boolean exit = false;
	BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
	System.out.println("Digite o nome de usuário:");
	String usuario = stdIn.readLine();
	Socket serverSocket = null;
	ObjectOutputStream out = null;
	ObjectInputStream in = null;
	ClientOut clOT = null;
	boolean iniciado = false;
	boolean conectando = false;
	Key secretKey = null;
	KeyPair kp = null;
	Cipher c = null;
	Signature s = null;
	String error = null;
	try {
	    serverSocket = new Socket(SERVERNAME, SERVERPORT);
	    out = new ObjectOutputStream(serverSocket.getOutputStream());
	    in = new ObjectInputStream(serverSocket.getInputStream());
	} catch (Exception e) {
	    error = e.getMessage();
	    System.out.println("Erro: " + error);
	    return;
	}
	ProtocolData dataToServer;
	ProtocolData dataFromServer;
	try {
	    while (!exit) {
		String sMessage = null;
		byte[] barray = null;
		byte[] ver = null;
		if (iniciado) {
		    if (conectando) {
			conectando = false;
			if (((ProtocolData) in.readObject()).toString().equals("ERROR")) {
			    System.out.println("Cliente Existente no Servidor");
			    return;
			}
			dataFromServer = (ProtocolData) in.readObject();
			try {
			    c = Cipher.getInstance("RSA");
			    c.init(Cipher.DECRYPT_MODE, kp.getPrivate(), c.getParameters());
			    byte[] ba = c.doFinal(dataFromServer.getBytes());
			    barray = new byte[16];
			    System.arraycopy(ba, 0, barray, 0, 16);
			    secretKey = new SecretKeySpec(barray, "AES");
			} catch (Exception e) {
			    error = e.getMessage();
			    System.out.println("Erro: " + error);
			    sMessage = "EXIT";
			    exit = true;
			}
			System.out.println("Connection with Server stabilished");
			clOT = new ClientOut(in, secretKey);
			clOT.start();
		    }
		    sMessage = stdIn.readLine();
		    barray = sMessage.getBytes();
		    if (sMessage.equalsIgnoreCase("@exit")) {
			sMessage = "EXIT";
			exit = true;
		    } else {
			sMessage = "DATA";
		    }
		} else {
		    conectando = true;
		    iniciado = true;
		    sMessage = "CONNECT";
		    try {
			kp = KeyPairGenerator.getInstance("RSA").genKeyPair();
			barray = kp.getPublic().getEncoded();
		    } catch (Exception e) {
			error = e.getMessage();
			System.out.println("Erro: " + error);
			sMessage = "EXIT";
			exit = true;
		    }
		}
		if (sMessage != null) {
		    if (!sMessage.equalsIgnoreCase("CONNECT")) {
			try {
			    s = Signature.getInstance("SHA1withRSA");
			    c = Cipher.getInstance("AES");
			    c.init(Cipher.ENCRYPT_MODE, secretKey);
			    s.initSign(kp.getPrivate());
			    s.update(barray);
			    ver = s.sign();
			    barray = c.doFinal(barray);
			} catch (Exception e) {
			    error = e.getMessage();
			}
			if (barray == null || error != null) {
			    System.out.println("Criptografia mal sucedida");
			    System.out.println("Erro: " + error);
			    sMessage = "EXIT";
			    exit = true;
			}
		    }
		    dataToServer = new ProtocolData(barray);
		    dataToServer.setMessage(sMessage);
		    dataToServer.setUser(usuario);
		    dataToServer.setVer(ver);
		    out.writeObject(dataToServer);
		}
	    }
	} catch (ClassNotFoundException e) {
	    error = e.getMessage();
	    System.out.println("Erro: " + error);
	} finally {
	    if (clOT != null) {
		try {
		    clOT.join();
		} catch (Exception e) {
		    System.out.println("Erro: " + e.getMessage());
		}
	    }
	    in.close();
	    out.close();
	    serverSocket.close();
	    System.out.println("Client: Sucessfull exit!");
	}
    }
}
