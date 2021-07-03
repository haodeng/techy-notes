package guava;

import com.google.common.base.Preconditions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class PreconditionsTest {

    @Test
    public void test()
    {
        int i = -1;
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Preconditions.checkArgument(i >= 0, "Argument was %s but expected nonnegative", i);
        });

        Assertions.assertThrows(NullPointerException.class, () -> {
            Preconditions.checkNotNull(null);
        });

        boolean state = false;
        Assertions.assertThrows(IllegalStateException.class, () -> {
            Preconditions.checkState(state);
        });
    }
}
