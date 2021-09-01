/*
    Classe resonsável pelo recebimento de requisições de rede tanto UDP quanto
    TCP. No caso do processo para o protocolo UDP, ela instância novas threads
    para cada requisição de serviço. Já para TCP, ela instancia novas threads
    para cada pedido de estabelecimento de sessões, e a thread criada trata
    a requisições de serviço.
*/
package conexao;

import java.io.*;
import java.net.*;

public class ServerMultiThreaded {
    private final static boolean TCP = true;
    private final static String SERVERNAME = "localhost";
    private final static int SERVERPORT = 7000;

    public static void main(String[] args) throws IOException {
        if(TCP) {
            TCPServer();
        } else {
            UDPServer();
        }
    }
    
    private static void UDPServer() {
        DatagramSocket udpSocket;
        try {
            udpSocket = new DatagramSocket(SERVERPORT, InetAddress.getByName(SERVERNAME));
        } catch (UnknownHostException | SocketException ex) {
            return;
        }
        System.out.println("Starting Sever: " + SERVERNAME + " Port: " + SERVERPORT);
        boolean listening = true;
	byte[] buf = new byte[256]; 
	try {
            while(listening) {
                DatagramPacket pacote = new DatagramPacket(buf, buf.length);  
                udpSocket.receive(pacote);
                new UDPServerConnectionThread(pacote, udpSocket).start();
            }
        } catch (IOException ex) {
            udpSocket.close();
        }
        udpSocket.close();
    }
    
    private static void TCPServer() throws IOException{
        ServerSocket serverSocket;
	boolean listening = true;
	serverSocket = new ServerSocket(SERVERPORT);
	System.out.println("Starting Sever: " + SERVERNAME + " Port: " + SERVERPORT);
	while(listening) {
	    new TCPServerConnectionThread(serverSocket.accept()).start();
	}
	serverSocket.close();
    }
}
