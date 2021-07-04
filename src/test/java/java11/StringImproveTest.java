package java11;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

public class StringImproveTest {

    @Test
    public void test_isBlank() {
        Assertions.assertTrue("".isBlank());
        // Space
        Assertions.assertTrue("   ".isBlank());
        // Tab whitespace
        Assertions.assertTrue(" ".isBlank());
    }

    @Test
    public void test_strip() {
        // Spaces
        Assertions.assertEquals("", "     ".strip());

        // Tab
        Assertions.assertEquals("", "   ".strip());

        Assertions.assertEquals("Me", " Me ".strip());
        Assertions.assertEquals("Me ", " Me ".stripLeading());
        Assertions.assertEquals(" Me", " Me ".stripTrailing());
    }

    @Test
    public void test_lines() {
        String str = "line 1\nLine 2\nline3";

        List<String> lines = str.lines().collect(Collectors.toList());
        // [line 1, Line 2, line3]
        System.out.println(lines);
    }

    @Test
    public void test_repeat() {
        Assertions.assertEquals("-----", "-".repeat(5));
    }
}
