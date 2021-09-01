package br.campos.sockets;

import java.util.concurrent.Executors;
import java.io.IOException;
import java.net.InetSocketAddress;

import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.executor.ExecutorFilter;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.SocketAcceptor;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import br.campos.sockets.util.Constants;

public class AppServer {
	// @formatter:off
	/*
	public static void main(String[] args) {
		Test teste1 = new Test("Teste1");
		Test teste2 = new Test("Teste2");
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// inicia a transação
			transaction = session.beginTransaction();
			// salva os objetos
			session.save(teste1);
			session.save(teste2);
			// Faz o commit da transação
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			List<Test> students = session.createQuery("from Test", Test.class).list();
			students.forEach(s -> System.out.println(s.getName()));
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}
	*/
	// @formatter:on

	private static int getThreadCount() {
		final int availableProcessors = Runtime.getRuntime().availableProcessors();
		return 2 * (availableProcessors - 1) - availableProcessors / 2 + 2;
	}

	public static void main(String[] args) throws IOException {
		SocketAcceptor acceptor = new NioSocketAcceptor(getThreadCount());
		DefaultIoFilterChainBuilder filterChainBuilder = acceptor.getFilterChain();
		filterChainBuilder.addLast("logger1", new LoggingFilter());
		filterChainBuilder.addLast("codec1",
				new ProtocolCodecFilter(new TextLineCodecFactory(Constants.CHARSET)));
		filterChainBuilder.addLast("threadPool", new ExecutorFilter(Executors.newCachedThreadPool()));
		acceptor.setHandler(new ServerHandler());
		acceptor.bind(new InetSocketAddress(Constants.PORT));
	}
}