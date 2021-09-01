package br.ufu.facom.tesi.Server;

import java.io.*;
import java.net.*;
import java.security.*;

public class KeyServerMultiThreaded {

    private static char[] keyStorePassword = "serverstdpw".toCharArray();
    private static File keyStoreFile = null;
    private static KeyStore ks = null;
    private final static String SERVERNAME = "localhost";
    private final static int SERVERPORT = 7001;

    public static void main(String[] args) throws IOException {
	ServerSocket serverSocket = null;
	boolean listening = true;
	try {
	    loadKeyStore();
	} catch (Exception e) {
	    System.out.println("Erro: " + e.getMessage());
	}
	try {
	    serverSocket = new ServerSocket(SERVERPORT);
	    System.out.println("Starting Sever: " + SERVERNAME + "Port: " + SERVERPORT);
	} catch (IOException e) {
	    System.err.println("Server Error: " + "I/O error while connecting to port " + SERVERPORT);
	    System.exit(-1);
	}
	while (listening) {
	    new KeyServerConnectionThread(serverSocket.accept()).start();
	}
	serverSocket.close();
    }

    public static void loadKeyStore() throws Exception {
	ks = KeyStore.getInstance("JCEKS");
	keyStoreFile = new File("ks.keystore");
	if (!keyStoreFile.exists()) {
	    ks.load(null, null);
	    FileOutputStream fos = new FileOutputStream(keyStoreFile);
	    ks.store(fos, keyStorePassword);
	    fos.close();
	} else {
	    FileInputStream fis = new FileInputStream(keyStoreFile);
	    ks.load(fis, null);
	    fis.close();
	}
    }

    public static Key getKey(String alias) throws Exception {
	return ks.getKey(alias, keyStorePassword);
    }

    public static void setKey(String alias, Key k) throws Exception {
	ks.setKeyEntry(alias, k, keyStorePassword, null);
	FileOutputStream fos = new FileOutputStream(keyStoreFile);
	ks.store(fos, keyStorePassword);
	fos.close();
    }
}
