package java9;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class ImmutablesTest {

    @Test
    public void immutableList() {
        List emptyList = List.of();
        Assertions.assertEquals(Collections.EMPTY_LIST, emptyList);

        List immutableList = List.of("1", "2", "3");
        Assertions.assertEquals(3, immutableList.size());

        // immutableList, cannot modify
        Assertions.assertThrows(UnsupportedOperationException.class, () -> {
            immutableList.add("4");
        });

        // java8 can use use Collections.unmodifiableXXX, but tedious and verbose
        List myImmutableList = Collections.unmodifiableList(Arrays.asList("1", "2", "3"));
        Assertions.assertEquals(3, myImmutableList.size());
        Assertions.assertThrows(UnsupportedOperationException.class, () -> {
            myImmutableList.add("4");
        });
    }

    @Test
    public void immutableSet() {
        Set immutableSet = Set.of("1", "2", "3");
        Assertions.assertEquals(3, immutableSet.size());

        // immutable, cannot modify
        Assertions.assertThrows(UnsupportedOperationException.class, () -> {
            immutableSet.add("4");
        });

        // java8 can use use Collections.unmodifiableXXX, but tedious and verbose
        Set set = new HashSet();
        set.add("1");
        set.add("2");
        set.add("3");
        Set myImmutableSet = Collections.unmodifiableSet(set);
        Assertions.assertThrows(UnsupportedOperationException.class, () -> {
            myImmutableSet.add("4");
        });
    }

    @Test
    public void immutableMap() {
        // Init via Map.of
        Map immutableMap = Map.of(1, "one", 2, "two", 3, "three");
        Assertions.assertEquals(3, immutableMap.size());

        // immutable, cannot modify
        Assertions.assertThrows(UnsupportedOperationException.class, () -> {
            immutableMap.put(4, "four");
        });

        // Init via Map.ofEntries
        Map<Integer,String> immutableMapViaEntry = Map.ofEntries(
                Map.entry(1,"one"),
                Map.entry(2,"two"),
                Map.entry(3,"three"));
        // immutable, cannot modify
        Assertions.assertThrows(UnsupportedOperationException.class, () -> {
            immutableMapViaEntry.put(4, "four");
        });

        // java8 can use use Collections.unmodifiableXXX, but tedious and verbose
        Map map = new HashMap();
        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");
        Map myImmutableMap = Collections.unmodifiableMap(map);
        Assertions.assertThrows(UnsupportedOperationException.class, () -> {
            myImmutableMap.put(4, "four");
        });
    }
}
