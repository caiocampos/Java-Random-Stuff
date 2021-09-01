/*
    Classe responsável pela conexão de rede por parte do cliente. Ela possui
    métodos para o envio e recebimento de dados usando tanto o protocolo TCP
    quanto o UDP.
*/
package conexao;

import modelo.ProtocolData;
import java.io.*;
import java.net.*;
import java.util.logging.*;

public class ConexaoClient {
    private String SERVERNAME = "localhost";
    private int SERVERPORT = 7000;
    private final boolean TCP = true;
    private Socket tcpSocket;
    private ObjectOutputStream tcpOut;
    private ObjectInputStream tcpIn;
    private DatagramSocket udpSocket;

    public ConexaoClient() {
        instanciador();
    }
    
    public ConexaoClient(String nome, int porta) {
        SERVERNAME = nome;
        SERVERPORT = porta;
        instanciador();
    }
    
    private void instanciador() {
        try {
            if(TCP) {
                tcpSocket = new Socket(SERVERNAME, SERVERPORT);
                tcpOut = new ObjectOutputStream(tcpSocket.getOutputStream());
                tcpIn = new ObjectInputStream(tcpSocket.getInputStream());
                tcpIn.readObject();
            }
            else udpSocket = new DatagramSocket();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ConexaoClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean enviar(ProtocolData pd) {
	if(TCP) return enviarTCP(pd);
        else    return enviarUDP(pd);
    }
    
    public ProtocolData receber() {
	if(TCP) return receberTCP();
        else    return receberUDP();
    }

    private boolean enviarTCP(ProtocolData pd) {
        try {
            tcpOut.writeObject(pd);
            return true;
        } catch (IOException ex) {
            Logger.getLogger(ConexaoClient.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    private boolean enviarUDP(ProtocolData pd) {
        byte[] buf = pd.toString().getBytes();
        try {
            DatagramPacket pacote = new DatagramPacket(buf, buf.length,
                    InetAddress.getByName(SERVERNAME), SERVERPORT);
            udpSocket.send(pacote);
            return true;
        } catch (IOException ex) {
            Logger.getLogger(ConexaoClient.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    private ProtocolData receberTCP() {
        ProtocolData data;
        try {
            data = (ProtocolData) tcpIn.readObject();
            return data;
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ConexaoClient.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    private ProtocolData receberUDP() {
        byte[] buf;
        ProtocolData data;
        buf = new byte[256];
        String s;
        DatagramPacket pacote = new DatagramPacket(buf, buf.length);
        try {
            udpSocket.receive(pacote);
            s = new String(buf);
            data = new ProtocolData(s);
            return data;
        } catch (IOException ex) {
            Logger.getLogger(ConexaoClient.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
