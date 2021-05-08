package guava.gragh;

import com.google.common.graph.MutableNetwork;
import com.google.common.graph.NetworkBuilder;
import org.junit.Test;

import java.util.Set;

public class MutableNetworkTest {
    @Test
    public void test() {
        MutableNetwork<Integer, String> network = NetworkBuilder.directed().build();
        network.addNode(1);
        network.addEdge( 2, 3, "2->3");  // also adds nodes 2 and 3 if not already present

        Set<Integer> successorsOfTwo = network.successors(2);  // returns {3}
        Set<String> outEdgesOfTwo = network.outEdges(2);   // returns {"2->3"}

        network.addEdge( 2, 3, "2->3 too");  // throws; Network disallows parallel edges
        // by default
        network.addEdge( 2, 3, "2->3");  // no effect; this edge is already present
        // and connecting these nodes in this order

        Set<String> inEdgesOfFour = network.inEdges(4); // throws; node not in graph
    }
}
