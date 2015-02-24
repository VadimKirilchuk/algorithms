package ru.vkirilchuk.algorithm.graph.util;

import ru.vkirilchuk.algorithm.graph.model.Edge;

public final class Edges {

	public static <V> Edge<V> newEdge(V source, V target) {
		return new Edge<V>(source, target);
	}
	
	public static <V> Edge<V> reverseEdge(Edge<V> edge) {
		return new Edge<V>(edge.getTarget(), edge.getSource());
	}
	
	private Edges() {
	}
}
