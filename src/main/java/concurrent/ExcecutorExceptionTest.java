package concurrent;

import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.IntStream;

public class ExcecutorExceptionTest {

    @AllArgsConstructor
    class ALongRunningTestTask implements Callable<String> {

        private int taskNumber;

        @Override
        public String call() throws Exception {
            Thread.sleep(5 * 1000);
            return "done " + taskNumber;
        }
    }

    public void testTimeoutException() {
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        IntStream.rangeClosed(1, 10)
                .forEach(i -> {
                    Future<String> result = executorService.submit(new ALongRunningTestTask(i));
                    try {
                        // timeout before the long running task finished
                        System.out.println(result.get(2, TimeUnit.SECONDS));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });

        executorService.shutdown();
    }

    public void testRejectedExecutionException() {
        ExecutorService executorService =
                new ThreadPoolExecutor(1,
                        1,
                        500L, TimeUnit.MILLISECONDS,
                        // give queue size 5, when queue is full, throw RejectedExecutionException
                        new LinkedBlockingQueue(5));

        List<Future<String>> futures = new ArrayList<>();
        IntStream.rangeClosed(1, 10)
                .forEach(i -> {
                    // Expect RejectedExecutionException
                    Future<String> result = executorService.submit(new ALongRunningTestTask(i));
                    futures.add(result);
                });

        futures.forEach(f -> {
            try {
                System.out.println(f.get(10, TimeUnit.SECONDS));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        executorService.shutdown();
    }

    public static void main(String[] args) {
        ExcecutorExceptionTest test = new ExcecutorExceptionTest();
        test.testTimeoutException();
        test.testRejectedExecutionException();
    }
}
