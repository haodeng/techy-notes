package concurrent;

import lombok.SneakyThrows;

import java.util.stream.IntStream;

public class ThreadsBlockTest {
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

            // bad practice!
            synchronized (ThreadsBlockTest.class) {
                System.out.println(this.runId);
                // Thread 4 blocks all other threads
                if (runId == 4) {
                    // A time-consuming operation
                    Thread.sleep(500 * 1000);
                }
                else {
                    Thread.sleep(1 * 1000);
                }
            }
        }
    }

    public static void main(String[] args) throws Exception
    {
        IntStream.range(1, 6)
                .forEach(i -> new Thread(new TestRun(i)).start());
    }
}
