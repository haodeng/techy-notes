package concurrent;

public class ThreadLocalTest {
    private static ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

    public static class TestRunnable implements Runnable {
        private Integer runnerId;
        public TestRunnable(Integer runnerId) {
            this.runnerId = runnerId;
        }

        @Override
        public void run() {
            threadLocal.set(runnerId);
            System.out.println("thread" + Thread.currentThread().getName() + ":" + threadLocal.get());

            done();
        }
    }

    // Must call remove after use to avoid memory leak
    private static void done() {
        threadLocal.remove();
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new TestRunnable(1));
        Thread thread2 = new Thread(new TestRunnable(2));

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
    }
}
