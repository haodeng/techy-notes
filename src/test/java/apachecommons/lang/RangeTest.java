package apachecommons.lang;

import org.apache.commons.lang3.Range;
import org.junit.Assert;
import org.junit.Test;

public class RangeTest {
    @Test
    public void test_range()
    {
        Range<Long> range = Range.between(1L, 100L);

        Assert.assertFalse(range.contains(0L));
        Assert.assertTrue(range.contains(1L));
        Assert.assertTrue(range.contains(20L));
        Assert.assertTrue(range.contains(100L));
        Assert.assertFalse(range.contains(101L));

        Assert.assertTrue(range.isAfter(-100L));
        Assert.assertTrue(range.isBefore(1000L));

        Assert.assertTrue(range.isStartedBy(1L));
        Assert.assertFalse(range.isStartedBy(2L));
        Assert.assertTrue(range.isEndedBy(100L));
        Assert.assertFalse(range.isEndedBy(99L));

        Range<Long> range2 = Range.between(1L, 10L);
        Assert.assertEquals(range2, range.intersectionWith(range2));

        Range<Long> range3 = Range.between(50L, 200L);
        Assert.assertEquals(Range.between(50L, 100L), range.intersectionWith(range3));

        Assert.assertEquals(Long.valueOf(1), range.fit(-100L));
        Assert.assertEquals(Long.valueOf(1), range.fit(0L));
        Assert.assertEquals(Long.valueOf(1), range.fit(1L));
        Assert.assertEquals(Long.valueOf(2), range.fit(2L));
        Assert.assertEquals(Long.valueOf(99), range.fit(99L));
        Assert.assertEquals(Long.valueOf(100), range.fit(100L));
        Assert.assertEquals(Long.valueOf(100), range.fit(101L));
    }
}
