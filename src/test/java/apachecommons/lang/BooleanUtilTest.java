package apachecommons.lang;

import org.apache.commons.lang3.BooleanUtils;
import org.junit.Test;
import static org.junit.Assert.*;

public class BooleanUtilTest {

    @Test
    public void test()
    {
        assertTrue(BooleanUtils.isTrue(true));
        assertFalse(BooleanUtils.isTrue(false));

        assertFalse(BooleanUtils.negate(Boolean.TRUE));

        assertTrue(BooleanUtils.toBoolean(-1));
        assertFalse(BooleanUtils.toBoolean(0));
        assertTrue(BooleanUtils.toBoolean(1));

        assertFalse(BooleanUtils.toBoolean("-1"));
        assertFalse(BooleanUtils.toBoolean("0"));
        assertTrue(BooleanUtils.toBoolean("1"));
        assertFalse(BooleanUtils.toBoolean("abcmd"));
        assertTrue(BooleanUtils.toBoolean("true"));
        assertTrue(BooleanUtils.toBoolean("True"));
        assertTrue(BooleanUtils.toBoolean("TRUE"));
    }
}
