package Protocol;

import Server.ServerMultiThreaded;
import java.security.*;
import java.security.spec.*;
import javax.crypto.*;

public class ServiceProtocol {

    private Key secretKey = null;
    private PublicKey puKey = null;

    public ProtocolData processInput(ProtocolData theInput) {
	Cipher c = null;
	Signature s = null;
	ProtocolData theOutput = null;
	String sMessage = theInput.getMessage();
	byte[] barray = theInput.getBytes();
	if (sMessage.equalsIgnoreCase("CONNECT")) {
	    try {
		if (ServerMultiThreaded.cls.contains(theInput.getUser())) {
		    throw new Exception();
		} else {
		    ServerMultiThreaded.cls.add(theInput.getUser());
		    ProtocolData nOutput = new ProtocolData(theInput.getUser() + " entrou");
		    nOutput.setStatus(ServerMultiThreaded.getLastData().getStatus() + 1);
		    ServerMultiThreaded.writeOut(nOutput);
		    puKey = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(barray));
		    secretKey = ServerMultiThreaded.getKey("chave");
		    c = Cipher.getInstance("RSA");
		    c.init(Cipher.ENCRYPT_MODE, puKey);
		    barray = c.doFinal(secretKey.getEncoded());
		}
	    } catch (Exception e) {
		System.out.println("Erro: " + e.getMessage());
		return new ProtocolData("ERROR");
	    }
	    theOutput = new ProtocolData(barray);
	} else if (sMessage.equalsIgnoreCase("DATA")) {
	    try {
		s = Signature.getInstance("SHA1withRSA");
		c = Cipher.getInstance("AES");
		c.init(Cipher.DECRYPT_MODE, secretKey, c.getParameters());
		barray = c.doFinal(barray);
		s.initVerify(puKey);
		s.update(barray);
		if (!s.verify(theInput.getVer())) {
		    throw new Exception();
		} else {
		    barray = (theInput.getUser() + " disse: " + new String(barray)).getBytes();
		    c.init(Cipher.ENCRYPT_MODE, secretKey);
		    barray = c.doFinal(barray);
		}
	    } catch (Exception e) {
		System.out.println("Erro: " + e.getMessage());
		return new ProtocolData("ERROR");
	    }
	    theOutput = new ProtocolData(barray);
	    theOutput.setEncripted(true);
	    theOutput.setStatus(ServerMultiThreaded.getLastData().getStatus() + 1);
	} else if (sMessage.equalsIgnoreCase("EXIT")) {
	    ServerMultiThreaded.cls.remove(theInput.getUser());
	    barray = (theInput.getUser() + " saiu").getBytes();
	    theOutput = new ProtocolData(barray);
	    theOutput.setFinished(true);
	    theOutput.setStatus(ServerMultiThreaded.getLastData().getStatus() + 1);
	} else {
	    theOutput = new ProtocolData("Wrong message to the server");
	}
	return theOutput;
    }
}
