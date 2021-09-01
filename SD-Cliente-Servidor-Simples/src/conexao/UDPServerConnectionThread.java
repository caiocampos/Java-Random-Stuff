/*
    Thread responsável por desempacotar as requisições de serviços UDP e os
    enviar para que controle possa processar. Por fim esta classe envia a
    resposta processada para o cliente.
*/
package conexao;

import modelo.ProtocolData;
import controle.ProcessoServidor;
import java.io.IOException;
import java.net.*;
import java.util.logging.*;

public class UDPServerConnectionThread extends Thread {

    private DatagramPacket pacote = null;
    private DatagramSocket udpSocket = null;

    UDPServerConnectionThread(DatagramPacket pacote, DatagramSocket udpSocket) {
        super("UDPServerConnectionThread");
	this.pacote = pacote;
	this.udpSocket = udpSocket;
    }

    @Override
    public void run() {
	byte[] bufIn, bufOut;
        ProtocolData input, output;
        String s;
        DatagramPacket resultado;
        bufIn = pacote.getData();
        s = new String(bufIn);
        input = new ProtocolData(s);
        output = ProcessoServidor.run(input);
        bufOut = output.toString().getBytes();
        resultado = new DatagramPacket(bufOut, bufOut.length, pacote.getAddress(), pacote.getPort());
        try {
            udpSocket.send(resultado);
        } catch (IOException ex) {
            Logger.getLogger(UDPServerConnectionThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
