package ru.vkirilchuk.algorithm.graph.model;

// TODO: do we need E as parameter also?
public interface GraphVisitor<V> {

	void visit(Graph<V, Edge<V>> graph);
}
