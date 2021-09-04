package java12;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Java12Test {

    @Test
    public void testTeeing() {
        //Two collectors and a Bi-function.
        //All input values are passed to each collector and the result is available in the Bi-function.
        double mean = Stream.of(1, 2, 3, 4, 5)
                .collect(Collectors.teeing(
                        Collectors.summingDouble(i -> i),
                        Collectors.counting(),
                        (sum, n) -> sum / n));

        Assertions.assertEquals(3.0, mean);
    }

    @Test
    public void testGetCompactNumberInstance() {
        NumberFormat usShort =
                NumberFormat.getCompactNumberInstance(
                        new Locale("en", "US"),
                        NumberFormat.Style.SHORT);
        usShort.setMaximumFractionDigits(2);
        Assertions.assertEquals("3.11K", usShort.format(3111));

        NumberFormat usLong =
                NumberFormat.getCompactNumberInstance(
                        new Locale("en", "US"),
                        NumberFormat.Style.LONG);
        usLong.setMaximumFractionDigits(2);
        Assertions.assertEquals("3.11 thousand", usLong.format(3111));
    }

    @Test
    public void identicalFileShouldMatch() throws IOException {
        Path filePath1 = Files.createTempFile("file1", ".txt");
        Path filePath2 = Files.createTempFile("file2", ".txt");
        Files.writeString(filePath1, "content");
        Files.writeString(filePath2, "content");

        long mismatch = Files.mismatch(filePath1, filePath2);
        Assertions.assertEquals(-1, mismatch);
    }

    @Test
    public void differentFileShouldMismatch() throws IOException {
        Path filePath1 = Files.createTempFile("file1", ".txt");
        Path filePath2 = Files.createTempFile("file2", ".txt");
        Files.writeString(filePath1, "1234 67");
        Files.writeString(filePath2, "1234567");

        long mismatch = Files.mismatch(filePath1, filePath2);
        // first mismatch from index 4
        Assertions.assertEquals(4, mismatch);
    }

    @Test
    public void stringTransform() {
        String text = "Hao Deng";
        String transformed = text.transform(value ->
                new StringBuilder(value).reverse().toString()
        );

        Assertions.assertEquals("gneD oaH", transformed);

        String s = "1,2,3";
        List strList = s.transform(s1 -> Arrays.asList(s1.split(",")));
        Assertions.assertEquals("1", strList.get(0));
        Assertions.assertEquals("2", strList.get(1));
        Assertions.assertEquals("3", strList.get(2));
    }

    @Test
    public void stringIndent() {
        String text = "Hao\nDeng";
        //    Hao
        //    Deng
        System.out.println(text.indent(4));
        // Hao
        // Deng
        System.out.println(text.indent(1));
    }
}
