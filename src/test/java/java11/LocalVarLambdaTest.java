package java11;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.annotation.Nonnull;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.IntStream;

public class LocalVarLambdaTest {

    @FunctionalInterface
    interface MyFunc {
        double test(double x, double y);
    }

    @Test
    public void test() {
        long evenCount = IntStream.rangeClosed(1, 100)
                .filter((@Nonnull var i) -> i%2 == 0)
                .count();

        Assertions.assertEquals(50, evenCount);

        // Pre java 11
        // Explicitly Typed
//        MyFunc divide = (double x, double y) ->  x / y;

        // Implicitly Typed
//        MyFunc divide = (x, y) ->  x / y;

        //New Implicitly Typed Java 11+ Syntax
        MyFunc divide = (var x, var y) ->  x / y;
        Assertions.assertEquals(5, divide.test(10, 2));
    }
}
