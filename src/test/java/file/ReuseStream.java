package file;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReuseStream {

    @Test
    public void testFilter(){
        int[] array = {1,2,3,4,5,6};

        List<Integer> list = Arrays.stream(array).boxed().collect(Collectors.toList());

        Supplier<Stream<Integer>> streamSupplier = () -> list.stream();

        streamSupplier.get().forEach(System.out::println);

        //get() reuse the stream
        String res = streamSupplier.get().filter(i -> i %2 == 0)
                .map(i -> new String("" + i))
                .collect(Collectors.joining(","));

        Assert.assertEquals("2,4,6", res);
    }
}