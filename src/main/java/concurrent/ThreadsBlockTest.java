package concurrent;

import lombok.SneakyThrows;

import java.util.stream.IntStream;

public class ThreadsBlockTest {
    private Object lock = new Object();

    static class TestRun implements Runnable
    {
        private int runId;
        public TestRun(int runId)
        {
            this.runId = runId;
        }

        @SneakyThrows
        @Override
        public void run() {
            // lock
            synchronized (ThreadsBlockTest.class) {
                System.out.println(this.runId);
                // Thread 4 blocks all other threads
                if (runId == 4) {
                    // A time-consuming operation
                    // bad practice to use sleep while holding the lock!
                    // should use wait() instead
                    Thread.sleep(5 * 1000);
                }
                else {
                    Thread.sleep(1 * 1000);
                }

                System.out.println("complete " + runId);
            }
        }
    }

    static class TestRunNoLock implements Runnable
    {
        private int runId;
        public TestRunNoLock(int runId)
        {
            this.runId = runId;
        }

        @SneakyThrows
        @Override
        public void run() {

            System.out.println(this.runId);
            if (runId == 4) {
                // A time-consuming operation
                // But only blocks current thread
                Thread.sleep(5 * 1000);
            }
            else {
                Thread.sleep(1 * 1000);
            }

            System.out.println("complete " + runId);
        }
    }

    class TestRun2 implements Runnable
    {
        private int runId;
        public TestRun2(int runId)
        {
            this.runId = runId;
        }

        @SneakyThrows
        @Override
        public void run() {
            blockTest(runId);
        }
    }

    @SneakyThrows
    private synchronized void blockTest(int runId) {
        System.out.println(runId);

        // Thread 4 blocks all other threads
        if (runId == 4) {
            // A time-consuming operation
            // bad practice!
            Thread.sleep(10 * 1000);
        }
        else {
            Thread.sleep(200);
        }

        System.out.println("complete " + runId);
    }


    class TestRunWithWait implements Runnable
    {
        private int runId;
        public TestRunWithWait(int runId)
        {
            this.runId = runId;
        }

        @SneakyThrows
        @Override
        public void run() {
            blockTestWithWait(runId);
        }
    }

    // Better!
    // wait release CPU and lock
    // sleep only release CPU, do NOT release lock. May get deadlock
    @SneakyThrows
    private void blockTestWithWait(int runId) {
        // lock
        synchronized (lock) {
            System.out.println(runId);

            // Thread 4 NOT blocks other threads
            if (runId == 4) {
                // A time-consuming operation
                // wait release the lock, so other thread can enter
                lock.wait(10 * 1000);
            }
            else {
                lock.wait(1 * 1000);
            }
            System.out.println("complete " + runId);
        }
    }

    public void runTest() {
        IntStream.range(1, 100)
                .forEach(i -> new Thread(new TestRun2(i)).start());
    }

    public void runTestWithWait() {
        IntStream.range(1, 100)
                .forEach(i -> new Thread(new TestRunWithWait(i)).start());
    }

    public static void main(String[] args) throws Exception
    {
//        IntStream.range(1, 6)
//                .forEach(i -> new Thread(new TestRun(i)).start());

//        IntStream.range(1, 6)
//                .forEach(i -> new Thread(new TestRunNoLock(i)).start());

        ThreadsBlockTest test = new ThreadsBlockTest();
        test.runTest();

        test.runTestWithWait();
    }
}
