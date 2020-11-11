package guava;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import org.junit.Assert;
import org.junit.Test;
import org.openjdk.jol.info.GraphLayout;

import java.nio.charset.StandardCharsets;

public class BloomFilterTest {

    @Test
    public void test_mightContain()
    {
        int total = 1000000;
        BloomFilter<CharSequence> bloomFilter =
                BloomFilter.create(Funnels.stringFunnel(StandardCharsets.UTF_8), total * 2, 0.00001);

        for (int i = 0; i < total; i++)
        {
            bloomFilter.put("" + i);
        }

        int inSetCount = 0;
        for (int i = 0; i < total; i++)
        {
            if (bloomFilter.mightContain("" + i))
            {
                inSetCount++;
            }
        }
        Assert.assertEquals(total, inSetCount);

        int notInSetCount = 0;
        for (int i = total; i < total * 10; i++)
        {
            if (!bloomFilter.mightContain("" + i))
            {
                notInSetCount++;
            }
        }
        Assert.assertEquals(total * 9, notInSetCount);

        System.out.println(GraphLayout.parseInstance(bloomFilter).toFootprint());
    }
}
