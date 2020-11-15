package apachecommons.lang;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;

public class RandomStringUtilsTest {

    @Test
    public void test()
    {
        System.out.println(RandomStringUtils.random(10));

        System.out.println(RandomStringUtils.random(10, "abcd"));
        System.out.println(RandomStringUtils.random(10, 'a', 'b', 'c', '1'));

        //number only
        System.out.println(RandomStringUtils.random(10, false, true));

        System.out.println(RandomStringUtils.randomAlphabetic(20));
        System.out.println(RandomStringUtils.randomAlphabetic(10, 15));

        System.out.println(RandomStringUtils.randomAlphanumeric(10));

        System.out.println(RandomStringUtils.randomAscii(10));
        System.out.println(RandomStringUtils.randomAscii(5, 12));

        System.out.println(RandomStringUtils.randomGraph(10));

        System.out.println(RandomStringUtils.randomPrint(10));
    }
}
