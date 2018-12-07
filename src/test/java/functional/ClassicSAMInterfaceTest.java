package functional;

import org.eclipse.collections.impl.block.factory.Functions;
import org.eclipse.collections.impl.list.Interval;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ClassicSAMInterfaceTest {
    @Test
    public void comparator() {
        // TODO - Convert the comparator to a lambda and then to a method reference
//        Comparator<Integer> comparator = new Comparator<Integer>() {
//            @Override
//            public int compare(Integer one, Integer two) {
//                return one.compareTo(two);
//            }
//        };
        Comparator<Integer> comparator = (a, b) -> a.compareTo(b);

        Assert.assertEquals(0, comparator.compare(1, 1));
        Assert.assertEquals(-1, comparator.compare(1, 2));
        Assert.assertEquals(1, comparator.compare(3, 2));

        List integers = IntStream.rangeClosed(1, 5).boxed().collect(Collectors.toList());
        Collections.shuffle(integers);
        integers.sort(comparator);

        Assert.assertEquals(Arrays.asList(1, 2, 3, 4, 5), integers);
    }

    @Test
    public void runnable() {
        // Note: The following list reference is "effectively" final, which is a new feature in Java 8
        List list = new ArrayList<Integer>();
        // TODO - convert the anonymous Inner class to a lambda
//        Runnable runnable = new Runnable()
//        {
//            @Override
//            public void run()
//            {
//                list.add(1);
//            }
//        };

        Runnable runnable = () -> list.add(1);
        runnable.run();
        Assert.assertEquals(Arrays.asList(1), list);
        // TODO - convert the anonymous Inner class to a lambda
//        Interval.fromTo(2, 10).run(new Runnable()
//        {
//            @Override
//            public void run()
//            {
//                list.add(1);
//            }
//        });

        Interval.fromTo(2, 10).run(() -> list.add(1));
        List expectedList = Collections.nCopies(10, 1);
        Assert.assertEquals(expectedList, list);
    }

    @Test
    public void callable() throws Exception {
        // Note: The following set references is "effectively" final, which is a new feature in Java 8
        List<Integer> list = new ArrayList<Integer>();
        // TODO - convert the anonymous inner class to lambda
//        Callable<Boolean> callable = new Callable<Boolean>()
//        {
//            @Override
//            public Boolean call() throws Exception
//            {
//                return list.add(1);
//            }
//        };
        Callable<Boolean> callable = () -> list.add(1);
        Assert.assertTrue(callable.call());
        Assert.assertEquals(Arrays.asList(1), list);

        ExecutorService executor = Executors.newWorkStealingPool();
        List<Future<Boolean>> futures = executor.invokeAll(Collections.<Callable<Boolean>>nCopies(5, callable));

        // Note: Functions.throwing() is a utility method in Eclipse Collections without which we would
        // have to wrap the call to Future.get() in a try-catch block.
        Assert.assertTrue(futures.stream()
                .map(Functions.throwing(Future::get))
                .allMatch(b -> b));
        Assert.assertEquals(Arrays.asList(1,1,1,1,1,1), list);
    }
}