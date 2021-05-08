package guava.graph;

import com.google.common.graph.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.Set;
//https://github.com/google/guava/wiki/GraphsExplained
public class GraphTest {
    @Test
    public void testMutableNetwork() {
        MutableNetwork<Integer, String> network = NetworkBuilder.directed().build();
        network.addNode(1);
        // also adds nodes 2 and 3 if not already present
        network.addEdge( 2, 3, "2->3");
        network.addEdge( 3, 4, "3->4");
        network.addEdge( 3, 5, "3->5");

        // no effect; this edge is already present
        network.addEdge( 2, 3, "2->3");

        // throws; Network disallows parallel edges
//        network.addEdge( 2, 3, "2->3 too");

        // returns {3}
        Set<Integer> successorsOfTwo = network.successors(2);
        // returns {"2->3"}
        Set<String> outEdgesOfTwo = network.outEdges(2);
        // returns {3}
        Set<Integer> adjacentOfTwo = network.adjacentNodes(2);

        // returns {4, 5}
        Set<Integer> successorsOfThree = network.successors(3);
        // returns {"3->4", "4->5"}
        Set<String> outEdgesOfThree = network.outEdges(3);
        // returns {2, 4, 5}
        Set<Integer> adjacentOfThree = network.adjacentNodes(3);

        // throws; node not in graph
//        Set<String> inEdgesOfFour = network.inEdges(99);
    }

    @Test
    public void testImmutableGraph() {
        ImmutableGraph<Integer> graph =
                GraphBuilder.directed()
                        .<Integer>immutable()
                        .addNode(1)
                        .putEdge(2, 3) // also adds nodes 2 and 3 if not already present
                        .putEdge(2, 3) // no effect; Graph does not support parallel edges
                        .build();

        Set<Integer> successorsOfTwo = graph.successors(2); // returns {3}

        Assert.assertTrue(graph.nodes().contains(1));
        Assert.assertTrue(graph.hasEdgeConnecting(2, 3));
        // directed graph, only 2->3. If undirected, 2->3 and 3->2
        Assert.assertFalse(graph.hasEdgeConnecting(3, 2));
        Assert.assertTrue(graph.successors(2).contains(3));

        // These are equivalent (to each other and to the above expression).
        Assert.assertTrue(graph.predecessors(3).contains(2));
    }

    @Test
    public void testMutableValueGraph() {
        MutableValueGraph<Integer, Double> weightedGraph = ValueGraphBuilder.directed().build();
        weightedGraph.addNode(1);
        // also adds nodes 2 and 3 if not already present
        weightedGraph.putEdgeValue(2, 3, 1.5);
        // edge values (like Map values) need not be unique
        weightedGraph.putEdgeValue(3, 5, 1.5);

        // updates the value for (2,3) to 2.0
        weightedGraph.putEdgeValue(2, 3, 2.0);
    }
}
