package apachecommons.lang;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.junit.Assert;
import org.junit.Test;

public class ExceptionUtilsTest {

    @Test
    public void test()
    {
        System.out.println(ExceptionUtils.getStackTrace(new RuntimeException("Test")));

        Assert.assertEquals("RuntimeException: Test",
                ExceptionUtils.getMessage(new RuntimeException("Test")));
    }
}
