package maps;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class MergeMapTest {
    /**
     *  Merge mapA with mapB, return a new merged map
     *  If keys are the same:
     *    1. If both values are map instance, merge the values map recursively
     *    2. else replace the value with mapB
     */
    public static <K, V> Map<K, V> mergeMap(Map<K, V> mapA, Map<K, V> mapB)
    {
        if (mapA == null || mapA.isEmpty())
        {
            return mapB;
        }

        if (mapB == null || mapB.isEmpty())
        {
            return mapA;
        }

        BiFunction<V, V, V> combiner = new BiFunction<V, V, V>()
        {
            @Override
            public V apply(V v1, V v2)
            {
                // Both values are maps, recursively merge
                if (v1 instanceof Map && v2 instanceof Map)
                {
                    Map<K, V> map1 = (Map<K, V>) v1;
                    Map<K, V> map2 = (Map<K, V>) v2;

                    Map<K, V> map3 = new HashMap<>(map1);
                    map2.forEach(
                            (key, value) -> map3.merge(key, value, this)
                    );

                    return (V) map3;
                }
                else
                {
                    // Overwrite v1 with v2 if key are the same
                    return v2;
                }
            }
        };

        Map<K, V> mergedMap = new HashMap<>(mapA);
        mapB.forEach(
                (key, value) -> mergedMap.merge(key, value, combiner)
        );

        return mergedMap;
    }

    @Test
    public void test_mergeMap()
    {
        // MapA: keys 1, 2, 3, 4
        Map<String, Object> mapA = new HashMap<>();
        mapA.put("1", "1");
        mapA.put("2", "2");
        Map<String, Object> mapA_1 = new HashMap<>();
        mapA_1.put("A1", "A1");
        mapA_1.put("A1.1", "A1.1");
        mapA.put("3", mapA_1);
        Map<String, Object> mapA_2 = new HashMap<>();
        mapA_2.put("A2", "A2");
        mapA.put("4", mapA_2);

        // MapB: key 1, 3, 11, 12. Key 1 and 3 conflict with mapA
        Map<String, Object> mapB = new HashMap<>();
        mapB.put("1", "1 from MapB");
        Map<String, Object> mapB_1 = new HashMap<>();
        mapB_1.put("A1", "A1 from MapB");
        mapB_1.put("B1", "B1");
        mapB.put("3", mapB_1);
        mapB.put("11", "11");
        mapB.put("12", "12");

        Map<String, Object> mergedMap = MergeMapTest.mergeMap(mapA, mapB);
        Assert.assertEquals(6, mergedMap.size());
        // Expect key "1", value is from mapB
        Assert.assertEquals("1 from MapB", mergedMap.get("1"));

        // Key 2, no conflict, value is from mapA
        Assert.assertEquals("2", mergedMap.get("2"));

        // Key 3 value is a map
        Assert.assertTrue(mergedMap.get("3") instanceof Map);
        // The map's values are merged recursively by the same rule
        Map<String, Object> map3 = (Map<String, Object>) mergedMap.get("3");
        // Expect map3 has 3 entries, 1 from mapA.get("3), 2 from mapB.get("3")
        Assert.assertEquals(3, map3.size());
        Assert.assertEquals("A1 from MapB", map3.get("A1"));
        Assert.assertEquals("A1.1", map3.get("A1.1"));
        Assert.assertEquals("B1", map3.get("B1"));

        // Key 11, 12, no conflict, value is from mapB
        Assert.assertEquals("11", mergedMap.get("11"));
        Assert.assertEquals("12", mergedMap.get("12"));
    }
}
