package java9;

import org.junit.jupiter.api.Test;

import java.util.Optional;

public class OptionalImproveTest {
    @Test
    public void testStream() {
        Optional optional = Optional.ofNullable(1);
        optional.stream().forEach(System.out::println);
    }

    @Test
    public void test_ifPresentOrElse() {
        // Found 4
        Optional.of(4)
                .ifPresentOrElse( x -> System.out.println("Found: " + x),
                        () -> System.out.println("Not Found."));

        // Not Found.
        Optional.ofNullable(null)
                .ifPresentOrElse( x -> System.out.println("Found: " + x),
                        () -> System.out.println("Not Found."));

        // Not Found.
        Optional.empty()
                .ifPresentOrElse( x -> System.out.println("Found: " + x),
                        () -> System.out.println("Not Found."));
    }

    @Test
    public void test_or() {
        // 4
        Optional.of(4)
                .or(() -> Optional.of(99))
                .ifPresent(System.out::println);

        // 99
        Optional.empty()
                .or(() -> Optional.of(99))
                .ifPresent(System.out::println);
    }
}
