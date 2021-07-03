package java9;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

public class CompletableFutureAPIImproveTest {

    @SneakyThrows
    @Test
    public void demo() {
        // delayedExecutor() is a static utility method used to return a new Executor that submits a task to the default executor after the given delay.
        Executor executor = CompletableFuture.delayedExecutor(5L, TimeUnit.SECONDS);

        CountDownLatch latch = new CountDownLatch(1);
        // Submit after 5s
        executor.execute(
                () -> {
                    System.out.println("Hi..." + Thread.currentThread().getName());
                    latch.countDown();
                }
        );

        latch.await(10, TimeUnit.SECONDS);
    }
}
