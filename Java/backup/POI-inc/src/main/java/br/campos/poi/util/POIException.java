package br.campos.poi.util;

/**
 * Exceção personalizada para melhor tratativa
 * 
 * @author caio
 */
public class POIException extends Exception {
	private static final long serialVersionUID = 4979492878603725031L;

	/**
	 * Construtor de Exceção
	 */
	public POIException() {
		super();
	}

	/**
	 * Construtor de Exceção
	 * 
	 * @param msg mensagem de erro
	 */
	public POIException(String msg) {
		super(msg);
	}

	/**
	 * Construtor de Exceção
	 * 
	 * @param throwable origem da Exceção
	 */
	public POIException(Throwable throwable) {
		super(throwable);
	}

	/**
	 * Construtor de Exceção
	 * 
	 * @param msg       mensagem de erro
	 * @param throwable origem da Exceção
	 */
	public POIException(String msg, Throwable throwable) {
		super(msg, throwable);
	}
}
