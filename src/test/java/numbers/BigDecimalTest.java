package numbers;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class BigDecimalTest {
    @Test
    public void compare()
    {
        BigDecimal decimalA = new BigDecimal(2.01);
        BigDecimal decimalB = new BigDecimal(2.010000000000000);

        //Equals sometimes has precision issue
        Assert.assertTrue(decimalA.equals(decimalB));

        //Recommend to use compareTo
        Assert.assertEquals(0, decimalA.compareTo(decimalB));
    }
}
