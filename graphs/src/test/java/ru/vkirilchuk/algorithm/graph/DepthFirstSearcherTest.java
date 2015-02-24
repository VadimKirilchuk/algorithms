package ru.vkirilchuk.algorithm.graph;

import org.junit.Before;
import org.junit.Test;

import ru.vkirilchuk.algorithm.graph.model.Edge;
import ru.vkirilchuk.algorithm.graph.model.impl.AdjacencyListGraph;
import ru.vkirilchuk.algorithm.graph.model.visitors.DebugDFSVisitor;
import ru.vkirilchuk.algorithm.graph.model.visitors.RecursiveDepthFirstTraverseVisitor;

import com.carrotsearch.randomizedtesting.RandomizedTest;

//TODO: more tests
public class DepthFirstSearcherTest extends RandomizedTest {

	private DepthFirstSearcher<Integer> underTest;

	@Before
	public void before() {
		underTest = new DepthFirstSearcher<>();
	}

	@Test
	public void testConnectedGraph() {
		int size = atLeast(3);
		
		AdjacencyListGraph graph = new AdjacencyListGraph();
		for (int i = 0; i < size; ++i) {
			graph.addVertex(i);
		}
		
		for (int i = 0; i < size; ++i) {
			for (int j = i + 1; j < size; ++j) {
				graph.addEdge(new Edge<Integer>(i, j));
			}
		}
		
		for (int i = 0; i < size; ++i) {
			for (int j = 0; j < size; ++j) {
				// there is path between any two vertexes
				assertTrue(underTest.search(graph, i, j));
			}
		}
	}
	
	@Test
	public void testLinearGraph() {
		int size = atLeast(3);
		
		AdjacencyListGraph graph = new AdjacencyListGraph();
		for (int i = 1; i < size; ++i) {
			// linear structure
			graph.addVertex(i - 1);
			graph.addVertex(i);
			graph.addEdge(new Edge<Integer>(i - 1, i));
		}
		
		assertTrue(underTest.search(graph, 0, size - 1));
	}
}
