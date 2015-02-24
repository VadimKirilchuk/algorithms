package ru.vkirilchuk.algorithm.graph.model.visitors;

public class DebugDFSVisitor<V> extends RecursiveDepthFirstTraverseVisitor<V> {

	public DebugDFSVisitor(V startVertex) {
		super(startVertex);
	}

	@Override
	public void onNodeVisit(V currentVertex) {
		System.out.println(currentVertex);
	}
}
