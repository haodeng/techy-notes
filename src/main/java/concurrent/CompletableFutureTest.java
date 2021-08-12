package concurrent;

import java.util.concurrent.*;

public class CompletableFutureTest {
    public void test() throws InterruptedException, ExecutionException {
        Future<String> future = workAsync();
        System.out.println(future.get());
    }

    private Future<String> workAsync() {
        ExecutorService service = Executors.newFixedThreadPool(2);

        CompletableFuture<String> completableFuture = new CompletableFuture<>();
        completableFuture.whenComplete((o, o2) -> {
            //handle complete
            System.out.println("done:" + o);
            service.shutdown();
        });

        service.submit(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            completableFuture.complete("Hi, there...");
        });

        return completableFuture;
    }

    public void chains() throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(2);

        CompletableFuture
                .supplyAsync(() -> "step 1", service)
                .thenApplyAsync(step1 -> step1 + ", 2", service)
                .thenApplyAsync(step12 -> step12 + ", 3", service)
                .thenAcceptAsync(step123 -> {
                    System.out.println("Done: " + step123);
                    service.shutdown();}, service)
                .get();
    }

    public static void main(String[] args) throws Exception{
        CompletableFutureTest test = new CompletableFutureTest();
        test.test();
//        test.chains();
    }
}
