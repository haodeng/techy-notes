package java12;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TeeCollectorTest {

    @Test
    public void test() {
        //Two collectors and a Bi-function.
        //All input values are passed to each collector and the result is available in the Bi-function.
        double mean = Stream.of(1, 2, 3, 4, 5)
                .collect(Collectors.teeing(
                        Collectors.summingDouble(i -> i),
                        Collectors.counting(),
                        (sum, n) -> sum / n));

        Assertions.assertEquals(3.0, mean);
    }
}
