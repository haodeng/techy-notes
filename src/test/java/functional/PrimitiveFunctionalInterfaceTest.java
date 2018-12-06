package functional;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.DoubleAdder;
import java.util.concurrent.atomic.LongAdder;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class PrimitiveFunctionalInterfaceTest
{
    @Test
    public void IntConsumer() {
        LongAdder adder = new LongAdder();
//        IntConsumer consumer = new IntConsumer()
//        {
//            @Override
//            public void accept(int value)
//            {
//                adder.add((long)value);
//            }
//        };
        IntConsumer consumer = (v) -> adder.add((long)v);
        IntStream.rangeClosed(1, 5).forEach(consumer);
        Assert.assertEquals(15, adder.longValue());
    }

    @Test
    public void LongConsumer() {
        LongAdder adder = new LongAdder();
//        LongConsumer consumer = new LongConsumer()
//        {
//            @Override
//            public void accept(long value)
//            {
//                adder.add(value);
//            }
//        };
        LongConsumer consumer = (v) -> adder.add(v);
        LongStream.rangeClosed(1, 5).forEach(consumer);
        Assert.assertEquals(15, adder.longValue());
    }

    @Test
    public void DoubleConsumer() {
        DoubleAdder adder = new DoubleAdder();
//        DoubleConsumer consumer = new DoubleConsumer()
//        {
//            @Override
//            public void accept(double value)
//            {
//                adder.add(value);
//            }
//        };
        DoubleConsumer consumer = (v) -> adder.add(v);
        DoubleStream.of(1.0, 2.0, 3.0, 4.0, 5.0).forEach(consumer);
        Assert.assertEquals(15.0, adder.doubleValue(), 0.0);
    }

    @Test
    public void IntPredicate() {
//        IntPredicate predicate = new IntPredicate()
//        {
//            @Override
//            public boolean test(int value)
//            {
//                return value % 2 == 0;
//            }
//        };
        IntPredicate predicate = (v) -> v % 2 == 0;
        List evens = IntStream.rangeClosed(1, 5).filter(predicate).boxed().collect(Collectors.toList());
        Assert.assertEquals(Arrays.asList(2, 4), evens);
        List odds = IntStream.rangeClosed(1, 5).filter(predicate.negate()).boxed().collect(Collectors.toList());
        Assert.assertEquals(Arrays.asList(1, 3, 5), odds);
        Assert.assertTrue(IntStream.rangeClosed(1, 5).anyMatch(predicate));
        Assert.assertFalse(IntStream.rangeClosed(1, 5).allMatch(predicate));
        Assert.assertFalse(IntStream.rangeClosed(1, 5).noneMatch(predicate));
    }

    @Test
    public void LongPredicate() {
//        LongPredicate predicate = new LongPredicate()
//        {
//            @Override
//            public boolean test(long value)
//            {
//                return value % 2 == 0;
//            }
//        };
        LongPredicate predicate = (v) -> v % 2 == 0;

        List evens = LongStream.rangeClosed(1, 5).filter(predicate).boxed().collect(Collectors.toList());
        Assert.assertEquals(Arrays.asList(2L, 4L), evens);
        List odds = LongStream.rangeClosed(1, 5).filter(predicate.negate()).boxed().collect(Collectors.toList());
        Assert.assertEquals(Arrays.asList(1L, 3L, 5L), odds);
        Assert.assertTrue(LongStream.rangeClosed(1, 5).anyMatch(predicate));
        Assert.assertFalse(LongStream.rangeClosed(1, 5).allMatch(predicate));
        Assert.assertFalse(LongStream.rangeClosed(1, 5).noneMatch(predicate));
    }

    @Test
    public void DoublePredicate() {
//        DoublePredicate predicate = new DoublePredicate()
//        {
//            @Override
//            public boolean test(double value)
//            {
//                return value > 3.0;
//            }
//        };
        DoublePredicate predicate = (v) -> v > 3.0;

        List greaterThan =
                DoubleStream.of(1.0, 2.0, 3.0, 4.0, 5.0).filter(predicate).boxed().collect(Collectors.toList());
        Assert.assertEquals(Arrays.asList(4.0d, 5.0d), greaterThan);
        List lessThanEqualTo =
                DoubleStream.of(1.0, 2.0, 3.0, 4.0, 5.0).filter(predicate.negate()).boxed().collect(Collectors.toList());
        Assert.assertEquals(Arrays.asList(1.0d, 2.0d, 3.0d), lessThanEqualTo);
        Assert.assertTrue(DoubleStream.of(1.0, 2.0, 3.0, 4.0, 5.0).anyMatch(predicate));
        Assert.assertFalse(DoubleStream.of(1.0, 2.0, 3.0, 4.0, 5.0).allMatch(predicate));
        Assert.assertFalse(DoubleStream.of(1.0, 2.0, 3.0, 4.0, 5.0).noneMatch(predicate));
    }
}