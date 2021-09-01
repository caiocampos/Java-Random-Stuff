package br.ufu.facom.tesi.Protocol;

import java.io.*;
import java.net.*;
import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.*;

public class CommerceServiceProtocol {

    private PublicKey secretKey = null;
    private Cipher c = null;
    private Signature s = null;

    public ProtocolData processInput(ProtocolData theInput) {
	ProtocolData theOutput = null;
	String sMessage = theInput.getMessage();
	if (sMessage.equalsIgnoreCase("DATA")) {
	    if (secretKey == null) {
		try {
		    receivesKey(theInput.getNome());
		} catch (Exception e) {
		    System.out.println("Erro: " + e.getMessage());
		    return new ProtocolData("ERROR".getBytes());
		}
	    }
	    try {
		byte[] barray = theInput.getBytes();
		s = Signature.getInstance("SHA1withRSA");
		c = Cipher.getInstance("RSA");
		c.init(Cipher.DECRYPT_MODE, secretKey);
		barray = c.doFinal(barray);
		s.initVerify(secretKey);
		s.update(barray);
		if (s.verify(theInput.getVer())) {
		    throw new Exception();
		}
	    } catch (Exception e) {
		System.out.println("Erro: " + e.getMessage());
		return new ProtocolData("ERROR".getBytes());
	    }
	    theOutput = new ProtocolData("Request has been accepted");
	} else if (sMessage.equalsIgnoreCase("EXIT")) {
	    theOutput = new ProtocolData("BYE BYE");
	    theOutput.setFinished(true);
	} else {
	    return new ProtocolData("Wrong message to the server".getBytes());
	}
	return theOutput;
    }

    public void receivesKey(String alias) throws Exception {
	Socket keyServerSocket = new Socket("localhost", 7001);
	ObjectInputStream keyIn = new ObjectInputStream(keyServerSocket.getInputStream());
	ObjectOutputStream keyOut = new ObjectOutputStream(keyServerSocket.getOutputStream());
	ProtocolData dataToServer = new ProtocolData(alias);
	dataToServer.setMessage("GETKEY");
	keyOut.writeObject(dataToServer);
	ProtocolData dataFromServer = (ProtocolData) keyIn.readObject();
	byte[] barray = dataFromServer.getBytes();
	secretKey = (PublicKey) new SecretKeySpec(barray, "RSA");
	if (secretKey == null) {
	    throw new Exception();
	}
    }
}
