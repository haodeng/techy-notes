package concurrent;

import com.google.common.util.concurrent.*;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.util.concurrent.Executors;

public class ListenableFutureTest {

    public void test() {
        final ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(2));
        // submit the task to the threadpool to execute
        ListenableFuture<String> future = service.submit(() -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.interrupted();
            }
            return "Hi, there...";
        });

        Futures.addCallback(future, new FutureCallback<>() {
            @Override
            public void onSuccess(String result) {
                System.out.println("Done:" + result);
                service.shutdown();
            }

            @Override
            public void onFailure(Throwable thrown) {
                System.out.println(ExceptionUtils.getMessage(thrown));
                service.shutdown();
            }
        }, service);
    }



    public static void main(String[] args) {
        ListenableFutureTest test = new ListenableFutureTest();
        test.test();
    }
}
