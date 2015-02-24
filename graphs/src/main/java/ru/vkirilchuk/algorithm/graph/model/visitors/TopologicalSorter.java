package ru.vkirilchuk.algorithm.graph.model.visitors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

//TODO: javadoc with links
//TODO: input and output should be parametrized by generics
//TODO: add different algorithms, current is depth-first-search
//TODO: create common graph representations (adjacent matrix, adjacent vectors,etc)
public class TopologicalSorter {

    private static enum Mark {
        NOT_VISITED, VISITED, EXHAUSTED
    }

    private final int             size;
    private final List<Integer>[] graph;
    private final Mark[]          marks;

    public TopologicalSorter(List<Integer>[] graph) {
        this.size = graph.length;
        this.graph = graph;
        this.marks = new Mark[size];
        Arrays.fill(marks, Mark.NOT_VISITED);
    }

    public List<Integer> sort() {
        List<Integer> result = new ArrayList<Integer>();
        for (int index = 0; index < size; index++) {
            if (marks[index] == Mark.NOT_VISITED) {
                depthFirstSearch(result, index);
            }
        }
        Collections.reverse(result);
        return result;
    }

    private void depthFirstSearch(List<Integer> result, int index) {
        marks[index] = Mark.VISITED;
        for (int adjacentIndex : graph[index]) {
            if (marks[adjacentIndex] == Mark.VISITED) { // cycle detected
                throw new RuntimeException("Graph must have no cycles. Cycle detected at element: " + adjacentIndex);
            }
            if (marks[adjacentIndex] == Mark.NOT_VISITED) {
                depthFirstSearch(result, adjacentIndex);
            }
        }
        marks[index] = Mark.EXHAUSTED;
        result.add(index);
    }
}