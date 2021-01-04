package guava.collect;

import com.google.common.collect.ObjectArrays;
import org.junit.Assert;
import org.junit.Test;

public class ObjectArraysTest {

    @Test
    public void test_concat()
    {
        String[] a = {"1", "2"};
        String[] b = {"3", "4"};

        String[] c =  ObjectArrays.concat(a, b, String.class);
        Assert.assertEquals(4, c.length);
        Assert.assertEquals("1", c[0]);
        Assert.assertEquals("2", c[1]);
        Assert.assertEquals("3", c[2]);
        Assert.assertEquals("4", c[3]);

        String[] b2 = {};
        String[] c2 =  ObjectArrays.concat(a, b2, String.class);
        Assert.assertEquals(2, c2.length);
        Assert.assertEquals("1", c2[0]);
        Assert.assertEquals("2", c2[1]);
    }
}
