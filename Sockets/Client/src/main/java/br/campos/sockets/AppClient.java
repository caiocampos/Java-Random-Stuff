package br.campos.sockets;

import java.util.Scanner;
import java.net.InetSocketAddress;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.SocketConnector;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import br.campos.sockets.util.Constants;

public class AppClient {
	public static void main(String[] args) throws Exception {
		SocketConnector connector = new NioSocketConnector();
		// Configurando
		configureConnector(connector);
		// Cria sessao
		IoSession session = connect(connector);
		if (session != null) {
			// Envia comandos do teclado
			sendCommands(session);
		}
		// Encerra conexoes
		close(connector, session);
	}

	/*
	 * Configura NioSocketConnector
	 */
	public static void configureConnector(final SocketConnector connector) {
		connector.setConnectTimeoutMillis(Constants.TIMEOUT);

		connector.getFilterChain().addLast("codec",
				new ProtocolCodecFilter(new TextLineCodecFactory(Constants.CHARSET)));
		connector.getFilterChain().addLast("logger", new LoggingFilter());

		connector.setHandler(new ClientHandler());
	}

	/*
	 * Conexao com o server
	 */
	private static IoSession connect(final SocketConnector connector) throws Exception {
		IoSession session = null;
		try {
			ConnectFuture future = connector.connect(new InetSocketAddress(Constants.HOSTNAME, Constants.PORT));
			future.awaitUninterruptibly();
			session = future.getSession();
		} catch (Exception e) {
			System.err.println("Failed to connect.");
			e.printStackTrace();
		}
		return session;
	}

	/*
	 * Envia comando do teclado ao servidor
	 */
	private static void sendCommands(final IoSession session) {
		final Scanner scanner = new Scanner(System.in);
		String text;
		do {
			System.out.println("Entre com texto: ");
			text = scanner.nextLine();
			session.write(text);
		} while (!"quit".equalsIgnoreCase(text));
		scanner.close();
	}

	/*
	 * Encerra conexao
	 */
	private static void close(final SocketConnector connector, final IoSession session) {
		if (session != null) {
			if (session.isConnected()) {
				session.closeNow();
				session.getCloseFuture().awaitUninterruptibly();
			}
		}
		connector.dispose();
	}
}