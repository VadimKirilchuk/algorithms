package ru.vkirilchuk.algorithm.graph;

import ru.vkirilchuk.algorithm.graph.model.Edge;
import ru.vkirilchuk.algorithm.graph.model.Graph;
import ru.vkirilchuk.algorithm.graph.model.visitors.RecursiveDepthFirstTraverseVisitor;

public class DepthFirstSearcher<V> {
	
	private final class RecursiveDepthFirstSearchVisitor extends RecursiveDepthFirstTraverseVisitor<V> {
		private boolean found;
		private V targetVertex;

		private RecursiveDepthFirstSearchVisitor(V startVertex, V targetVertex) {
			super(startVertex);
			this.targetVertex = targetVertex;
		}

		@Override
		public void onNodeVisit(V vertex) {
			if (vertex.equals(targetVertex)) {
				found = true;
			}
		}
		
		public boolean isFound() {
			return found;
		}
	}

	public boolean search(Graph<V, Edge<V>> graph, V startVertex, V targetVertex) {
		RecursiveDepthFirstSearchVisitor visitor = new RecursiveDepthFirstSearchVisitor(startVertex, targetVertex);
		graph.accept(visitor);
		
		return visitor.isFound();
	}
}
