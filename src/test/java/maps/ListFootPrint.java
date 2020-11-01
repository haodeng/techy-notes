package maps;

import org.junit.Test;
import org.openjdk.jol.info.GraphLayout;
import org.openjdk.jol.vm.VM;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ListFootPrint {

    /**
     * ArrayList has better footprint
     */
    @Test
    public void testFootPrint()
    {
        System.out.println(VM.current().details());

        List<Integer> al = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            al.add(new Integer(i));
        }

        /**
         * java.util.ArrayList@2096442dd footprint:
         *      COUNT       AVG       SUM   DESCRIPTION
         *          1      4952      4952   [Ljava.lang.Object;
         *       1000        16     16000   java.lang.Integer
         *          1        24        24   java.util.ArrayList
         *       1002               20976   (total)
         */
        System.out.println(GraphLayout.parseInstance(al).toFootprint());

        List<Integer> ll = new LinkedList<>();
        for (int i = 0; i < 1000; i++) {
            ll.add(new Integer(i));
        }

        /**
         * java.util.LinkedList@4b168fa9d footprint:
         *      COUNT       AVG       SUM   DESCRIPTION
         *       1000        16     16000   java.lang.Integer
         *          1        32        32   java.util.LinkedList
         *       1000        24     24000   java.util.LinkedList$Node
         *       2001               40032   (total)
         */
        System.out.println(GraphLayout.parseInstance(ll).toFootprint());
    }
}
