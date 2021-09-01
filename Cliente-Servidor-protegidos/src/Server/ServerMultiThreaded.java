package Server;

import Protocol.ProtocolData;
import java.io.*;
import java.net.*;
import java.security.*;
import java.util.*;
import javax.crypto.*;

public class ServerMultiThreaded {

    private static KeyStore ks = null;
    private final static String SERVERNAME = "localhost";
    private final static int SERVERPORT = 7000;
    private static ProtocolData lastData = new ProtocolData();
    private static ArrayList<ServerClientConnectionThread> sccts = new ArrayList<ServerClientConnectionThread>();
    
    public static ArrayList<String> cls = new ArrayList<String>();

    public static void main(String[] args) throws IOException {
	ServerSocket serverSocket = null;
	boolean listening = true;
	try {
	    loadKeyStore();
	    serverSocket = new ServerSocket(SERVERPORT);
	    System.out.println("Starting Sever: " + SERVERNAME + "Port: " + SERVERPORT);
	} catch (Exception e) {
	    System.out.println("Erro: " + e.getMessage());
	    return;
	}
	while (listening) {
	    ServerClientConnectionThread scct = new ServerClientConnectionThread(serverSocket.accept());
	    sccts.add(scct);
	    scct.start();
	}
	serverSocket.close();
    }

    public static void loadKeyStore() throws Exception {
	char[] keyStorePassword = "keypwstring".toCharArray();
	ks = KeyStore.getInstance("JCEKS");
	File keyStoreFile = new File("myks.keystore");
	if (!keyStoreFile.exists()) {
	    ks.load(null, null);
	    ks.setKeyEntry("chave", KeyGenerator.getInstance("AES").generateKey(), keyStorePassword, null);
	    FileOutputStream fos = new FileOutputStream(keyStoreFile);
	    ks.store(fos, keyStorePassword);
	    fos.close();
	} else {
	    FileInputStream fis = new FileInputStream(keyStoreFile);
	    ks.load(fis, null);
	    fis.close();
	}
    }

    public static Key getKey(String s) throws Exception {
	char[] keyStorePassword = "keypwstring".toCharArray();
	return ks.getKey(s, keyStorePassword);
    }

    public static void setKey(String alias, Key k) throws Exception {
	char[] keyStorePassword = "keypwstring".toCharArray();
	File keyStoreFile = new File("myks.keystore");
	ks.setKeyEntry(alias, k, keyStorePassword, null);
	FileOutputStream fos = new FileOutputStream(keyStoreFile);
	ks.store(fos, keyStorePassword);
	fos.close();
    }

    public static ProtocolData getLastData() {
	return lastData;
    }
    
    public static void remThread(ServerClientConnectionThread scct){
	sccts.remove(scct);
    }
    
    public static void addThread(ServerClientConnectionThread scct){
	sccts.add(scct);
    }

    public static void writeOut(ProtocolData pd) {
	lastData = pd;
	sccts.remove(null);
	for (ServerClientConnectionThread scct : sccts) {
	    try {
		if (scct.isAlive()) {
		    scct.getOut().writeObject(pd);
		} else {
		    sccts.remove(scct);
		}
	    } catch (Exception e) {
		System.out.println("Erro: " + e.getMessage());
	    }
	}
    }
}
