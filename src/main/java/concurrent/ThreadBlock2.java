package concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class ThreadBlock2 {

    class CallableThread implements Callable {
        @Override
        public Object call() throws Exception {
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName());
            return "Hi";
        }
    }

    public void test() throws ExecutionException, InterruptedException {
        CallableThread callableThread = new CallableThread();
        FutureTask futureTask = new FutureTask<>(callableThread);
        new Thread(futureTask).start();

        System.out.println("Do something else in main thread");

        // get is blocking main thread, better add timeout
        System.out.println(futureTask.get());

        System.out.println("after get.");
    }

    static class ThreadA implements Runnable {

        @Override
        public void run() {
            try {
                System.out.println("Child thread, sleep");
                Thread.sleep(1000);
                System.out.println("Child thread, done");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void test2() throws InterruptedException {
        Thread thread = new Thread(new ThreadA());
        thread.start();

        // block main thread, wait child thread done.
        thread.join();
        System.out.println("Without join(), I'm gonna print out first");
    }

    public static void main(String[] args) throws Exception {
        ThreadBlock2 threadBlock2 = new ThreadBlock2();
        threadBlock2.test();

        threadBlock2.test2();
    }
}


