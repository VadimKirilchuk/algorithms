package ru.vkirilchuk.algorithm.graph.model.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import ru.vkirilchuk.algorithm.graph.model.Edge;
import ru.vkirilchuk.algorithm.graph.model.Graph;
import ru.vkirilchuk.algorithm.graph.model.GraphVisitor;
import ru.vkirilchuk.algorithm.graph.util.Edges;

// TODO: somehow distinguish directed and undirected cases
public class AdjacencyListGraph implements Graph<Integer, Edge<Integer>> {

	private final Map<Integer, Set<Edge<Integer>>> vertexEdgeMap = new HashMap<>();

	@Override
	public boolean addVertex(Integer vertex) {
		if (vertexEdgeMap.containsKey(vertex)) {
			return false;
		} 
		
		Set<Edge<Integer>> edgeSet = new HashSet<>();
		vertexEdgeMap.put(vertex, edgeSet);
		return true;
	}

	@Override
	public boolean addEdge(Edge<Integer> edge) {
		Integer vertex1 = edge.getSource();
		Integer vertex2 = edge.getTarget();
		
		Set<Edge<Integer>> vertex1Edges = vertexEdgeMap.get(vertex1);
		if (vertex1Edges == null) {
			throw new IllegalArgumentException("The graph doesn't have vertex: " + vertex1);
		}
		
		Set<Edge<Integer>> vertex2Edges = vertexEdgeMap.get(vertex2);
		if (vertex2Edges == null) {
			throw new IllegalArgumentException("The graph doesn't have vertex: " + vertex2);
		}
		
		// TODO: undirected/directed
		if (vertex1Edges.contains(edge)) {
			return false;
		}
		
		// TODO: undirected/directed
		// everything ok
		vertex1Edges.add(edge);
		vertex2Edges.add(Edges.reverseEdge(edge));
		
		return true;
	}

	@Override
	public boolean containsVertex(Integer vertex) {
		return vertexEdgeMap.containsKey(vertex);
	}

	@Override
	public Set<Integer> getVertexSet() {
		return vertexEdgeMap.keySet();
	}

	@Override
	public boolean containsEdge(Edge<Integer> edge) {
		// TODO directed/undirected
		
		Integer source = edge.getSource();
		Set<Edge<Integer>> edgeSet = vertexEdgeMap.get(source);
		return edgeSet.contains(edge);
	}

	@Override
	public boolean containsEdge(Integer sourceVertex, Integer targetVertex) {
		// TODO directed/undirected
		Set<Edge<Integer>> edgeSet = vertexEdgeMap.get(sourceVertex);
		for (Edge<Integer> edge : edgeSet) {
			if (edge.getTarget().equals(targetVertex)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Edge<Integer> getEdge(Integer sourceVertex, Integer targetVertex) {
		// TODO directed/undirected
		Set<Edge<Integer>> edgeSet = vertexEdgeMap.get(sourceVertex);
		for (Edge<Integer> edge : edgeSet) {
			if (edge.getTarget().equals(targetVertex)) {
				return edge;
			}
		}
		
		return null;
	}

	@Override
	public Set<Edge<Integer>> getOutgoingEdges(Integer vertex) {
		Set<Edge<Integer>> edgeSet = vertexEdgeMap.get(vertex);
		if (edgeSet == null) {
			return Collections.emptySet();
		}
		
		return edgeSet;
	}

	@Override
	public Set<Edge<Integer>> getEdgeSet() {
		Collection<Set<Edge<Integer>>> values = vertexEdgeMap.values();
		Set<Edge<Integer>> result = new HashSet<>();
		for (Set<Edge<Integer>> edgeSet : values) {
			result.addAll(edgeSet);
		}
		return result;
	}
	
	@Override
	public void accept(GraphVisitor<Integer> visitor) {
		visitor.visit(this);
	}
}
