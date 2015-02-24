package ru.vkirilchuk.algorithm.graph.model;
/**
 * Represents edge between source and target vertexes
 * 
 * @param <V> vertex type
 * 
 * @author vkirilchuk
 */
public class Edge<V> implements Cloneable{
	private final V source;
	private final V target;
	
	public Edge(V source, V target) {
		this.source = source;
		this.target = target;
	}
	
	public V getSource() {
		return source;
	}
	
	public V getTarget() {
		return target;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + source.hashCode();
		result = prime * result + target.hashCode();
		return result;
	}

	/**
	 * Does not allow comparison of sub with superclass objects.
	 * So, the class prohibits mixed-type comparison.
	 * This is done by purpose. 
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		
		if (obj == null) {
			return false;
		}
		
		if (getClass() != obj.getClass()) {
			return false;
		}
		
		@SuppressWarnings("rawtypes")
		Edge other = (Edge) obj;
		
		if (!source.equals(other.source)) {
			return false;
		}
		
		if (!target.equals(other.target)) {
			return false;
		}
		
		return true;
	}

	@Override
	public String toString() {
		return "Edge [source=" + source + ", target=" + target + "]";
	}
	
}
