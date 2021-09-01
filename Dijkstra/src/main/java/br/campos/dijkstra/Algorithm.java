package br.campos.dijkstra;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import br.campos.dijkstra.model.Edge;
import br.campos.dijkstra.model.Graph;
import br.campos.dijkstra.model.Vertex;

import java.util.LinkedList;

/**
 * Algoritmo de Dijkstra
 */
public class Algorithm {

	private final List<Edge> edges;
	private Set<Vertex> settledNodes;
	private Set<Vertex> unSettledNodes;
	private Map<Vertex, Vertex> predecessors;
	private Map<Vertex, Integer> distance;

	public Algorithm(Graph graph) {
		// Cria uma cópia do array para o processamento
		this.edges = new ArrayList<>(graph.getEdges());
	}

	public void execute(Vertex source) {
		settledNodes = new HashSet<>();
		unSettledNodes = new HashSet<>();
		distance = new HashMap<>();
		predecessors = new HashMap<>();
		distance.put(source, 0);
		unSettledNodes.add(source);
		while (unSettledNodes.size() > 0) {
			Vertex node = getMinimum(unSettledNodes);
			settledNodes.add(node);
			unSettledNodes.remove(node);
			findMinimalDistances(node);
		}
	}

	private void findMinimalDistances(Vertex node) {
		List<Vertex> adjacentNodes = getNeighbors(node);
		adjacentNodes.stream().filter(
				(target) -> (getShortestDistance(target) > getShortestDistance(node) + getDistance(node, target)))
				.map((target) -> {
					distance.put(target, getShortestDistance(node) + getDistance(node, target));
					return target;
				}).map((target) -> {
					predecessors.put(target, node);
					return target;
				}).forEachOrdered((target) -> {
					unSettledNodes.add(target);
				});

	}

	private int getDistance(Vertex node, Vertex target) {
		for (Edge edge : edges) {
			if (edge.getSource().equals(node) && edge.getDestination().equals(target)) {
				return edge.getWeight();
			}
		}
		throw new RuntimeException("Should not happen");
	}

	private List<Vertex> getNeighbors(Vertex node) {
		List<Vertex> neighbors = new ArrayList<>();
		edges.stream().filter((edge) -> (edge.getSource().equals(node) && !isSettled(edge.getDestination())))
				.forEachOrdered((edge) -> {
					neighbors.add(edge.getDestination());
				});
		return neighbors;
	}

	private Vertex getMinimum(Set<Vertex> vertexes) {
		Vertex minimum = null;
		for (Vertex vertex : vertexes) {
			if (minimum == null) {
				minimum = vertex;
			} else {
				if (getShortestDistance(vertex) < getShortestDistance(minimum)) {
					minimum = vertex;
				}
			}
		}
		return minimum;
	}

	private boolean isSettled(Vertex vertex) {
		return settledNodes.contains(vertex);
	}

	private int getShortestDistance(Vertex destination) {
		Integer d = distance.get(destination);
		if (d == null) {
			return Integer.MAX_VALUE;
		} else {
			return d;
		}
	}

	/**
	 * Método para retornar o caminho entre dois nós, retorna null se não houver
	 * caminho
	 *
	 * @param target Nó de destino
	 * @return Retona a lista ligada dos nós que compõem o caminho ou null
	 */
	public LinkedList<Vertex> getPath(Vertex target) {
		LinkedList<Vertex> path = new LinkedList<>();
		Vertex step = target;
		// Verifica se o caminho existe
		if (predecessors.get(step) == null) {
			return null;
		}
		path.add(step);
		while (predecessors.get(step) != null) {
			step = predecessors.get(step);
			path.add(step);
		}
		// Coloca na ordem certa
		Collections.reverse(path);
		return path;
	}
}
