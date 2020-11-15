package apachecommons.lang;

import org.apache.commons.lang3.ObjectUtils;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class ObjectUtilsTest {

    @Test
    public void test_nullEmpty()
    {
        assertTrue(ObjectUtils.allNull(null, null));
        assertTrue(ObjectUtils.anyNull(null, "123"));
        assertTrue(ObjectUtils.anyNotNull(null, "123"));

        assertTrue(ObjectUtils.isEmpty(null));
        assertTrue(ObjectUtils.isEmpty(""));
        assertFalse(ObjectUtils.isEmpty("  "));
        assertTrue(ObjectUtils.isEmpty(new int[]{}));
        assertTrue(ObjectUtils.isEmpty(new HashMap<>()));

        assertTrue(ObjectUtils.isNotEmpty(new Object()));
        assertTrue(ObjectUtils.isNotEmpty("abc"));
        assertTrue(ObjectUtils.isNotEmpty(new int[]{1, 2, 3}));

        Map map = new HashMap<>();
        map.put(1L, "1");
        assertTrue(ObjectUtils.isNotEmpty(map));
    }

    @Test
    public void test()
    {
        System.out.println(ObjectUtils.toString("123", () -> "a"));
    }
}
