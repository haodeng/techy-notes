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

        assertTrue(StringUtils.isWhitespace(""));
        //Space
        assertTrue(StringUtils.isWhitespace(" "));
        //Tab
        assertTrue(StringUtils.isWhitespace("   "));
        assertFalse(StringUtils.isWhitespace("a"));
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

        assertEquals("ABCØ", StringUtils.upperCase("abcø"));
        assertEquals("ABC", StringUtils.upperCase("ABC"));
        assertEquals("ABC123", StringUtils.upperCase("abc123"));

        assertEquals("abcø", StringUtils.lowerCase("ABCØ"));
        assertEquals("abc", StringUtils.lowerCase("abc"));
        assertEquals("abc123", StringUtils.lowerCase("ABC123"));

        assertEquals("abcDEF", StringUtils.swapCase("ABCdef"));
        assertEquals("abcDEF  123!@#$?", StringUtils.swapCase("ABCdef  123!@#$?"));
    }

    @Test
    public void test_index()
    {
        assertEquals(-1, StringUtils.indexOf(null, "a"));
        assertEquals(-1, StringUtils.indexOf("", "a"));
        assertEquals(0, StringUtils.indexOf("abc", ""));

        //Take the first index
        assertEquals(0, StringUtils.indexOf("abaca", "a"));
        assertEquals(2, StringUtils.indexOf("abaca", "ac"));

        assertEquals(2, StringUtils.indexOf("abaca", "a", 1));
        assertEquals(5, StringUtils.indexOf("abacaab", "ab", 1));
    }
}
