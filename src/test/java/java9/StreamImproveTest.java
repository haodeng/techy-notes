package java9;


import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamImproveTest {

    @Test
    public void testTakeWhile() {
        Stream<Integer> orderedStream = Stream.of(1,2,3,4,5,6,7,8,9,10);
        /**
         * 1
         * 2
         * 3
         */
        orderedStream.takeWhile(x -> x < 4)
                .forEach(x -> System.out.println(x));


        // When that Predicate returns false for first element, then it stops evaluation and returns that subset elements.
        // That Predicate is evaluated until that returns false for first time.
        /**
         * 1
         */
        Stream<Integer> unorderedStream = Stream.of(1,4,2,5,9,6,7,8,3,10);
        unorderedStream.takeWhile(x -> x < 4)
                .forEach(x -> System.out.println(x));
    }

    @Test
    public void testDropWhile() {
        Stream<Integer> orderedStream = Stream.of(1,2,3,4,5,6,7,8,9,10);
        // If Stream is an Ordered, then dropWhile method drops the longest prefix elements which matches that Predicate and returns the rest of elements.
        /**
         * 8
         * 9
         * 10
         */
        orderedStream.dropWhile(x -> x < 8)
                .forEach(x -> System.out.println(x));

        Stream<Integer> unorderedStream = Stream.of(1,4,2,5,9,6,7,8,3,10);
        // When that Predicate returns false for first element, then it stops evaluation and returns the rest of subset elements into resulted Stream.
        /**
         * 9
         * 6
         * 7
         * 8
         * 3
         * 10
         */
        unorderedStream.dropWhile(x -> x < 8)
                .forEach(x -> System.out.println(x));
    }

    @Test
    public void testIterate() {
        /**
         * 2
         * 4
         * 16
         */
        IntStream.iterate(2, x -> x < 20, x -> x * x)
                .forEach(System.out::println);

        // java8 way by filter
        IntStream.iterate(2, x -> x * x)
                .filter(x -> x < 20)
                .forEach(System.out::println);
    }

    @Test
    public void testNullable() {
        // null, empty stream
        Stream.ofNullable(null).forEach(System.out::println);

        // 1
        Stream.ofNullable(1).forEach(System.out::println);
    }
}
