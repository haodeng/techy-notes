package guava.concurrent;

import com.google.common.util.concurrent.Uninterruptibles;
import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class UninterruptiblesTest {

    @Test
    public void sleep()
    {
        long before = System.currentTimeMillis();
        // a handy way to avoid dealing with the InterruptedExceptions is using Guava's Uninterruptibles
        Uninterruptibles.sleepUninterruptibly(1000, TimeUnit.MILLISECONDS);

        long after = System.currentTimeMillis();

        Assert.assertTrue(after - 1000 >= before);
    }
}
