package apachecommons.lang;

import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;

public class RandomUtilsTest {

    @Test
    public void test()
    {
        System.out.println(RandomUtils.nextBoolean());

        System.out.println(RandomUtils.nextBytes(1));

        System.out.println(RandomUtils.nextDouble());
        System.out.println(RandomUtils.nextDouble(100, 110));

        System.out.println(RandomUtils.nextFloat());
        System.out.println(RandomUtils.nextFloat(2, 4));

        System.out.println(RandomUtils.nextInt());
        System.out.println(RandomUtils.nextInt(20, 30));

        System.out.println(RandomUtils.nextLong());
        System.out.println(RandomUtils.nextLong(1000L, 2000L));
    }
}
