package ru.vkirilchuk.algorithm.graph;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import ru.vkirilchuk.algorithm.graph.model.visitors.TopologicalSorter;

import com.carrotsearch.randomizedtesting.RandomizedTest;

//TODO: more tests
public class TopologicalSorterTest extends RandomizedTest {

    private TopologicalSorter underTest;

    @Test //TODO: can it be non reversed?
    public void testOrderReversedForNotDependentElements() {
        int size = atLeast(3);
        
        @SuppressWarnings("unchecked")
        List<Integer>[] graph = new List[size];
        
        for (int index = 0; index < size; ++index) {
            graph[index] = new ArrayList<Integer>();
        }

        underTest = new TopologicalSorter(graph);
        List<Integer> result = underTest.sort();
        assertEquals("Result preserves number of elements in graph", graph.length, result.size());

        for (int index = 0; index < size; ++index) {
            assertEquals("Result values are descending integers", size - index - 1, (int) result.get(index));
        }
    }

}
