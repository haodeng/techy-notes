package functional;

import org.junit.Assert;
import org.junit.Test;

public class FourIntFunctionTest {
    @Test
    public void addFourInts() {
        FourIntFunction function = (i1, i2 ,i3, i4) -> i1 + i2 + i3 + i4;
        Assert.assertEquals(10, this.applyFourIntFunction(1, 2, 3, 4, function));
    }

    @Test
    public void multiplyFourInts() {
        FourIntFunction function = (i1, i2 ,i3, i4) -> i1 * i2 * i3 * i4;
        Assert.assertEquals(16, this.applyFourIntFunction(2, 2, 2, 2, function));
    }

    @Test
    public void addFirstThreeIntsAndDivideByLast() {
        FourIntFunction function = (i1, i2 ,i3, i4) -> (i1 + i2 + i3)/i4;
        Assert.assertEquals(3, this.applyFourIntFunction(1, 1, 1, 1, function));
    }

    private int applyFourIntFunction(int one, int two, int three, int four, FourIntFunction function) {
        return function.calculate(one, two, three, four);
    }

    @FunctionalInterface
    public interface FourIntFunction {
        int calculate(int i1, int i2, int i3, int i4);
    }
}