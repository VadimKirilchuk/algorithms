package ru.vkirilchuk.algorithm.graph.model.visitors;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import ru.vkirilchuk.algorithm.graph.model.Edge;
import ru.vkirilchuk.algorithm.graph.model.Graph;
import ru.vkirilchuk.algorithm.graph.model.GraphVisitor;

/**
 * http://www.algolist.net/Algorithms/Graph/Undirected/Depth-first_search
 * 
 * @author vkirilchuk
 */
public class RecursiveDepthFirstTraverseVisitor<V> implements GraphVisitor<V> {

	private static enum State {
		NOT_VISITED, VISITED
	};

	private final V startVertex;

	public RecursiveDepthFirstTraverseVisitor(V startVertex) {
		this.startVertex = startVertex;
	}

	@Override
	public void visit(Graph<V, Edge<V>> graph) {
		Set<V> vertexSet = graph.getVertexSet();
		Map<V, State> stateMap = new HashMap<>();
		for (V vertex : vertexSet) {
			stateMap.put(vertex, State.NOT_VISITED);
		}
		if (!vertexSet.contains(startVertex)) {
			throw new IllegalArgumentException("Graph doesn't contain start vertex: " + startVertex);
		}
		dfsRec(graph, startVertex, stateMap);
	}

	public void onNodeVisit(V currentVertex) {
		// do nothing, override in sub classes
	}
	
	private void dfsRec(Graph<V, Edge<V>> graph, V currentVertex, Map<V, State> stateMap) {
		stateMap.put(currentVertex, State.VISITED);
		onNodeVisit(currentVertex);
		
		// Recurse for all vertices adjacent to current vertex
		Set<Edge<V>> outgoingEdges = graph.getOutgoingEdges(currentVertex);
		for (Edge<V> edge : outgoingEdges) {
			V nextVertex = edge.getTarget();
			if (stateMap.get(nextVertex) == State.NOT_VISITED) {
				dfsRec(graph, nextVertex, stateMap);
			}
		}
	}
}
