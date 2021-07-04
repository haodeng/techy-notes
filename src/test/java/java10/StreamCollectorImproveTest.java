package java10;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class StreamCollectorImproveTest {

    @Test
    public void test() {
        List<Integer> ints = new ArrayList();
        ints.add(1);
        ints.add(2);
        ints.add(3);

        List unmodifiableList = ints.stream()
                .collect(Collectors.toUnmodifiableList());

        Set unmodifiableSet = ints.stream()
                .collect(Collectors.toUnmodifiableSet());

        Map<Integer, String> unmodifiableMap = ints.stream()
                .collect(Collectors.toUnmodifiableMap(i -> i, i -> "Test " + i));
        Assertions.assertEquals("Test 1", unmodifiableMap.get(1));
    }
}
