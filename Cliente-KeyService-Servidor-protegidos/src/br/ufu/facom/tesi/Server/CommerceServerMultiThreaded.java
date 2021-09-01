package br.ufu.facom.tesi.Server;

import java.io.*;
import java.net.*;

public class CommerceServerMultiThreaded {

    private final static String SERVERNAME = "localhost";
    private final static int SERVERPORT = 7000;

    public static void main(String[] args) throws IOException {
	ServerSocket serverSocket = null;
	boolean listening = true;
	try {
	    serverSocket = new ServerSocket(SERVERPORT);
	    System.out.println("Starting Sever: " + SERVERNAME + "Port: " + SERVERPORT);
	} catch (IOException e) {
	    System.err.println("Server Error: " + "I/O error while connecting to port " + SERVERPORT);
	    System.exit(-1);
	}
	while (listening) {
	    new CommerceServerConnectionThread(serverSocket.accept()).start();
	}
	serverSocket.close();
    }
}
