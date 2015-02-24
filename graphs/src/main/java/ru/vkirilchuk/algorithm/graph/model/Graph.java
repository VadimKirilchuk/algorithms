package ru.vkirilchuk.algorithm.graph.model;

import java.util.*;

/**
 * <p>In mathematics, and more specifically in graph theory, a graph is a
 * representation of a set of objects where some pairs of objects are connected
 * by links. The interconnected objects are represented by mathematical
 * abstractions called vertices, and the links that connect some pairs of
 * vertices are called edges.</p>
 * 
 * <p>Through generics, a graph can be typed to specific classes for vertices
 * <code>V</code> and edges <code>E</code>.
 * 
 * TODO: Different data structures for the representation of graphs are
 * used in practice: 
 * <li>Adjacency list Vertices are stored as records or
 * objects, and every vertex stores a list of adjacent vertices. This data
 * structure allows the storage of additional data on the vertices.
 * Additional data can be stored if edges are also stored as objects, in
 * which case each vertex stores its incident edges and each edge stores its
 * incident vertices. 
 * <li>Adjacency matrix A two-dimensional matrix, in which
 * the rows represent source vertices and columns represent destination
 * vertices. Data on edges and vertices must be stored externally. Only the
 * cost for one edge can be stored between each pair of vertices. 
 * <li>Incidence
 * matrix A two-dimensional Boolean matrix, in which the rows represent the
 * vertices and columns represent the edges. The entries indicate whether
 * the vertex at a row is incident to the edge at a column.
 *
 * @see <a href="http://en.wikipedia.org/wiki/Graph_(mathematics)">http://en.wikipedia.org/wiki/Graph_(mathematics)</a>.
 * @see <a href="http://mathworld.wolfram.com/Graph.html">http://mathworld.wolfram.com/Graph.html</a>.
 * @author vkirilchuk
 */
public interface Graph<V, E extends Edge<V>> {
	
	/**
	 * Adds the specified vertex to this graph if not already present. More
	 * formally, adds the specified vertex, <code>v</code>, to this graph if
	 * this graph contains no vertex <code>u</code> such that <code>
	 * u.equals(v)</code>. If this graph already contains such vertex, the call
	 * leaves this graph unchanged and returns <tt>false</tt>. In combination
	 * with the restriction on constructors, this ensures that graphs never
	 * contain duplicate vertices.
	 *
	 * @param v
	 *            vertex to be added to this graph.
	 *
	 * @return <tt>true</tt> if this graph did not already contain the specified
	 *         vertex.
	 *
	 * @throws NullPointerException
	 *             if the specified vertex is <code>
	 * null</code>.
	 */
	boolean addVertex(V v);
	
	/**
	 * Adds the specified edge to this graph, going from its source vertex to
	 * its target vertex. More formally, adds the specified edge, <code>
	 * e</code>, to this graph if this graph contains no edge <code>e2</code>
	 * such that <code>e2.equals(e)</code>. If this graph already contains such
	 * an edge, the call leaves this graph unchanged and returns <tt>false</tt>.
	 * Some graphs do not allow edge-multiplicity. In such cases, if the graph
	 * already contains an edge from the specified source to the specified
	 * target, than this method does not change the graph and returns <code>
	 * false</code>. If the edge was added to the graph, returns <code>
	 * true</code>.
	 *
	 * <p>
	 * The source and target vertices must already be contained in this graph.
	 * If they are not found in graph IllegalArgumentException is thrown.
	 * </p>
	 *
	 * @param e
	 *            edge to be added to this graph.
	 *
	 * @return <tt>true</tt> if this graph did not already contain the specified
	 *         edge.
	 *
	 * @throws IllegalArgumentException
	 *             if source or target vertices are not found in the graph.
	 */
	boolean addEdge(E e);
	
	/**
	 * Returns <tt>true</tt> if this graph contains the specified vertex. More
	 * formally, returns <tt>true</tt> if and only if this graph contains a
	 * vertex <code>u</code> such that <code>u.equals(v)</code>. If the
	 * specified vertex is <code>null</code> returns <code>false</code>.
	 *
	 * @param v
	 *            vertex whose presence in this graph is to be tested.
	 *
	 * @return <tt>true</tt> if this graph contains the specified vertex.
	 */
	boolean containsVertex(V v);
	
	/**
	 * Returns a set of the vertices contained in this graph. The set is backed
	 * by the graph, so changes to the graph are reflected in the set. If the
	 * graph is modified while an iteration over the set is in progress, the
	 * results of the iteration are undefined.
	 *
	 * <p>
	 * The graph implementation may maintain a particular set ordering (e.g. via
	 * {@link java.util.LinkedHashSet}) for deterministic iteration, but this is
	 * not required. It is the responsibility of callers who rely on this
	 * behavior to only use graph implementations which support it.
	 * </p>
	 *
	 * @return a set view of the vertices contained in this graph.
	 */
	Set<V> getVertexSet();
	
	/**
	 * Returns <tt>true</tt> if this graph contains the specified edge. More
	 * formally, returns <tt>true</tt> if and only if this graph contains an
	 * edge <code>e2</code> such that <code>e.equals(e2)</code>. If the
	 * specified edge is <code>null</code> returns <code>false</code>.
	 *
	 * @param e
	 *            edge whose presence in this graph is to be tested.
	 *
	 * @return <tt>true</tt> if this graph contains the specified edge.
	 */
	boolean containsEdge(E e);
	
	/**
	 * Returns <tt>true</tt> if and only if this graph contains an edge going
	 * from the source vertex to the target vertex. In undirected graphs the
	 * same result is obtained when source and target are inverted. If any of
	 * the specified vertices does not exist in the graph, or if is <code>
	 * null</code>, returns <code>false</code>.
	 *
	 * @param sourceVertex
	 *            source vertex of the edge.
	 * @param targetVertex
	 *            target vertex of the edge.
	 *
	 * @return <tt>true</tt> if this graph contains the specified edge.
	 */
	boolean containsEdge(V sourceVertex, V targetVertex);
	
	/**
	 * Returns an edge connecting source vertex to target vertex if such
	 * vertices and such edge exist in this graph. Otherwise returns <code>
	 * null</code>. If any of the specified vertices is <code>null</code>
	 * returns <code>null</code>
	 *
	 * <p>
	 * In undirected graphs, the returned edge may have its source and target
	 * vertices in the opposite order.
	 * </p>
	 *
	 * @param sourceVertex
	 *            source vertex of the edge.
	 * @param targetVertex
	 *            target vertex of the edge.
	 *
	 * @return an edge connecting source vertex to target vertex.
	 */
	E getEdge(V sourceVertex, V targetVertex);

	/**
	 * TODO: javadoc. For directed only directed edges, for undirected any edge
	 * 
	 * @param vertex
	 * @return
	 */
	Set<E> getOutgoingEdges(V vertex);
	
	/**
	 * Returns a set of the edges contained in this graph. The set is backed by
	 * the graph, so changes to the graph are reflected in the set. If the graph
	 * is modified while an iteration over the set is in progress, the results
	 * of the iteration are undefined.
	 *
	 * <p>
	 * The graph implementation may maintain a particular set ordering (e.g. via
	 * {@link java.util.LinkedHashSet}) for deterministic iteration, but this is
	 * not required. It is the responsibility of callers who rely on this
	 * behavior to only use graph implementations which support it.
	 * </p>
	 *
	 * @return a set of the edges contained in this graph.
	 */
	Set<E> getEdgeSet();
	
	void accept(GraphVisitor<V> visitor);
}
