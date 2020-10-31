package smoothiemap;

import io.timeandspace.smoothie.SmoothieMap;
import org.junit.Assert;
import org.junit.Test;
import org.openjdk.jol.info.GraphLayout;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SmoothieMapTest {
    @Test
    public void test_putGetRemove()
    {
        Map<String, Object> testMap = SmoothieMap.<String, Object>newBuilder()
                .expectedSize(10)
                .build();

        testMap.put("key1", 1);
        testMap.put("key2", 2L);
        testMap.put("key3", true);
        testMap.put("key4", "4");

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        testMap.put("key5", timestamp);

        Assert.assertEquals(testMap.get("key1"), 1);
        Assert.assertEquals(testMap.get("key2"), 2L);
        Assert.assertEquals(testMap.get("key3"), true);
        Assert.assertEquals(testMap.get("key4"), "4");
        Assert.assertEquals(testMap.get("key5"), timestamp);

        Object value1 = testMap.remove("key1");
        Assert.assertEquals(value1, 1);
        Assert.assertNull(testMap.get("key1"));
        Assert.assertNull(testMap.remove("key1"));
    }

    /**
     * Run it manually
     * SmoothieMap has less footprint than HashMap
     */
    @Test
    public void evaluate_footprint()
    {
        List<Map<String, Object>> rows = new ArrayList<>();

        //100000 rows of HashMap
        for (int i = 0; i < 100000; i++)
        {
            Map<String, Object> map = new HashMap<>(20);
            for (int k = 0; k < 20; ++k)
            {
                map.put(k + "", k);
            }

            rows.add(map);
        }

        /**
         * java.util.ArrayList@18eed359d footprint:
         *      COUNT       AVG       SUM   DESCRIPTION
         *    2000000        24  48000000   [C
         *          1    426856    426856   [Ljava.lang.Object;
         *     100000       144  14400000   [Ljava.util.HashMap$Node;
         *         20        16       320   java.lang.Integer
         *    2000000        24  48000000   java.lang.String
         *          1        24        24   java.util.ArrayList
         *     100000        48   4800000   java.util.HashMap
         *    2000000        32  64000000   java.util.HashMap$Node
         *    6200022           179627200   (total)
         */
        System.out.println(GraphLayout.parseInstance(rows).toFootprint());

        rows = new ArrayList<>();
        //100000 rows of SmoothieMap
        for (int i = 0; i < 100000; i++)
        {
            Map<String, Object> map = SmoothieMap.<String, Object>newBuilder()
                    .expectedSize(20)
                    .build();;
            for (int k = 0; k < 20; ++k)
            {
                map.put(k + "", k);
            }

            rows.add(map);
        }

        /**
         * java.util.ArrayList@72245a7dd footprint:
         *      COUNT       AVG       SUM   DESCRIPTION
         *    2000000        24  48000000   [C
         *     100000        24   2400000   [I
         *     100001        28   2826856   [Ljava.lang.Object;
         *     100000       416  41600000   io.timeandspace.smoothie.InterleavedSegments$IntermediateCapacitySegment
         *     100000        64   6400000   io.timeandspace.smoothie.SmoothieMap
         *         20        16       320   java.lang.Integer
         *    2000000        24  48000000   java.lang.String
         *          1        24        24   java.util.ArrayList
         *    4400022           149227200   (total)
         */
        System.out.println(GraphLayout.parseInstance(rows).toFootprint());
    }
}
