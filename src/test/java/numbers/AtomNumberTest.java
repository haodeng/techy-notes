package numbers;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class AtomNumberTest {
    @Test
    public void compareAtomicInteger()
    {
        AtomicInteger atomicIntegerA = new AtomicInteger(10);
        AtomicInteger atomicIntegerB = new AtomicInteger(10);

        Assert.assertFalse(atomicIntegerA == atomicIntegerB);
        //Do not equal
        Assert.assertFalse(atomicIntegerA.equals(atomicIntegerB));

        //primitive value is ==
        Assert.assertTrue(atomicIntegerA.get() == atomicIntegerB.get());
    }

    @Test
    public void compareAtomicLong()
    {
        AtomicLong atomicLongA = new AtomicLong(10000000);
        AtomicLong atomicLongB = new AtomicLong(10000000);

        Assert.assertFalse(atomicLongA == atomicLongB);
        //Do not equal
        Assert.assertFalse(atomicLongA.equals(atomicLongB));

        //primitive value is ==
        Assert.assertTrue(atomicLongA.get() == atomicLongB.get());
    }
}
