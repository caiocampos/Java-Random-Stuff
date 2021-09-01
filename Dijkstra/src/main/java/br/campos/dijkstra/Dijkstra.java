package br.campos.dijkstra;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import br.campos.dijkstra.model.Edge;
import br.campos.dijkstra.model.Graph;
import br.campos.dijkstra.model.Vertex;

/**
 *
 * @author Caio
 */
public class Dijkstra {

	private static Map<String, Vertex> nodes;
	private static List<Edge> edges;

	/**
	 * @param args argumentos da linah de comando (não usado)
	 */
	public static void main(String[] args) {
		nodes = new HashMap<>();
		edges = new ArrayList<>();

		try (Scanner reader = new Scanner(System.in)) {
			System.out.print("Entre com o número de nós (maior que 0): ");
			int tamanho;
			try {
				tamanho = Integer.parseInt(reader.next());
			} catch (Exception e) {
				tamanho = -1;
			}
			if (tamanho < 1) {
				System.out.println("Número de nós inválido, desligando!");
				return;
			}
			System.out.println();
			for (int i = 0; i < tamanho; i++) {
				System.out.print("Entre com o nome do " + (i + 1) + "º nó: ");
				String nome = reader.next();
				nodes.put(nome, new Vertex("V-" + i, nome));
			}
			System.out.println();
			System.out.println("Nós criados:");
			nodes.keySet().forEach((nome) -> {
				System.out.println(nome);
			});
			System.out.println();
			System.out.println("Crie os caminhos");
			int lastId = 0;
			while (true) {
				System.out.print("Deseja sair? ('S' para sair): ");
				if (reader.next().equalsIgnoreCase("S")) {
					break;
				}
				System.out.print("Nome do nó de origem: ");
				Vertex noOrgm = validaNo(reader.next());
				if (noOrgm == null) {
					continue;
				}
				System.out.print("Nome do nó de destino: ");
				Vertex noDstn = validaNo(reader.next());
				if (noDstn == null) {
					continue;
				}
				System.out.print("Peso do caminho (maior que 0): ");
				int peso;
				try {
					peso = Integer.parseInt(reader.next());
				} catch (NumberFormatException e) {
					peso = -1;
				}
				if (peso < 1) {
					System.out.println("Peso inválido!");
					continue;
				}
				Edge lane = new Edge("V-" + lastId++, noOrgm, noDstn, peso);
				edges.add(lane);
			}
			Graph graph = new Graph(new ArrayList<>(nodes.values()), edges);
			System.out.println();
			System.out.println("Agora executaremos o algoritmo!");
			while (true) {
				System.out.print("Deseja sair? ('S' para sair): ");
				if (reader.next().equalsIgnoreCase("S")) {
					break;
				}
				System.out.print("Nome do nó de origem: ");
				Vertex noOrgm = validaNo(reader.next());
				if (noOrgm == null) {
					continue;
				}
				System.out.print("Nome do nó de destino: ");
				Vertex noDstn = validaNo(reader.next());
				if (noDstn == null) {
					continue;
				}
				Algorithm dijkstra = new Algorithm(graph);
				dijkstra.execute(noOrgm);
				LinkedList<Vertex> path = dijkstra.getPath(noDstn);
				path.forEach((vertex) -> {
					System.out.println(vertex);
				});
			}
		}
	}

	/**
	 * Valida se o nó já foi criado
	 */
	private static Vertex validaNo(String nome) {
		Vertex v = nodes.get(nome);
		if (v == null) {
			System.out.println("Nome incorreto!");
		}
		return v;
	}
}
