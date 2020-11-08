package apachecommons.lang;


import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import static org.junit.Assert.*;

public class StringUtilsTest {
    @Test
    public void test_emptyBlank()
    {
        assertTrue(StringUtils.isBlank(null));
        assertTrue(StringUtils.isBlank(""));
        assertTrue(StringUtils.isBlank("    "));

        assertTrue(StringUtils.isAllBlank(null, "", "    "));
        assertFalse(StringUtils.isAllBlank(null, "", "    ", "A"));

        assertTrue(StringUtils.isAnyBlank(null, "", "    "));
        assertFalse(StringUtils.isAnyBlank("A", "B", "    C"));

        assertTrue(StringUtils.isEmpty(null));
        assertTrue(StringUtils.isEmpty(""));
        assertFalse(StringUtils.isEmpty("   "));

        assertTrue(StringUtils.isAllEmpty(null, ""));
        assertFalse(StringUtils.isAllEmpty(null, "", " "));

        assertTrue(StringUtils.isAnyEmpty(null, ""));
        assertTrue(StringUtils.isAnyEmpty(null, "", " "));
        assertFalse(StringUtils.isAnyEmpty("A", "B", " C"));
    }

    @Test
    public void test_lowerUpperCases()
    {
        assertTrue(StringUtils.isAllLowerCase("asbc"));
        assertFalse(StringUtils.isAllLowerCase("asbc "));
        assertFalse(StringUtils.isAllLowerCase("A"));

        assertTrue(StringUtils.isAllUpperCase("ABCD"));
        assertFalse(StringUtils.isAllUpperCase("ABC "));
        assertFalse(StringUtils.isAllUpperCase("a"));

        assertTrue(StringUtils.isMixedCase("ABCDabcd"));
        //as long as there are both upper and lower case, true
        assertTrue(StringUtils.isMixedCase("ABCDabcd  ?;''1234"));
        //Only upper/lower case, false
        assertFalse(StringUtils.isMixedCase("ABCD"));
        assertFalse(StringUtils.isMixedCase("abcd"));
        assertFalse(StringUtils.isMixedCase(""));
        assertFalse(StringUtils.isMixedCase(null));
        assertFalse(StringUtils.isMixedCase("123456"));
        assertFalse(StringUtils.isMixedCase("!@#$%^&"));
    }
}
