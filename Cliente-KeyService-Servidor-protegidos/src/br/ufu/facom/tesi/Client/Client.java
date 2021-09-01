package br.ufu.facom.tesi.Client;

import br.ufu.facom.tesi.Protocol.ProtocolData;

import java.io.*;
import java.net.*;
import java.security.*;
import javax.crypto.*;

public class Client {

    private final static String SERVERNAME = "localhost";
    private final static int COMSERVERPORT = 7000;
    private final static int KEYSERVERPORT = 7001;

    public static void main(String[] args) throws IOException {
	String usuario = null;
	BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
	System.out.println("Digite o nome de usuário:");
	usuario = stdIn.readLine();
	Socket keyServerSocket = null;
	Socket comServerSocket = null;
	ObjectOutputStream keyOut = null;
	ObjectOutputStream comOut = null;
	ObjectInputStream comIn = null;
	KeyPair kp = null;
	Cipher c = null;
	Signature s = null;
	String error = null;
	try {
	    keyServerSocket = new Socket(SERVERNAME, KEYSERVERPORT);
	    keyOut = new ObjectOutputStream(keyServerSocket.getOutputStream());
	    comServerSocket = new Socket(SERVERNAME, COMSERVERPORT);
	    comOut = new ObjectOutputStream(comServerSocket.getOutputStream());
	    comIn = new ObjectInputStream(keyServerSocket.getInputStream());
	} catch (UnknownHostException e) {
	    System.err.println(SERVERNAME + " : Unkown Host");
	    System.exit(1);
	} catch (IOException e) {
	    System.err.println(SERVERNAME + " : I/O Error");
	    System.exit(1);
	}
	ProtocolData dataToServer;
	ProtocolData dataFromServer;
	try {
	    s = Signature.getInstance("SHA1withRSA");
	    c = Cipher.getInstance("RSA");
	    KeyStore ks = KeyStore.getInstance("JCEKS");
	    File keyStoreFile = new File(usuario + ".keystore");
	    char[] keyStorePassword = "userstdpw".toCharArray();
	    if (!keyStoreFile.exists()) {
		kp = KeyPairGenerator.getInstance("RSA").generateKeyPair();
		ks.load(null, null);
		ks.setKeyEntry("Public", kp.getPublic(), keyStorePassword, null);
		ks.setKeyEntry("Private", ((Key)kp.getPrivate()), keyStorePassword, null);
		FileOutputStream fos = new FileOutputStream(keyStoreFile);
		ks.store(fos, keyStorePassword);
		fos.close();
		dataToServer = new ProtocolData(usuario, kp.getPublic().getEncoded());
		dataToServer.setMessage("SETKEY");
		keyOut.writeObject(dataToServer);
	    } else {
		FileInputStream fis = new FileInputStream(keyStoreFile);
		ks.load(fis, null);
		PublicKey puk = (PublicKey) ks.getKey("Public", keyStorePassword);
		PrivateKey prk = (PrivateKey) ks.getKey("Private", keyStorePassword);
		kp = new KeyPair(puk, prk);
		fis.close();
	    }
	} catch (Exception e) {
	    error = e.getMessage();
	    System.out.println("Erro: " + error);
	    return;
	}
	try {
	    while (true) {
		String sMessage = null;
		byte[] barray = null;
		byte[] ver = null;
		dataFromServer = (ProtocolData) comIn.readObject();
		System.out.println("Server Data: " + dataFromServer.toString());
		if (dataFromServer.isFinished()) {
		    break;
		}
		System.out.println("Enter Message: ");
		sMessage = stdIn.readLine();
		System.out.println("Enter Data: ");
		barray = stdIn.readLine().getBytes();
		if (sMessage != null) {
		    try {
			c.init(Cipher.ENCRYPT_MODE, kp.getPrivate());
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
			return;
		    }
		}
		dataToServer = new ProtocolData(usuario, barray);
		dataToServer.setMessage(sMessage);
		dataToServer.setVer(ver);
		comOut.writeObject(dataToServer);
	    }
	} catch (ClassNotFoundException e) {
	    error = e.getMessage();
	    System.out.println("Erro: " + error);
	} finally {
	    keyOut.close();
	    comOut.close();
	    comIn.close();
	    keyServerSocket.close();
	    comServerSocket.close();
	    System.out.println("Client: Sucessfull exit!");
	}
    }
}
