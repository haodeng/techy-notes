package java9;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

public class TryResourcesImproveTest {

    @Test
    public void demoBeforeJava9() throws IOException {
        try (BufferedReader reader = new BufferedReader(new StringReader("test...."));
             BufferedReader reader2 = new BufferedReader(new StringReader("test2...."));
             BufferedReader reader3 = new BufferedReader(new StringReader("test3...."))) {
            Assertions.assertEquals("test....", reader.readLine());
            Assertions.assertEquals("test2....", reader2.readLine());
            Assertions.assertEquals("test3....", reader3.readLine());
        }
    }

    @Test
    public void demoJava9() throws IOException {
        BufferedReader reader = new BufferedReader(new StringReader("test...."));
        BufferedReader reader2 = new BufferedReader(new StringReader("test2...."));
        BufferedReader reader3 = new BufferedReader(new StringReader("test3...."));

        try (reader; reader2; reader3) {
            Assertions.assertEquals("test....", reader.readLine());
            Assertions.assertEquals("test2....", reader2.readLine());
            Assertions.assertEquals("test3....", reader3.readLine());
        }
    }
}
