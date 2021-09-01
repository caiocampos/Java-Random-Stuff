package br.campos.sockets;

import java.util.Date;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

public class ServerHandler extends IoHandlerAdapter {

	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
		String str = message.toString();

		System.out.println("Message received: " + str);

		if (str.trim().equalsIgnoreCase("quit")) {
			session.closeNow();
			return;
		}
		if (str.trim().equals("data")) {
			Date date = new Date();
			session.write(date.toString());
		} else {
			session.write("echo " + str);
		}
		System.out.println("Message written...");
	}
}