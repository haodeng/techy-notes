package java8;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CompletableFutureDemo {

    @Test
    public void completedFuture() throws ExecutionException, InterruptedException {
        Future<String> completableFuture =
                CompletableFuture.completedFuture("Hello");
        Assert.assertEquals("Hello", completableFuture.get());
    }

    @Test
    public void supplyAsync() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future =
                CompletableFuture.supplyAsync(() -> "Hello");

        Assert.assertEquals("Hello", future.get());

        CompletableFuture<String> future2 =
                CompletableFuture.supplyAsync(() -> "Hello")
                        .thenApply(s -> s + " World");
        Assert.assertEquals("Hello World", future2.get());


        CompletableFuture<Void> future3 =
                CompletableFuture.supplyAsync(() -> "Hello")
                .thenAccept(s -> System.out.println("Computation returned: " + s));
        Assert.assertNull(future3.get());

        CompletableFuture<Void> future4 =
                CompletableFuture.supplyAsync(() -> "Hello")
                .thenRun(() -> System.out.println("Computation finished."));
        Assert.assertNull(future4.get());
    }

    @Test
    public void combiningFutures() throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture =
                CompletableFuture.supplyAsync(() -> "Hello")
                        .thenCompose(s -> CompletableFuture.supplyAsync(() -> s + " World"));
        Assert.assertEquals("Hello World", completableFuture.get());

        //2 arguments
        CompletableFuture<String> completableFuture2 =
                CompletableFuture.supplyAsync(() -> "Hello")
                        .thenCombine(CompletableFuture.supplyAsync(
                                () -> " World"),
                                (s1, s2) -> s1 + s2);
        Assert.assertEquals("Hello World", completableFuture2.get());

        //thenAcceptBoth
        CompletableFuture future = CompletableFuture.supplyAsync(() -> "Hello")
                .thenAcceptBoth(CompletableFuture.supplyAsync(
                        () -> " World"),
                        (s1, s2) -> System.out.println(s1 + s2));
        //only prints "Hello World"
        Assert.assertNull(future.get());
    }

    @Test
    public void multipleFuturesInParallel() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "Hello");
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> "Beautiful");
        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> "World");

        CompletableFuture<Void> combinedFuture = CompletableFuture.allOf(future1, future2, future3);

        combinedFuture.get();

        Assert.assertTrue(future1.isDone());
        Assert.assertTrue(future2.isDone());
        Assert.assertTrue(future3.isDone());

        String combined = Stream.of(future1, future2, future3)
                .map(CompletableFuture::join)
                .collect(Collectors.joining(" "));

        Assert.assertEquals("Hello Beautiful World", combined);
    }
}
