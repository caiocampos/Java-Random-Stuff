package main.losango;

/**
 *
 * @author Caio
 */
public class Losango {

	/**
	 * @param args argumentos da linah de comando (não usado)
	 */
	public static void main(String[] args) {
		int doisPontos = 22;
		int numberSign = 1;
		int total = 45, linhas = 10;
		int i;

		for (i = 0; i < linhas; i++) {
			if (i == 9) {
				imprimirCaracter(':', total);
				System.out.println();
			} else {
				imprimirCaracter(':', doisPontos);
				imprimirCaracter('#', numberSign);
				imprimirCaracter(':', doisPontos);
				System.out.println();

				if (i > 3) {
					doisPontos += 3;
					numberSign -= 6;
				} else {
					doisPontos -= 3;
					numberSign += 6;
				}
			}
		}
	}

	/**
	 * Método para imprimir caracteres na tela
	 *
	 * @param caractere: O caractere que deve ser impresso na tela
	 * @param quantidade: quantas vezes que o caractere será impresso
	 */
	private static void imprimirCaracter(char caractere, int quantidade) {
		for (int i = 0; i < quantidade; i++) {
			System.out.print(caractere);
		}
	}
}
