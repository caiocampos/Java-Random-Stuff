package br.ufu.facom.tesi.Protocol;

import br.ufu.facom.tesi.Server.KeyServerMultiThreaded;
import java.security.*;
import javax.crypto.spec.SecretKeySpec;

public class KeyServiceProtocol {

    private Key secretKey = null;

    public ProtocolData processInput(ProtocolData theInput) {
	ProtocolData theOutput = null;
	String sMessage = theInput.getMessage();
	if (sMessage.equalsIgnoreCase("SETKEY")) {
	    try {
		secretKey = new SecretKeySpec(theInput.getBytes(), "RSA");
		KeyServerMultiThreaded.setKey(theInput.getNome(), secretKey);
	    } catch (Exception e) {
		System.out.println("Erro: " + e.getMessage());
		return new ProtocolData("ERROR".getBytes());
	    }
	    theOutput = new ProtocolData("Key has been accepted");
	} else if (sMessage.equalsIgnoreCase("GETKEY")) {
	    try {
		secretKey = KeyServerMultiThreaded.getKey(theInput.getNome());
		theOutput = new ProtocolData(secretKey.getEncoded());
	    } catch (Exception e) {
		System.out.println("Erro: " + e.getMessage());
		return new ProtocolData("ERROR".getBytes());
	    }
	} else {
	    return new ProtocolData("Wrong message to the server".getBytes());
	}
	theOutput.setFinished(true);
	return theOutput;
    }
}
