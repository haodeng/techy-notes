package guava;

import com.google.common.base.Preconditions;
import org.junit.Assert;
import org.junit.Test;

public class PreconditionsTest {

    @Test
    public void test()
    {
        int i = -1;
        Assert.assertThrows(IllegalArgumentException.class, () -> {
            Preconditions.checkArgument(i >= 0, "Argument was %s but expected nonnegative", i);
        });

        Assert.assertThrows(NullPointerException.class, () -> {
            Preconditions.checkNotNull(null);
        });
    }
}
