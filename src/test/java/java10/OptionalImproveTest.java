package java10;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;
import java.util.Optional;

public class OptionalImproveTest {
    @Test
    public void test() {
        Optional<String> name = Optional.ofNullable("Hao");
        Assertions.assertEquals("Hao", name.orElseThrow());

        Optional<String> nullName = Optional.ofNullable(null);
        // java.util.NoSuchElementException: No value present
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            nullName.orElseThrow();
        });

    }
}
