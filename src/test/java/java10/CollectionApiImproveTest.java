package java10;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class CollectionApiImproveTest {

    @Test
    public void test_listCopyOf() {
        List ints = new ArrayList();
        ints.add(1);
        ints.add(2);
        ints.add(3);

        List unmodifiableInts = List.copyOf(ints);
        Assertions.assertThrows(UnsupportedOperationException.class, () -> {
            unmodifiableInts.add(4);
        });
    }

    @Test
    public void test_setCopyOf() {
        Set ints = new HashSet();
        ints.add(1);
        ints.add(2);
        ints.add(3);

        Set unmodifiableInts = Set.copyOf(ints);
        Assertions.assertThrows(UnsupportedOperationException.class, () -> {
            unmodifiableInts.add(4);
        });
    }

    @Test
    public void test_mapCopyOf() {
        Map<Integer, String> ints = new HashMap<>();
        ints.put(1, "test 1");
        ints.put(2, "test 2");
        ints.put(3, "test 3");

        Map unmodifiableInts = Map.copyOf(ints);
        Assertions.assertThrows(UnsupportedOperationException.class, () -> {
            unmodifiableInts.put(4, "tesst 4");
        });
    }
}
