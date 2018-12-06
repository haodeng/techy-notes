package functional;

import org.eclipse.collections.impl.list.Interval;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public class FunctionalInterfaceTest {
    @Test
    public void consumer() {
        List strings = Arrays.asList("one", "two", "three");


        // TODO - Can you remove the final keyword from the variable below?
        final List result = new ArrayList<String>();

        // TODO - Convert the anonymous inner class to a lambda
//        Consumer consumer = new Consumer<String>() {
//            @Override
//            public void accept(String each)
//            {
//                result.add(each.toUpperCase());
//            }
//        };

        Consumer<String> consumer = (v) -> result.add(v.toUpperCase());

        consumer.accept("zero");
        Assert.assertEquals(Arrays.asList("ZERO"), result);
        strings.forEach(consumer);
        Assert.assertEquals(Arrays.asList("ZERO", "ONE", "TWO", "THREE"), result);
    }

    @Test
    public void predicateIsEven() {
        List numbers = IntStream.rangeClosed(1, 10).boxed().collect(Collectors.toList());

        // TODO - Convert the anonymous inner class to a lambda
//        Predicate evenPredicate = new Predicate<Integer>() {
//            @Override
//            public boolean test(Integer integer)
//            {
//                return integer % 2 == 0;
//            }
//        };

        Predicate<Integer> evenPredicate = (v) -> v % 2 == 0;

        Assert.assertTrue(evenPredicate.test(2));
        Assert.assertFalse(evenPredicate.test(1));
        List evens = (List) numbers.stream().filter(evenPredicate).collect(Collectors.toList());
        Assert.assertTrue(evens.stream().allMatch(evenPredicate));
        Assert.assertFalse(evens.stream().noneMatch(evenPredicate));
        Assert.assertTrue(evens.stream().anyMatch(evenPredicate));
        Assert.assertEquals(Interval.evensFromTo(1, 10), evens);
    }

    @Test
    public void predicateIsOdd() {
        List numbers = IntStream.rangeClosed(1, 10).boxed().collect(Collectors.toList());

        // TODO - Convert the anonymous inner class to a lambda
//        Predicate oddPredicate = new Predicate<Integer>()
//        {
//            @Override
//            public boolean test(Integer integer)
//            {
//                return integer % 2 == 1;
//            }
//        };

        Predicate<Integer> oddPredicate = (v) -> v % 2 == 1;
        Assert.assertFalse(oddPredicate.test(2));
        Assert.assertTrue(oddPredicate.test(1));
        List odds = (List) numbers.stream().filter(oddPredicate).collect(Collectors.toList());
        Assert.assertTrue(odds.stream().allMatch(oddPredicate));
        Assert.assertFalse(odds.stream().noneMatch(oddPredicate));
        Assert.assertTrue(odds.stream().anyMatch(oddPredicate));
        Assert.assertEquals(Interval.oddsFromTo(1, 10), odds);
    }

    @Test
    public void function() {
        // TODO - Convert the anonymous inner class to a lambda and then a method reference
//        Function toUppercase = new Function<String, String>() {
//            @Override
//            public String apply(String s)
//            {
//                return s.toUpperCase();
//            }
//        };

//        Function toUppercase = (Function<String, String>)(s) -> (s).toUpperCase();
        Function<String, String> toUppercase = String::toUpperCase;

        Assert.assertEquals("UPPERCASE", toUppercase.apply("uppercase"));
        List<String> lowercase = Arrays.asList("a", "b", "c", "d");
        List<String> uppercase = (List<String>) lowercase.stream().map(toUppercase).collect(Collectors.toList());
        Assert.assertEquals(Arrays.asList("A", "B", "C", "D"), uppercase);
    }

    @Test
    public void supplier() {
        // TODO - Convert this anonymous inner class to a lambda and then to a constructor reference
//        Supplier supplier = new Supplier<List<String>>() {
//            @Override
//            public List<String> get()
//            {
//                return new CopyOnWriteArrayList<String>();
//            }
//        };

//        Supplier<List<String>> supplier = () -> new CopyOnWriteArrayList<String>();
        Supplier<List<String>> supplier = CopyOnWriteArrayList<String>::new;
        Assert.assertEquals(new CopyOnWriteArrayList<>(), supplier.get());
        Assert.assertNotSame(supplier.get(), supplier.get());
        List<String> list = (List<String>)Stream.of("1", "2", "3").collect(Collectors.toCollection(supplier));
        Assert.assertEquals(Arrays.asList("1", "2", "3"), list);
    }

    @Test
    public void biConsumer() {
        HashMap result = new HashMap<String, String>();
        // TODO - Convert the anonymous inner class to a lambda
//        BiConsumer biConsumer = new BiConsumer<String, String>()
//        {
//            @Override
//            public void accept(String key, String value)
//            {
//                result.put(key.toUpperCase(), value.toUpperCase());
//            }
//        };

//        BiConsumer biConsumer = (BiConsumer<String, String>)(k, v) -> result.put(k.toUpperCase(), v.toUpperCase());

        BiConsumer<String, String> biConsumer = (k, v) -> result.put(k.toUpperCase(), v.toUpperCase());
        biConsumer.accept("a", "one");

        Assert.assertEquals(
                new HashMap<String, String>() {{
                    put("A", "ONE");
                }}
                , result);

        HashMap lowercaseMap = new HashMap<String, String>() {{
            put("a", "one");
            put("b", "two");
            put("c", "three");
        }};

        lowercaseMap.forEach(biConsumer);
        Assert.assertEquals(
                new HashMap<String, String>() {{
                    put("A", "ONE");
                    put("B", "TWO");
                    put("C", "THREE");
                }},
                result);
    }

    @Test
    public void unaryOperator() {
        // TODO - Convert the anonymous inner class to a lambda
//        UnaryOperator squared = new UnaryOperator<Integer>()
//        {
//            @Override
//            public Integer apply(Integer integer)
//            {
//                return integer * integer;
//            }
//        };

        UnaryOperator squared = (UnaryOperator<Integer>)(v) -> v * v;

        Assert.assertEquals(Integer.valueOf(4), squared.apply(2));
        Assert.assertEquals(Integer.valueOf(9), squared.apply(3));
        Assert.assertEquals(Integer.valueOf(16), squared.apply(4));

        // TODO - Convert the anonymous inner class to a lambda
//        Assert.assertTrue(Stream.iterate(2, squared).anyMatch(new Predicate<Integer>()
//        {
//            @Override
//            public boolean test(Integer i)
//            {
//                return i.equals(Integer.valueOf(256));
//            }
//        }));

        Assert.assertTrue(Stream.iterate(2, squared).anyMatch((i) -> i.equals(Integer.valueOf(256))));
    }
}