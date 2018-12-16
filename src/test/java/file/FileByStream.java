package file;

import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileByStream {

    @Test
    public void test(){
        try (Stream<String> stream = Files.lines(
                Paths.get(this.getClass().getClassLoader().getResource("test.data").toURI()))) {
//            stream.forEach(System.out::println);

            List<String> lines = stream
                    .map(s -> {
                        System.out.println(s);
                        return s;
                    })
                    .sorted((a, b) -> a.compareTo(b))
                    .collect(Collectors.toList());

            Assert.assertEquals(6, lines.size());
            Assert.assertEquals("1", lines.get(0));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFilter(){
        try (Stream<String> stream = Files.lines(
                Paths.get(this.getClass().getClassLoader().getResource("test.data").toURI()))) {

            List<String> lines = stream
                    .map(s -> Integer.valueOf(s))
                    .filter(i -> i < 4)
                    .map(i -> new String(i + ""))
                    .collect(Collectors.toList());

            Assert.assertEquals(3, lines.size());
            Assert.assertEquals("3", lines.get(2));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testBuffer(){
        try (BufferedReader br = Files.newBufferedReader(
                Paths.get(this.getClass().getClassLoader().getResource("test.data").toURI()))) {

            List<String> lines = br.lines().collect(Collectors.toList());
            lines.forEach(System.out::println);

            Assert.assertEquals(6, lines.size());
            Assert.assertEquals("1", lines.get(0));

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}